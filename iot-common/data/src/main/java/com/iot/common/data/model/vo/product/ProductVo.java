package com.iot.common.data.model.vo.product;

import lombok.Data;

@Data
public class ProductVo {
    private Long id;
    private String productName;
    private Long productTypeId;
    private Integer netType;
    private Integer encryptType;
    private Long brandId;
    private String saleArea;
    private String description;
    private String fileId;
    private String imageUrl;
    private String fileName;
}
