package com.iot.common.dao.permission;

import com.iot.common.dao.base.BaseDao;
import com.iot.common.data.model.dto.security.SysPermissionDto;
import com.iot.common.entity.permission.RolePermissionEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SysRolePermissionDao extends BaseDao<RolePermissionEntity, Long>,SysRolePermissionDaoCustom,SysRolePermissionDaoJdbcCustom{

    @Query("SELECT new com.abupdate.ucc.common.data.model.dto.user.SysPermissionDto(p.id,p.pkey,p.permissionName,p.permissionKey,p.uri,p.method,p.permissionType,p.icon,p.displayOrder,p.isPublic,p.isAdmin,p.visible)" +
            " FROM PermissionEntity as p where" +
            " p.isPublic is false and " +
            " p.available is null and " +
            " p.deleted is false and p.id in (" +
            "select re.permissionId from RolePermissionEntity as re where " +
            " re.deleted is false and re.roleId =?1)")
    List<SysPermissionDto> findRolePrivatePermissionListByRoleId(Long id);


    /**
     * soft delete
     * @param roleId roleId
     */
    @Transactional
    @Query("update RolePermissionEntity e set e.deleted=NULL where e.roleId=:roleId")
    @Modifying
    void deleteSoftByRoleId(@Param("roleId") Long roleId);

    /**
     * soft delete
     * @param roleId roleId
     */
    @Transactional
    @Query("update RolePermissionEntity e set e.deleted=NULL where e.roleId=:roleId and e.permissionId in :permissionIds")
    @Modifying
    void deleteSoftByRoleIdAndPermissionIds(@Param("roleId") Long roleId,@Param("permissionIds")List<Long> permissionIds);


    List<RolePermissionEntity> findByRoleIdAndDeletedIsFalse(Long roleId);

    @Query("select distinct e.roleId from RolePermissionEntity e left join RoleEntity ro on e.roleId = ro.id " +
            "left join PermissionEntity p on p.id = e.permissionId where e.deleted is false and ro.roleLevel = 2 and p.permissionKey in :pks")
    List<Long> findPkHasPermissionRoleIdsByNewPks(@Param("pks")List<String> pks);
}
