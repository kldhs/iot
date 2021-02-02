package com.iot.common.data.model.dto.permission;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(name = "AddPermissionPartDto", title = "添加零件配置")
public record AddPermissionPartDto(
        @Schema(title = "角色id")
        @NotNull(message = "角色id不能为空")
        Long roleId,

        @Schema(title = "零件id列表")
        List<Long> partList
) {
}
