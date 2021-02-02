package com.iot.common.dao.user;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.iot.common.data.enums.RoleLevelEnum;
import com.iot.common.data.model.bo.page.PageAndSearch;
import com.iot.common.data.model.bo.page.PageData;
import com.iot.common.data.model.dto.user.UserSearchDto;
import com.iot.common.data.model.vo.user.UserItemVo;
import com.iot.common.entity.company.QCompanyEntity;
import com.iot.common.entity.user.*;
import com.querydsl.core.types.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.Objects;

public class SysUserDaoCustomImpl implements SysUserDaoCustom{

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CriteriaBuilderFactory builderFactory;

    public PageData<UserItemVo> findUserListPage(String companyCode, Integer roleLevel, Integer status, Long roleId, PageAndSearch pageAndSearch) {
        var searchText = pageAndSearch.getSearchText();
        QUserEntity userEntity = QUserEntity.userEntity;
        QCompanyEntity companyEntity = QCompanyEntity.companyEntity;
        QRoleEntity roleEntity = QRoleEntity.roleEntity;
        QUserRoleEntity userRoleEntity = QUserRoleEntity.userRoleEntity;
        QUserLoginLogEntity userLoginLogEntity = QUserLoginLogEntity.userLoginLogEntity;
        var query = new BlazeJPAQuery<UserEntity>(entityManager, builderFactory).select(
                Projections.bean(UserItemVo.class,
                        userEntity.id, roleEntity.roleName, roleEntity.roleLevel, userEntity.companyCode,
                        companyEntity.companyName, userEntity.email,userEntity.status,userLoginLogEntity.lastLoginTime.as("lastLoginTimeDb"),userEntity.createTime.as("createTimeDb")
                )
        ).from(userEntity).leftJoin(companyEntity).on(userEntity.companyId.eq(companyEntity.id))
                .leftJoin(userRoleEntity).on(userEntity.id.eq(userRoleEntity.userId))
                .leftJoin(roleEntity).on(userRoleEntity.roleId.eq(roleEntity.id))
                .leftJoin(userLoginLogEntity).on(userEntity.id.eq(userLoginLogEntity.userId));
        var predicate = userEntity.deleted.isFalse().and(roleEntity.deleted.isFalse().and(companyEntity.deleted.isFalse().and(userRoleEntity.deleted.isFalse())));
        if(Objects.nonNull(status)){
            predicate = predicate.and(userEntity.status.eq(status));
        }
        if (StringUtils.hasText(companyCode)){
            //用户端根据companyCode并且只能查询二级角色用户
            predicate = predicate.and(roleEntity.roleLevel.eq(RoleLevelEnum.SECOND.getCode())).and(companyEntity.companyCode.eq(companyCode));
            if (Objects.nonNull(roleId)){
                predicate = predicate.and(roleEntity.id.eq(roleId));
            }
            if(StringUtils.hasText(searchText)){
                predicate = predicate.and(userEntity.email.contains(searchText).or(roleEntity.roleName.contains(searchText)));
            }
        }else {
            if (Objects.nonNull(roleLevel)){
                predicate = predicate.and(roleEntity.roleLevel.eq(roleLevel));
            }
            if(StringUtils.hasText(searchText)){
                predicate = predicate.and(companyEntity.companyName.contains(searchText).or(companyEntity.companyCode.contains(searchText)).or(roleEntity.roleName.contains(searchText)).or(userEntity.email.contains(searchText)));
            }
        }

        var page = query.where(predicate).orderBy(userEntity.createTime.desc(), userEntity.id.desc())
                .fetchPage(pageAndSearch.getPageNumber()*pageAndSearch.getPageSize(), pageAndSearch.getPageSize());
        return PageData.of(page, page.getTotalPages(), page.getTotalSize(), page.getPage());
    }

    @Override
    public PageData<UserItemVo> findUserListPage(UserSearchDto dto) {
        return findUserListPage(null,dto.getRoleLevel(),dto.getStatus(),null,dto);
    }

    @Override
    public PageData<UserItemVo> findUserListPage(String companyCode,UserSearchDto dto) {
        return findUserListPage(companyCode,null,dto.getStatus(),dto.getRoleId(),dto);
    }
}
