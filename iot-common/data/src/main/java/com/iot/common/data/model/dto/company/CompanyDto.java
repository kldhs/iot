package com.iot.common.data.model.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@Schema(name = "CompanyDto", title = "企业信息")
public class CompanyDto {

    @NotNull(message = "企业id不能为空")
    private Long id;

    @Length(max = 50, message = "最大长度50字符")
    @NotNull(message = "企业名称不能为空")
    private String CompanyName;

    @Length(max = 50, message = "最大长度50字符")
    @NotNull(message = "企业编号不能为空")
    private String CompanyCode;

    @Length(max = 200, message = "最大长度200字符")
    private String describe;
}
