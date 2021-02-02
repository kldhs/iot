package com.iot.common.dao.user;

import com.iot.common.dao.base.BaseDao;
import com.iot.common.entity.security.UserRoleInfo;
import com.iot.common.entity.user.UserRoleEntity;
import org.springframework.data.jpa.repository.Query;


public interface SysUserRoleDao extends BaseDao<UserRoleEntity, Long> {


    @Query("select new com.abupdate.ucc.common.data.model.dto.user.UserRoleInfo(u.userId,u.roleId,ro.admin,ro.roleLevel) from UserRoleEntity u " +
            " left join RoleEntity ro on u.roleId=ro.id" +
            " where u.userId=?1 and u.deleted = false")
    UserRoleInfo findUserRoleInfoByUserId(Long userId);

    UserRoleEntity findByUserIdAndDeletedIsFalse(Long userId);


    Boolean existsByRoleIdAndDeletedIsFalse(Long roleId);

    Boolean existsByRoleIdAndUserIdNotAndDeletedIsFalse(Long roleId,Long userId);
}
