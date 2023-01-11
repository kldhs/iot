package com.iot.security.service.impl;

import com.abupdate.ucc.common.cache.RedisUtil;
import com.abupdate.ucc.common.data.constant.RedisKeyConstant;
import com.abupdate.ucc.common.data.constant.SystemConstant;
import com.abupdate.ucc.common.data.enums.CompanyStatusEnum;
import com.abupdate.ucc.common.data.enums.RoleLevelEnum;
import com.abupdate.ucc.common.data.enums.SysScopeEnum;
import com.abupdate.ucc.common.data.enums.SysUserStatusEnum;
import com.abupdate.ucc.common.data.result.CommonResult;
import com.abupdate.ucc.common.data.result.ResultEnum;
import com.abupdate.ucc.common.util.RsaKeyHelper;
import com.abupdate.ucc.common.util.UserInfoContext;
import com.abupdate.ucc.common.util.VerificationCode;
import com.abupdate.ucc.common.util.jwt.JwtToken;
import com.abupdate.ucc.common.util.model.UserInfo;
import com.abupdate.ucc.dao.company.SysCompanyDao;
import com.abupdate.ucc.dao.model.entity.CompanyEntity;
import com.abupdate.ucc.dao.model.entity.RoleEntity;
import com.abupdate.ucc.dao.model.entity.UserEntity;
import com.abupdate.ucc.dao.model.entity.UserRoleEntity;
import com.abupdate.ucc.dao.model.entity.permission.PermissionEntity;
import com.abupdate.ucc.dao.model.entity.permission.RolePermissionEntity;
import com.abupdate.ucc.dao.permission.SysPermissionDao;
import com.abupdate.ucc.dao.permission.SysRolePermissionDao;
import com.abupdate.ucc.dao.user.SysRoleDao;
import com.abupdate.ucc.dao.user.SysUserDao;
import com.abupdate.ucc.dao.user.SysUserRoleDao;
import com.abupdate.ucc.service.AuthTokenService;
import com.abupdate.ucc.service.PermissionService;
import com.abupdate.ucc.util.HttpResponseUtil;
import com.iot.common.data.constant.CommonResult;
import com.iot.common.data.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;



@Service("AuthTokenServiceImpl")
@Primary
public class AuthTokenServiceImpl implements AuthTokenService {

    private static final Logger log = LoggerFactory.getLogger(AuthTokenServiceImpl.class);
    private static final String DEFAULT_ROLE_NAME = "默认角色";

    private final SysCompanyDao companyDao;
    private final SysRolePermissionDao sysRolePermissionDao;
    private final PasswordEncoder passwordEncoder;
    private final SysUserDao sysUserDao;
    private final SysUserRoleDao userRoleDao;
    private final SysRoleDao sysRoleDao;
    private final SysPermissionDao sysPermissionDao;
    private final RedisUtil<?> redisUtil;
    private final PermissionService permissionService;

    public AuthTokenServiceImpl(SysUserDao sysUserDao,
                                SysUserRoleDao sysUserRoleDao,
                                SysCompanyDao companyDao,
                                PasswordEncoder passwordEncoder,
                                RedisUtil<?> redisUtil,
                                SysRolePermissionDao sysRolePermissionDao,
                                SysRoleDao sysRoleDao,
                                SysPermissionDao sysPermissionDao,
                                PermissionService permissionService) {
        this.sysUserDao = sysUserDao;
        this.userRoleDao = sysUserRoleDao;
        this.companyDao = companyDao;
        this.passwordEncoder = passwordEncoder;
        this.redisUtil = redisUtil;
        this.sysRolePermissionDao = sysRolePermissionDao;
        this.sysRoleDao = sysRoleDao;
        this.sysPermissionDao = sysPermissionDao;
        this.permissionService = permissionService;
    }

    public ResultEnum checkCompanyAndAccountValid(UserEntity user) {
        if (!SysUserStatusEnum.NORMAL.getCode().equals(user.getStatus())){
            return ResultEnum.USER_DISABLED;
        }
        //获取企业信息
        CompanyEntity com = companyDao.findByIdAndDeletedIsFalse(user.getCompanyId());
        if(Objects.isNull(com)){
            return ResultEnum.COMPANY_NOT_EXIST;
        }
        if (Objects.equals(com.getStatus(), CompanyStatusEnum.FREEZE.getCode())) {
            log.info("companyCode={}, user login failed: freeze", com.getCompanyCode());
            return ResultEnum.COMPANY_FREEZE_ERR;
        }
        return ResultEnum.SUCCESS;
    }

    @Override
    public void getLoginCaptcha(String uuid, HttpServletResponse response) {
        VerificationCode verificationCode = new VerificationCode();
        //获取验证码图片
        BufferedImage image = verificationCode.getImage(null);
        //获取验证码内容
        String text = verificationCode.getText();
        redisUtil.set(RedisKeyConstant.LOGIN_CAPTCHA+uuid,text,RedisKeyConstant.LOGIN_CAPTCHA_EXPIRE);
        log.info("uuid={}, session-signCode==>[{}]", uuid, text);
        HttpResponseUtil.sendCaptcha(response, image);
        log.info("uuid={}, write captcha code success", uuid);
    }

    @Override
    public CommonResult<Object> login(String username, String password) {
        CommonResult<Object> result;
        var user = sysUserDao.findByUsernameAndDeletedIsFalse(username);
        if (Objects.isNull(user)){
            return CommonResult.failed(ResultEnum.USERNAME_PASSWORD_ERR);
        }
        ResultEnum resultEnum = checkCompanyAndAccountValid(user);
        if ((resultEnum != ResultEnum.SUCCESS)){
            return CommonResult.failed(resultEnum);
        }
        String dePassword = RsaKeyHelper.rsaDecryptPassword(password);
        try{
            //校验登录密码,超过3次失败添加图形验证
            if (!passwordEncoder.matches(dePassword, user.getPassword())) {
                result = CommonResult.failed(ResultEnum.USERNAME_PASSWORD_ERR);
                if (loginFailedOverTimes(username)) {
                    result.setData(VERIFICATION_CODE);
                }
            }else {
                //获取用户角色
                var userRole = userRoleDao.findUserRoleInfoByUserId(user.getId());
                UserInfo userInfo = new UserInfo(user.getId(), user.getUsername(), userRole.getRoleId(), user.getCompanyId(), user.getCompanyCode(),
                        SysScopeEnum.findScope(userRole.getAdmin()).getValue(),userRole.getRoleLevel());
                String token = JwtToken.signTokenString(userInfo, SystemConstant.SYS_JWT_TOKEN_ISSUER);
                //获取权限
                permissionService.getPermissionsByRoleId(userRole.getRoleId());
                //存入缓存
                int cacheTokenCode = Objects.hashCode(token) & Integer.MAX_VALUE;
                redisUtil.set(RedisKeyConstant.SYS_USER_LOGIN_DATA_PREFIX+userInfo.username(), String.valueOf(cacheTokenCode),RedisKeyConstant.SYS_USER_LOGIN_DATA_PREFIX_EX);
                Map<String, String> tokenMap = new HashMap<>();
                tokenMap.put("token", token);
                result = CommonResult.success(tokenMap);
                clearLoginFailed(user.getUsername());
            }
        }catch (Exception e){
            e.printStackTrace();
            result = CommonResult.failed(ResultEnum.USER_CUSTOM_LOGIN_ERR);
        }
        return result;
    }

    @Override
    public CommonResult<Object> logout() {
        String username = UserInfoContext.getUsername();
        //清除缓存信息
        if(StringUtils.hasText(username))
           redisUtil.remove(RedisKeyConstant.SYS_USER_LOGIN_DATA_PREFIX+username);
        return CommonResult.success();
    }

    private boolean loginFailedOverTimes(String name) {
        String key = RedisKeyConstant.LOGIN_FAILED_USERNAME + name;
        Long count = 1L;
        if (Boolean.FALSE.equals(redisUtil.setIfAbsent(key,count,RedisKeyConstant.LOGIN_FAILED_TIME))) {
            count = redisUtil.incrementAndExpire(key, RedisKeyConstant.LOGIN_FAILED_TIME);
        }
        log.info("login failed username={}, count={}", name, count);
        return Objects.nonNull(count) && count >= PASSWORD_ERROR_TIMES;
    }

    private void clearLoginFailed(String username){
        String usernameKey = RedisKeyConstant.LOGIN_FAILED_USERNAME + username;
        redisUtil.remove(List.of(usernameKey));
    }

    @Override
    @PostConstruct
    @Transactional(rollbackFor = Exception.class)
    public void createSuperAdminUser() {
        String username = "super@abupdate.com";
        String password = "{bcrypt}$2a$10$AnT9og2EHdd/tcRlxrtgW.BmirlyaKGGwIeuItgdV6RaZUCT/Qn9m";
        UserEntity byUsername = sysUserDao.findByUsernameAndDeletedIsFalse(username);
        if (Objects.nonNull(byUsername)) {
            log.trace("super exists");
            return;
        }
        log.info("start init super user");

        UserEntity sysUser = new UserEntity();
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        sysUser.setName(username);
        sysUser.setEmail(username);
        sysUser.setPhone("13723458022");

        CompanyEntity com = new CompanyEntity();
        com.setCompanyName("ABUP");
        com.setCompanyCode("ABUP-CODE");
        com.setCreateUname(username);
        CompanyEntity company = companyDao.save(com);
        sysUser.setCompanyId(company.getId());
        sysUser.setCompanyCode(company.getCompanyCode());
        sysUserDao.save(sysUser);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(DEFAULT_ROLE_NAME);
        roleEntity.setCompanyId(com.getId());
        roleEntity.setCompanyCode(com.getCompanyCode());
        roleEntity.setRoleLevel(RoleLevelEnum.FIRST.getCode());
        roleEntity.setAdmin(null);
        roleEntity.setCreateUname(sysUser.getUsername());
        sysRoleDao.save(roleEntity);

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(sysUser.getId());
        userRoleEntity.setRoleId(roleEntity.getId());
        userRoleDao.save(userRoleEntity);

        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setPermissionKey("user_admin_upload_permissions");
        permissionEntity.setPermissionName("update_permissions");
        permissionEntity.setPermissionType(2);
        permissionEntity.setUri("/api/security/permissions");
        permissionEntity.setDisplayOrder(0);
        permissionEntity.setMethod("PUT");
        
        sysPermissionDao.saveAndFlush(permissionEntity);
        sysRolePermissionDao.saveAndFlush(new RolePermissionEntity(roleEntity.getId(), permissionEntity.getId()));
        log.info("init role permission");
        log.info("init super user success");
    }
}
