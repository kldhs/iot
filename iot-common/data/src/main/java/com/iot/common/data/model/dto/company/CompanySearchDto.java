package com.iot.common.data.model.dto.company;

import com.iot.common.data.model.bo.page.PageAndSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CompanySearchDto extends PageAndSearch {

    @Schema(title ="状态",nullable = true)
    private Integer status;

    @Schema(title ="公司编号",nullable = true)
    private String companyCode;

}
