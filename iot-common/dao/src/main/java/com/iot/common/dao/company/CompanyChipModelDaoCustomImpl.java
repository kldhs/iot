package com.iot.common.dao.company;


import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.iot.common.data.model.bo.page.PageData;
import com.iot.common.data.model.dto.company.CompanyChipSearchDto;
import com.iot.common.data.model.vo.company.CompanyChipModelVo;
import com.iot.common.entity.chip.QChipManufacturerEntity;
import com.iot.common.entity.chip.QChipModelEntity;
import com.iot.common.entity.chip.QChipSeriesEntity;
import com.iot.common.entity.company.QCompanyChipModelEntity;
import com.iot.common.entity.user.UserEntity;
import com.querydsl.core.types.Projections;

import javax.persistence.EntityManager;
import java.util.Objects;

public class CompanyChipModelDaoCustomImpl implements CompanyChipModelDaoCustom{

    private final EntityManager entityManager;

    private final CriteriaBuilderFactory builderFactory;

    public CompanyChipModelDaoCustomImpl(EntityManager entityManager,
                                         CriteriaBuilderFactory builderFactory) {
        this.entityManager = entityManager;
        this.builderFactory = builderFactory;
    }


    @Override
    public PageData<CompanyChipModelVo> findCompanyChipList(CompanyChipSearchDto dto) {
        String companyCode = dto.getCompanyCode();
        Integer systemType = dto.getSystemType();
        QCompanyChipModelEntity companyChipModel = QCompanyChipModelEntity.companyChipModelEntity;
        QChipModelEntity chipModel = QChipModelEntity.chipModelEntity;
        QChipManufacturerEntity chipManufacturer = QChipManufacturerEntity.chipManufacturerEntity;
        QChipSeriesEntity chipSeries = QChipSeriesEntity.chipSeriesEntity;

        var query = new BlazeJPAQuery<UserEntity>(entityManager, builderFactory).select(
                Projections.bean(CompanyChipModelVo.class,
                        companyChipModel.id,chipModel.chipModelName,chipModel.chipSeriesId,
                        chipSeries.seriesName,chipManufacturer.manufacturerName,companyChipModel.systemType,
                        companyChipModel.createUname,companyChipModel.createTime.as("createTimeDb")
                )
        ).from(companyChipModel)
                .leftJoin(chipModel).on(chipModel.id.eq(companyChipModel.chipModelId))
                .leftJoin(chipManufacturer).on(chipManufacturer.id.eq(chipModel.chipManufacturerId))
                .leftJoin(chipSeries).on(chipSeries.id.eq(chipModel.chipSeriesId));

        var predicate = companyChipModel.deleted.isFalse().and(chipModel.deleted.isFalse()).and(chipManufacturer.deleted.isFalse())
                .and(chipSeries.deleted.isFalse());
        predicate = predicate.and(companyChipModel.companyCode.eq(companyCode));
        if (Objects.nonNull(systemType)){
            predicate = predicate.and(companyChipModel.systemType.eq(systemType));
        }
        var page = query.where(predicate).orderBy(companyChipModel.id.desc()).fetchPage(dto.getOffset(), dto.getPageSize());
        return PageData.of(page, page.getTotalPages(), page.getTotalSize(), page.getPage());
    }
}
