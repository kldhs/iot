package com.iot.common.data.model.dto.permission;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(name = "AddPermissionProductDto", title = "添加产品配置")
public record AddPermissionProductDto(
        @Schema(title = "角色id")
        @NotNull(message = "角色id不能为空")
        Long roleId,

        @Schema(title = "产品id列表")
        List<Long> productList
) {
}
