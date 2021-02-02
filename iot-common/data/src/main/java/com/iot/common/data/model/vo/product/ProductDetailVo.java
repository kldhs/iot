package com.iot.common.data.model.vo.product;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ProductDetailVo {
    private Long id;
    private String productName;
    private String productKey;
    private String productSecret;
    private Long deviceCount;

    private Long productTypeId;
    private String productTypeName;
    private Long activateCount;
    private Integer encryptType;
    private String saleArea;
    private OffsetDateTime updateTime;
    private String description;

    private Integer netType;
    private Long onlineCount;
    private String brandCode;
    private OffsetDateTime createTime;
    private String createUname;
    private String fileId;
    private String imageUrl;
    private String fileName;

}
