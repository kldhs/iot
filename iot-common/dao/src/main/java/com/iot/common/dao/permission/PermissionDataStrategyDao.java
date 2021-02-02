package com.iot.common.dao.permission;

import com.iot.common.dao.base.BaseDao;
import com.iot.common.entity.permission.PermissionDataStrategyEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PermissionDataStrategyDao extends BaseDao<PermissionDataStrategyEntity, Long> {

    @Query("select e.strategyId from PermissionDataStrategyEntity e where e.deleted is false and " +
            " e.roleId = ?1 ")
    List<Long> findPermissionDataList(Long roleId);

    @Transactional
    @Modifying
    @Query("update PermissionDataStrategyEntity e set e.deleted=NULL where e.roleId = ?1")
    void deleteSoftByRoleId(Long roleId);




    List<PermissionDataStrategyEntity> findByRoleIdAndDeletedIsFalse(Long roleId);
}
