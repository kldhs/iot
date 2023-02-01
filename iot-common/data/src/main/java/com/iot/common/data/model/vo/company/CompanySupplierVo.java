package com.iot.common.data.model.vo.company;

import lombok.Data;

@Data
public class CompanySupplierVo {

    private Long id;
    private String companyCode;
    private String supplierCode;
    private String supplierName;
    private String remark;

}
