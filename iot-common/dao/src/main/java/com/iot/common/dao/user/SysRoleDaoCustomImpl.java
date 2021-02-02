package com.iot.common.dao.user;


import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.iot.common.data.enums.RoleLevelEnum;
import com.iot.common.data.model.bo.page.PageData;
import com.iot.common.data.model.dto.user.RoleSearchDto;
import com.iot.common.data.model.vo.user.RoleItemVo;
import com.iot.common.entity.company.QCompanyEntity;
import com.iot.common.entity.user.QRoleEntity;
import com.iot.common.entity.user.RoleEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;


public class SysRoleDaoCustomImpl implements SysRoleDaoCustom{

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CriteriaBuilderFactory builderFactory;

    @Autowired
    private JPAQueryFactory jqf;

    @Override
    public PageData<RoleItemVo> findPageByLevelAndCompanyAndRoleName(Boolean admin, RoleSearchDto roleSearchDto) {
        QRoleEntity roleEntity = QRoleEntity.roleEntity;
        QCompanyEntity companyEntity = QCompanyEntity.companyEntity;
        var query = new BlazeJPAQuery<RoleEntity>(entityManager, builderFactory).select(
                Projections.bean(RoleItemVo.class,
                        roleEntity.id, companyEntity.id.as("companyId"), companyEntity.companyName, roleEntity.roleLevel,
                        roleEntity.roleName, roleEntity.createTime.as("createTimeDb"),roleEntity.description
                )
        ).from(roleEntity).join(companyEntity).on(roleEntity.companyId.eq(companyEntity.id));
        var searchText = roleSearchDto.getSearchText();
        var predicate = roleEntity.deleted.isFalse().and(companyEntity.deleted.isFalse()).and(roleEntity.admin.isFalse());
        predicate = predicate.and(roleEntity.companyId.notIn(1L));
        if (Boolean.TRUE.equals(admin)){
            if (Objects.nonNull(roleSearchDto.getRoleLevel())){
                predicate = predicate.and(roleEntity.roleLevel.eq(roleSearchDto.getRoleLevel()));
            }
            if (StringUtils.hasText(searchText)){
                predicate = predicate.and(roleEntity.roleName.contains(searchText).or(companyEntity.companyName.contains(searchText)));
            }
        }else {
            predicate = predicate.and(companyEntity.companyCode.eq(roleSearchDto.getCompanyCode()))
                    .and(roleEntity.roleLevel.eq(RoleLevelEnum.SECOND.getCode()));
            if (StringUtils.hasText(searchText)){
                predicate = predicate.and(roleEntity.roleName.contains(searchText));
            }
        }
        var page = query.where(predicate).orderBy(roleEntity.createTime.desc(), roleEntity.id.desc())
                .fetchPage(roleSearchDto.getPageNumber()*roleSearchDto.getPageSize(), roleSearchDto.getPageSize());
        return PageData.of(page, page.getTotalPages(), page.getTotalSize(), page.getPage());
    }

    @Override
    public List<Long> findIdsForLevelTwo(String companyCode,List<Long> excludeIds) {
        QRoleEntity roleEntity = QRoleEntity.roleEntity;
        var expression = roleEntity.roleLevel.eq(RoleLevelEnum.SECOND.getCode()).and(roleEntity.deleted.isFalse());
        if (!CollectionUtils.isEmpty(excludeIds)){
            expression = expression.and(roleEntity.id.notIn(excludeIds));
        }
        if (StringUtils.hasText(companyCode)){
            expression = expression.and(roleEntity.companyCode.eq(companyCode));
        }
        return jqf.select(roleEntity.id).from(roleEntity).where(expression).fetch();
    }
}
