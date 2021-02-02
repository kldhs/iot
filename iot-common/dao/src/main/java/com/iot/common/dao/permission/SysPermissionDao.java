package com.iot.common.dao.permission;

import com.iot.common.dao.base.BaseDao;
import com.iot.common.data.model.dto.security.SysPermissionDto;
import com.iot.common.entity.permission.PermissionEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SysPermissionDao extends BaseDao<PermissionEntity, Long>,SysPermissionDaoCustom {


    List<PermissionEntity> findByIsAdminAndDeletedIsFalse(Boolean isAdmin);

    @Query("SELECT new com.abupdate.ucc.common.data.model.dto.user.SysPermissionDto(e.id,e.pkey,e.permissionName,e.permissionKey," +
            "e.uri,e.method,e.permissionType,e.icon,e.displayOrder,e.isPublic,e.isAdmin,e.visible) from PermissionEntity e where e.deleted is false ")
    List<SysPermissionDto> findAllScope();

    boolean existsByPermissionKeyAndPermissionTypeAndDeletedIsFalse(String permissionKey,Integer permissionType);


    @Query("select count(p.id) from PermissionEntity p where p.pkey = ?2 and p.deleted is false and p.id not in (select rp.permissionId from RolePermissionEntity rp " +
            "left join PermissionEntity d on d.id = rp.permissionId where rp.roleId = ?1 and rp.deleted is false and d.deleted is false and d.pkey = ?2)")
    Integer findCountRoleNotHasPermission(Long roleId,String pk);
}
