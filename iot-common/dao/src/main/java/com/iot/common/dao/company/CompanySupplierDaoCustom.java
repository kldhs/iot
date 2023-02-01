package com.iot.common.dao.company;


import com.iot.common.data.model.bo.page.PageAndSearch;
import com.iot.common.data.model.bo.page.PageData;
import com.iot.common.data.model.vo.company.CompanySupplierItemVo;

public interface CompanySupplierDaoCustom {

    /**
     * 分页查询
     */
    PageData<CompanySupplierItemVo> findPageList(String companyCode, PageAndSearch pageAndSearch);
}
