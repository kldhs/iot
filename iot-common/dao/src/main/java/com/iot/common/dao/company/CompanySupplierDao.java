package com.iot.common.dao.company;


import com.iot.common.dao.base.BaseDao;
import com.iot.common.entity.company.CompanySupplierEntity;

import java.util.List;

public interface CompanySupplierDao extends BaseDao<CompanySupplierEntity, Long>,CompanySupplierDaoCustom{

    CompanySupplierEntity findByIdAndDeletedFalse(Long id);

    CompanySupplierEntity findByIdAndCompanyCodeAndDeletedFalse(Long id, String companyCode);

    Boolean existsBySupplierCodeAndCompanyCodeAndDeletedFalse(String supplierCode,String companyCode);

    Boolean existsBySupplierNameAndCompanyCodeAndDeletedFalse(String supplierName,String companyCode);

    Boolean existsBySupplierNameAndCompanyCodeAndIdNotAndDeletedFalse(String supplierName,String companyCode,Long id);

    List<CompanySupplierEntity> findByCompanyCodeAndDeletedFalse(String companyCode);

    //@Query("select new com.abupdate.ucc.common.data.model.vo.CommonSelectVo(c.id, c.supplierName)" +
    //        " from CompanySupplierEntity c where c.companyCode=:companyCode and c.deleted=false order by c.createTime DESC ")
    //List<CommonSelectVo> findByCompanyCodeSelect(String companyCode);
}
