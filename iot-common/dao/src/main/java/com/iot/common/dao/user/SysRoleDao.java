package com.iot.common.dao.user;


import com.iot.common.dao.base.BaseDao;
import com.iot.common.data.model.vo.user.RoleVo;
import com.iot.common.entity.user.RoleEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SysRoleDao extends BaseDao<RoleEntity, Long>,SysRoleDaoCustom{

    RoleEntity findByIdAndDeletedIsFalse(Long id);

    RoleEntity findByIdAndCompanyCodeAndDeletedIsFalse(Long id,String companyCode);

    Boolean existsByRoleNameAndCompanyCodeAndDeletedIsFalse(String name,String companyCode);

    Boolean existsByRoleNameAndCompanyCodeAndIdNotAndDeletedIsFalse(String roleName,String companyCode,Long id);

    List<RoleEntity> findByCompanyCodeAndRoleLevelAndDeletedIsFalse(String companyCode,Integer roleLevel);

    List<RoleEntity> findByCompanyIdAndRoleLevelAndDeletedIsFalse(Long companyId,Integer roleLevel);

    Boolean existsByCompanyIdAndRoleLevelAndDeletedIsFalse(Long companyId,Integer roleLevel);

    List<RoleEntity> findByAdminIsNullAndDeletedIsFalse();

    @Query("select new com.iot.common.data.model.vo.user.RoleVo(ro.id,ro.roleName,ro.roleLevel," +
            "ro.companyCode,ro.companyId,co.companyName,ro.createTime)" +
            " from RoleEntity ro left join CompanyEntity co on ro.companyId = co.id " +
            " where ro.id = ?1 and ro.deleted is false and co.deleted is false ")
    RoleVo findRoleDetailById(Long id);

    @Query("SELECT e.id from RoleEntity e where e.deleted is false and e.roleLevel = 1 and e.admin is false")
    List<Long> findAllLevelOneIds();
}
