package com.iot.common.dao.permission;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.iot.common.data.model.dto.security.SysPermissionDto;
import com.iot.common.entity.permission.QPermissionEntity;
import com.iot.common.entity.user.UserEntity;
import com.querydsl.core.types.Projections;

import javax.persistence.EntityManager;
import java.util.List;

public class SysPermissionDaoCustomImpl implements SysPermissionDaoCustom{

    private final EntityManager entityManager;

    private final CriteriaBuilderFactory builderFactory;

    public SysPermissionDaoCustomImpl(EntityManager entityManager,
                                         CriteriaBuilderFactory builderFactory) {
        this.entityManager = entityManager;
        this.builderFactory = builderFactory;
    }

    @Override
    public List<SysPermissionDto> findAllByIsAdminAndIsPublic(Boolean isAdmin, Boolean isPublic) {
        QPermissionEntity permissionEntity = QPermissionEntity.permissionEntity;
        var predicate = permissionEntity.available.isNull().and(permissionEntity.deleted.isFalse());
        predicate = isAdmin==null?predicate.and(permissionEntity.isAdmin.isNull()):predicate.and(permissionEntity.isAdmin.eq(isAdmin));
        predicate = isPublic==null?predicate.and(permissionEntity.isPublic.isNull()):predicate.and(permissionEntity.isPublic.eq(isPublic));
        return new BlazeJPAQuery<UserEntity>(entityManager, builderFactory).select(
                Projections.bean(SysPermissionDto.class,
                        permissionEntity.id.as("permissionId"),permissionEntity.pkey,
                        permissionEntity.permissionName,permissionEntity.permissionKey,
                        permissionEntity.uri,permissionEntity.method,
                        permissionEntity.permissionType,permissionEntity.icon,
                        permissionEntity.displayOrder,permissionEntity.isPublic,
                        permissionEntity.isAdmin,permissionEntity.visible
                )
        ).from(permissionEntity).where(predicate).fetch();
    }
}
