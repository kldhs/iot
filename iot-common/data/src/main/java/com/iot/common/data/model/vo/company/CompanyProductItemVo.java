package com.iot.common.data.model.vo.company;

import lombok.Data;

@Data
public class CompanyProductItemVo {

    private Long productId;

    private String productTypeName;

    private String productName;

    private String brandName;

    private Long partCount;

    private Long chipModelCount;

    private Long deviceCount;
}
