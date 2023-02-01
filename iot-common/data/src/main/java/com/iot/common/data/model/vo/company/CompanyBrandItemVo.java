package com.iot.common.data.model.vo.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iot.common.data.constant.DateConstant;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompanyBrandItemVo {
    private Long id;
    private String brandCode;
    private String brandName;
    private String remark;
    private String createUname;
    @JsonFormat(pattern = DateConstant.DATETIME_FORMAT_PATTERN)
    private LocalDateTime createTime;
}
