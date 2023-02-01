package com.iot.common.dao.company;


import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.iot.common.data.model.bo.page.PageAndSearch;
import com.iot.common.data.model.bo.page.PageData;
import com.iot.common.data.model.vo.company.CompanySupplierItemVo;
import com.iot.common.entity.company.QCompanySupplierEntity;
import com.iot.common.entity.user.UserEntity;
import com.querydsl.core.types.Projections;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

public class CompanySupplierDaoCustomImpl implements CompanySupplierDaoCustom{


    private final EntityManager entityManager;

    private final CriteriaBuilderFactory builderFactory;

    public CompanySupplierDaoCustomImpl(EntityManager entityManager,
                                        CriteriaBuilderFactory builderFactory) {
        this.entityManager = entityManager;
        this.builderFactory = builderFactory;
    }

    @Override
    public PageData<CompanySupplierItemVo> findPageList(String companyCode, PageAndSearch pageAndSearch) {
        QCompanySupplierEntity companySupplierEntity = QCompanySupplierEntity.companySupplierEntity;
        String searchText = pageAndSearch.getSearchText();
        var query = new BlazeJPAQuery<UserEntity>(entityManager, builderFactory).select(
                Projections.bean(CompanySupplierItemVo.class,
                        companySupplierEntity.id,companySupplierEntity.supplierCode,
                        companySupplierEntity.supplierName,companySupplierEntity.remark,
                        companySupplierEntity.createUname,companySupplierEntity.createTime.as("createTimeLocal")
                )
        ).from(companySupplierEntity);
        var predicate = companySupplierEntity.companyCode.eq(companyCode).and(companySupplierEntity.deleted.isFalse());
        if (StringUtils.hasText(searchText)){
            predicate = predicate.and(companySupplierEntity.supplierCode.contains(searchText).or(companySupplierEntity.supplierName.contains(searchText)));
        }
        var page = query.where(predicate).orderBy(companySupplierEntity.createTime.desc(), companySupplierEntity.id.desc())
                .fetchPage(pageAndSearch.getPageNumber()*pageAndSearch.getPageSize(), pageAndSearch.getPageSize());
        return PageData.of(page, page.getTotalPages(), page.getTotalSize(), page.getPage());
    }
}
