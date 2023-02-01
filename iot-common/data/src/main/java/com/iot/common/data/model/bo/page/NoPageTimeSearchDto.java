package com.iot.common.data.model.bo.page;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NoPageTimeSearchDto extends NoPageTimeDto {

    private String searchText;
}
