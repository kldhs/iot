package com.iot.common.data.model.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreateCompanyChipModelDto(
        @Schema(title = "芯片序号")
        @NotNull(message = "芯片序号必填")
        Long chipModelId,
        @Schema(title = "企业编号")
        @NotBlank(message = "企业编号不能为空")
        String companyCode,
        @Schema(title = "操作系统: 1:Linux 2:Android 3:RTOS 4:NonOS  99:OTHER -未定义")
        @NotNull(message = "芯片操作系统")
        Integer systemType
) {
}
