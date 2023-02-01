package com.iot.common.dao.company;


import com.iot.common.dao.base.BaseDao;
import com.iot.common.entity.company.CompanyChipModelEntity;

import java.util.List;

public interface CompanyChipModelDao extends BaseDao<CompanyChipModelEntity, Long>,CompanyChipModelDaoCustom{


    /**
     * 查询芯片关联企业个数
     */
    Boolean existsByChipModelIdAndDeletedIsFalse(Long chipModelId);

    Boolean existsByChipModelIdAndCompanyCodeAndSystemTypeAndDeletedIsFalse(Long chipModelId,String companyCode,Integer systemType);

    CompanyChipModelEntity findByIdAndDeletedIsFalse(Long id);

    List<CompanyChipModelEntity> findByCompanyCodeAndDeletedIsFalse(String companyCode);


}
