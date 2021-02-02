package com.iot.common.dao.permission;

import com.iot.common.entity.permission.QPermissionEntity;
import com.iot.common.entity.permission.QRolePermissionEntity;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class SysRolePermissionDaoCustomImpl implements SysRolePermissionDaoCustom {

    private final JPAQueryFactory jqf;

    public SysRolePermissionDaoCustomImpl(JPAQueryFactory jqf) {
        this.jqf = jqf;
    }

    @Override
    public Integer existsRolePermission(Long roleId, String permissionKey) {
        QRolePermissionEntity rolePermissionEntity = QRolePermissionEntity.rolePermissionEntity;
        QPermissionEntity permissionEntity = QPermissionEntity.permissionEntity;
        var predicate = rolePermissionEntity.roleId.eq(roleId).and(permissionEntity.permissionKey.eq(permissionKey));
        return jqf.select(Wildcard.countAsInt).from(rolePermissionEntity).leftJoin(permissionEntity)
                .on(rolePermissionEntity.permissionId.eq(permissionEntity.id))
                .where(predicate).fetchOne();
    }
}
