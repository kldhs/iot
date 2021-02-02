package com.iot.common.data.model.dto.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PermissionProductSearchDto {

    @Schema(title = "角色id")
    @NotNull(message = "角色id不能为空")
    private Long roleId;

    private String searchText;

    @Schema(title = "品牌id",nullable = true)
    private Long brandId;

    @Schema(hidden = true)
    private String companyCode;


}
