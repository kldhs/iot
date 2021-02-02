package com.iot.common.data.model.vo.product;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardThingModelVo {

    private Long id;

    private Integer funcType;

    private String funcName;

    private String funcId;

    private JsonNode define;

    private String description;

    private Boolean isStandard;

    private Long productTypeId;

    private String productTypeName;

}
