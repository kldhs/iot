package com.iot.common.dao.permission;

import com.iot.common.dao.base.BaseDao;
import com.iot.common.entity.permission.PermissionDataWhiteListEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface PermissionDataWhiteListDao extends BaseDao<PermissionDataWhiteListEntity, Long> {


    Boolean existsByCompanyCodeAndRoleIdAndDeletedIsFalse(String companyCode,Long roleId);

    Boolean existsByRoleIdAndDeletedIsFalse(Long roleId);

    /**
     * soft delete
     */
    @Transactional
    @Query("update PermissionDataWhiteListEntity e set e.deleted=NULL where e.roleId=:roleId and e.companyCode=:companyCode")
    @Modifying
    void deleteSoftRoleId(@Param("roleId") Long roleId,@Param("companyCode")String companyCode);

    @Query("select distinct e.roleId from PermissionDataWhiteListEntity e where e.deleted is false ")
    List<Long> findWhiteRoleIds();

    @Query("select distinct e.roleId from PermissionDataWhiteListEntity e where e.companyCode =?1 and e.deleted is false ")
    List<Long> findWhiteRoleIdsByCompanyCode(String companyCode);

    PermissionDataWhiteListEntity findByRoleIdAndDeletedIsFalse(Long roleId);

}
