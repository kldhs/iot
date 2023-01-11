package com.iot.common.feignapi.security;


import com.iot.common.data.constant.CommonResult;
import com.iot.common.data.model.entity.security.UserRoleInfo;
import com.iot.common.data.model.vo.security.DataPermissionInfoVo;
import com.iot.common.data.model.dto.security.SysPermissionDto;
import com.iot.common.feignapi.security.fallback.SecurityApiServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wzh
 */
@FeignClient(value = "security-service", path = "/internal", fallbackFactory = SecurityApiServiceFallback.class)
public interface SecurityApiService {

    /**
     * 根据用户名称获取权限数据
     * @param userName 用户名
     * @return permission data
     */
    @GetMapping("/permission/data/permissions/user")
    CommonResult<DataPermissionInfoVo> getDataPermissions(@RequestParam("userName") String userName);


    /**
     * 根据角色id获取权限数据
     * @param roleId roleId
     * @return permission data
     */
    @GetMapping("/permission/data/permissions")
    CommonResult<DataPermissionInfoVo> getDataPermissions(@RequestParam("roleId") Long roleId);

    /**
     * 添加业务数据时，添加数据权限
     * @param roleId 角色id
     * @param dataId 数据id
     * @param dataType 数据类型(产品/零件...)
     * @param username  操作者username
     * @param companyCode companyCode
     * @return result
     */
    @PostMapping("/permission/data/add")
    CommonResult<Object> addDataPermission(@RequestParam("roleId") Long roleId,
                                           @RequestParam("dataId") Long dataId,
                                           @RequestParam("dataType") Integer dataType,
                                           @RequestParam("username") String username,
                                           @RequestParam("companyCode") String companyCode);


    /**
     * 删除业务数据时，删除数据权限
     * @param roleId 角色id
     * @param dataId 数据id
     * @param dataType 数据类型(产品/零件...)
     * @param userName 操作者username
     * @param companyCode companyCode
     * @return result
     */
    @DeleteMapping("/permission/data")
    CommonResult<Object> deleteDataPermission(@RequestParam(value = "roleId") Long roleId,
                                         @RequestParam(value = "dataId") Long dataId,
                                         @RequestParam(value = "dataType") Integer dataType,
                                         @RequestParam(value = "userName") String userName,
                                         @RequestParam(value = "companyCode") String companyCode);


    /**
     * 根据用户名获取角色信息
     * @param userName 用户名
     * @return UserRoleInfo
     */
    @GetMapping("/user/{userName}/role")
    CommonResult<UserRoleInfo> getUserRoleInfoByUserName(@PathVariable String userName);

    /**
     * 跟新task&strategy权限
     * @param companyCode companyCode
     * @param operateUserName 操作者
     */
    @PostMapping("/permission/data/task-strategy/update")
    CommonResult<Object> updateTaskAndStrategy(@RequestParam("companyCode") String companyCode,
                               @RequestParam("operateUserName") String operateUserName);


    /**
     * 根据role获取权限
     */
    @GetMapping("/role/{roleId}/permissions")
    CommonResult<List<SysPermissionDto>> getPermissionsByRole(@PathVariable Long roleId);

    @GetMapping("/all/permissions")
    CommonResult<List<SysPermissionDto>> getAllPermissions();


}
