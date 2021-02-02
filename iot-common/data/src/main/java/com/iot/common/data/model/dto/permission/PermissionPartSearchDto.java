package com.iot.common.data.model.dto.permission;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PermissionPartSearchDto {

    @Schema(title = "角色id")
    @NotNull(message = "角色id不能为空")
    private Long roleId;

    private String searchText;

    @Schema(title = "供应商id",nullable = true)
    private Long supplierId;

    @Schema(hidden = true)
    private Integer systemType;

    @Hidden
    private String companyCode;


}
