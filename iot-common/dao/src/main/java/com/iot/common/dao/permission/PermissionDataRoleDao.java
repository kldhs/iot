package com.iot.common.dao.permission;

import com.iot.common.dao.base.BaseDao;
import com.iot.common.entity.permission.PermissionDataRoleEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wzh
 * @date 2022/8/12 16:54
 * content:
 */
public interface PermissionDataRoleDao extends BaseDao<PermissionDataRoleEntity, Long>,PermissionDataRoleDaoCustom {


    @Query("select e.dataId from PermissionDataRoleEntity e where e.deleted is false and " +
            "e.companyCode = ?1 and e.roleId = ?2 and e.dataType = ?3")
    List<Long> findPermissionDataList(String companyCode,Long roleId,Integer type);

    @Transactional
    @Query("update PermissionDataRoleEntity e set e.deleted=NULL where e.roleId=:roleId and e.companyCode=:companyCode and e.dataType=:dataType")
    @Modifying
    void deleteSoftPermissionData(@Param("roleId") Long roleId, @Param("companyCode")String companyCode,@Param("dataType")Integer dataType);

    @Transactional
    @Query("update PermissionDataRoleEntity e set e.deleted=NULL where e.roleId=:roleId and e.companyCode=:companyCode")
    @Modifying
    void deleteAllSoftDataByRoleAndCompanyCode(@Param("roleId") Long roleId,@Param("companyCode")String companyCode);

    @Transactional
    @Query("update PermissionDataRoleEntity e set e.deleted=NULL where e.roleId=:roleId and e.id=:id")
    @Modifying
    void deleteSoftPermissionData(@Param("roleId") Long roleId, @Param("id")Long id);

    List<PermissionDataRoleEntity> findByCompanyCodeAndDataTypeAndDataIdAndDeletedIsFalse(String companyCode,Integer dataType,Long dataId);


    Boolean existsByRoleIdAndIdAndDeletedIsFalse(Long roleId,Long id);

    Boolean existsByRoleIdAndDeletedIsFalse(Long roleId);

    List<PermissionDataRoleEntity> findByRoleIdAndDeletedIsFalse(Long roleId);



}
