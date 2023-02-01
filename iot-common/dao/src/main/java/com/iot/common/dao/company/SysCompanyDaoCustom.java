package com.iot.common.dao.company;

import com.iot.common.data.model.bo.page.PageData;
import com.iot.common.data.model.dto.company.CompanySearchDto;
import com.iot.common.entity.company.CompanyEntity;
import com.iot.common.data.model.vo.company.CompanyVo;

public interface SysCompanyDaoCustom {

    void update(CompanyEntity company);

    /**
     * 分页查询
     */
    PageData<CompanyVo> findPageList(CompanySearchDto companySearchDto);
}
