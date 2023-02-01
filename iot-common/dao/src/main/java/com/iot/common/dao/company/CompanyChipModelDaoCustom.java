package com.iot.common.dao.company;


import com.iot.common.data.model.bo.page.PageData;
import com.iot.common.data.model.dto.company.CompanyChipSearchDto;
import com.iot.common.data.model.vo.company.CompanyChipModelVo;

public interface CompanyChipModelDaoCustom {

    PageData<CompanyChipModelVo> findCompanyChipList(CompanyChipSearchDto dto);
}
