package com.iot.common.data.model.vo.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyItemVo {

    private Long companyId;

    private String companyCode;

    private String companyName;
}
