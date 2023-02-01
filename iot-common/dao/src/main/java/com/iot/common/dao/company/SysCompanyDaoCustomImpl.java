package com.iot.common.dao.company;


import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.iot.common.data.model.bo.page.PageData;
import com.iot.common.data.model.dto.company.CompanySearchDto;
import com.iot.common.entity.company.CompanyEntity;
import com.iot.common.data.model.vo.company.CompanyVo;
import com.iot.common.entity.company.QCompanyEntity;
import com.iot.common.entity.user.UserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.Objects;

public class SysCompanyDaoCustomImpl implements SysCompanyDaoCustom{

    private final JPAQueryFactory jqf;

    private final EntityManager entityManager;

    private final CriteriaBuilderFactory builderFactory;

    public SysCompanyDaoCustomImpl(JPAQueryFactory jqf,
                                   EntityManager entityManager,
                                   CriteriaBuilderFactory builderFactory) {
        this.jqf = jqf;
        this.entityManager = entityManager;
        this.builderFactory = builderFactory;
    }



    @Override
    public void update(CompanyEntity ce) {
        QCompanyEntity com = QCompanyEntity.companyEntity;
        jqf.update(com).set(com.description,ce.getDescription())
                .set(com.updateUname,ce.getUpdateUname())
                .set(com.updateTime,ce.getUpdateTime()).where(com.id.eq(ce.getId())).execute();
    }

    @Override
    public PageData<CompanyVo> findPageList(CompanySearchDto companySearchDto) {
        Integer status = companySearchDto.getStatus();
        String searchText = companySearchDto.getSearchText();
        QCompanyEntity com = QCompanyEntity.companyEntity;
        var query = new BlazeJPAQuery<UserEntity>(entityManager, builderFactory).select(
                        Projections.bean(CompanyVo.class,
                                com.id,com.companyCode,com.companyName,
                                com.createUname,com.createTime.as("createTimeDb"),com.status,
                                com.description
                        )
                ).from(com);
        var predicate = com.deleted.isFalse();
        predicate = predicate.and(com.id.notIn(1L));
        if(Objects.nonNull(status)){
            predicate = predicate.and(com.status.eq(status));
        }
        if (StringUtils.hasText(searchText)){
            predicate = predicate.and(com.companyName.contains(searchText).or(com.companyCode.contains(searchText)));
        }
        var page = query.where(predicate).orderBy(com.createTime.desc(), com.id.desc())
                .fetchPage(companySearchDto.getPageNumber()*companySearchDto.getPageSize(), companySearchDto.getPageSize());
        return PageData.of(page, page.getTotalPages(), page.getTotalSize(), page.getPage());
    }
}
