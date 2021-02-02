package com.iot.common.dao.permission;


import com.iot.common.entity.permission.RolePermissionEntity;

import java.util.List;

public interface SysRolePermissionDaoJdbcCustom {

    void batchSave(List<RolePermissionEntity> list);
}
