package com.iot.common.data.model.dto.permission;

import javax.validation.constraints.NotNull;

public record PermissionProductDelDto(
        @NotNull(message = "产品id不能为空")
        Long productId,
        @NotNull(message = "角色id")
        Long roleId
) {
}
