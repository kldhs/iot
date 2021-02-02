package com.iot.common.data.model.vo.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListVo {
    private Long id;
    private String productName;
    @JsonProperty("productId")
    private String productKey;
    private Long productTypeId;
    private String productTypeName;

    @JsonIgnore
    private LocalDateTime innerCreateTime;
    private OffsetDateTime createTime;

    private Long brandId;
    private String brandName;
    private String productSecret;

    public ProductListVo(Long id, String productName,
                         String productKey, Long productTypeId,
                         String productTypeName, LocalDateTime innerCreateTime) {
        this.id = id;
        this.productName = productName;
        this.productKey = productKey;
        this.productTypeId = productTypeId;
        this.productTypeName = productTypeName;
        this.innerCreateTime = innerCreateTime;
    }

    public ProductListVo(Long id, String productName,
                         String productKey, Long productTypeId,
                         String productTypeName, LocalDateTime innerCreateTime,
                         Long brandId, String brandName,
                         String productSecret) {
        this.id = id;
        this.productName = productName;
        this.productKey = productKey;
        this.productTypeId = productTypeId;
        this.productTypeName = productTypeName;
        this.innerCreateTime = innerCreateTime;
        this.brandId = brandId;
        this.brandName = brandName;
        this.productSecret = productSecret;
    }
}
