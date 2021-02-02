package com.iot.common.dao.user;

import com.iot.common.dao.base.BaseDao;
import com.iot.common.data.model.vo.user.UserDetailVo;
import com.iot.common.data.model.vo.user.UserInfoVo;
import com.iot.common.data.model.vo.user.UserItemVo;
import com.iot.common.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


public interface SysUserDao extends BaseDao<UserEntity, Long>,SysUserDaoCustom{

    UserEntity findByUsernameAndDeletedIsFalse(String userName);

    Boolean existsByUsernameAndDeletedFalse(String username);

    UserEntity findByIdAndDeletedIsFalse(Long id);


    @Query("select new com.abupdate.ucc.common.data.model.vo.UserDetailVo(us.id," +
            "us.username,us.phone,us.createTime,us.companyId,us.companyCode,comp.companyName,us.departmentName," +
            "us.positionName,us.name,us.status,us.email,ro.id,ro.roleLevel,ro.roleName)" +
            " from UserEntity us left join CompanyEntity comp on comp.id = us.companyId " +
            " left join UserRoleEntity usro on usro.userId = us.id " +
            " left join RoleEntity ro on ro.id = usro.roleId " +
            " where us.id = ?1 and us.deleted = false and usro.deleted = false and ro.deleted = false ")
    UserDetailVo findByUserId(Long id);

    @Query("select new com.abupdate.ucc.common.data.model.vo.UserInfoVo(us.id," +
            "us.username,us.phone,us.companyId,us.companyCode,comp.companyName,us.departmentName," +
            "us.positionName,us.name)" +
            " from UserEntity us left join CompanyEntity comp on comp.id = us.companyId " +
            " left join UserRoleEntity usro on usro.userId = us.id " +
            " left join RoleEntity ro on ro.id = usro.roleId " +
            " where us.username = ?1 and us.deleted = false and usro.deleted = false and ro.deleted = false ")
    UserInfoVo findByUserName(String userName);

    Boolean existsByPhoneAndDeletedFalse(String phone);

    Boolean existsByPhoneAndDeletedFalseAndIdNot(String phone,Long id);

    Boolean existsByUsernameOrEmailAndDeletedFalse(String userName,String email);


    @Query("select new com.abupdate.ucc.common.data.model.vo.UserItemVo(us.id," +
            "ro.roleName,ro.roleLevel,ro.companyCode,comp.companyName,us.email,us.status,ull.lastLoginTime,us.createTime)" +
            " from UserEntity us left join CompanyEntity comp on comp.id = us.companyId " +
            " left join UserRoleEntity usro on usro.userId = us.id " +
            " left join RoleEntity ro on ro.id = usro.roleId " +
            " left join UserLoginLogEntity ull on ull.userId = us.id" +
            " where us.companyCode = ?1 and us.deleted = false and usro.deleted = false and ro.deleted = false ")
    Page<UserItemVo> findListByCompany(String companyCode, Pageable pageable);

    Long countByCompanyCodeAndDeletedIsFalse(String companyCode);

    @Query("select us.username" +
            " from UserEntity us left join CompanyEntity comp on comp.id = us.companyId " +
            " left join UserRoleEntity usro on usro.userId = us.id " +
            " left join RoleEntity ro on ro.id = usro.roleId " +
            " where ro.roleLevel=1 and us.companyCode = ?1 and us.deleted = false and usro.deleted = false and ro.deleted = false ")
    String findFirstLevelByCompanyCode(String companyCode);

}
