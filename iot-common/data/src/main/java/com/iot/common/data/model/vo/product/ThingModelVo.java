package com.iot.common.data.model.vo.product;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class ThingModelVo {

    private Long id;

    private Integer funcType;

    private String funcName;

    private String funcId;

    private JsonNode define;

    private String description;

    private Boolean isStandard;
}
