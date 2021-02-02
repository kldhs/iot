package com.iot.common.dao.permission;

import com.iot.common.dao.base.BaseDao;
import com.iot.common.entity.permission.PermissionDataTaskEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface PermissionDataTaskDao extends BaseDao<PermissionDataTaskEntity, Long> {

    @Query("select e.taskId from PermissionDataTaskEntity e where e.deleted is false and " +
            " e.roleId = ?1 ")
    List<Long> findPermissionDataList(Long roleId);

    @Transactional
    @Modifying
    @Query("update PermissionDataTaskEntity e set e.deleted=NULL where e.roleId = ?1")
    void deleteSoftByRoleId(Long roleId);

    List<PermissionDataTaskEntity> findByRoleIdAndDeletedIsFalse(Long roleId);
}
