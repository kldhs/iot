package com.iot.common.data.model.vo.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * content: 产品以及产品配置信息
 */
@Data
@Schema(title = "产品权限配置信息")
public class ProductPermissionInfoVo {
    /**
     * 产品权限配置id
     */
    @Schema(title = "权限配置id",description = "产品配置信息主键")
    private Long permissionDataInfoId;

    @Schema(title = "产品信息id")
    private Long id;

    @JsonProperty("productId")
    private String productKey;
    /**
     * 产品名称
     */
    @Schema(title = "产品名称")
    private String productName;

    @Schema(title = "产品secret")
    private String productSecret;
    /**
     * 品牌名称
     */
    @Schema(title = "品牌名称")
    private String brandName;
    /**
     * 产品类型名称
     */
    @Schema(title = "产品类型名称")
    private String productTypeName;

}
