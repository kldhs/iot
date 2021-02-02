package com.iot.common.dao.permission;

import com.iot.common.data.model.dto.security.SysPermissionDto;

import java.util.List;

public interface SysPermissionDaoCustom {

    List<SysPermissionDto> findAllByIsAdminAndIsPublic(Boolean isAdmin, Boolean isPublic);

}
