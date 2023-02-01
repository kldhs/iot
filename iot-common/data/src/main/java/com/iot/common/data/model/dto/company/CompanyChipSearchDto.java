package com.iot.common.data.model.dto.company;

import com.iot.common.data.model.bo.page.PageAndSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CompanyChipSearchDto extends PageAndSearch {

        @Schema(title = "企业编号")
        @NotBlank(message = "企业编号不能为空")
        String companyCode;

        @Schema(title = "系统类型",nullable = true,description = "1:Linux,2:Android,3:RTOS,4:NonOS")
        Integer systemType;
}
