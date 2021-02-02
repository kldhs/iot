package com.iot.common.dao.permission;


public interface SysRolePermissionDaoCustom {

    Integer existsRolePermission(Long roleId,String permissionKey);
}
