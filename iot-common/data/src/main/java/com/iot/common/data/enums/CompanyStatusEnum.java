package com.iot.common.data.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 企业状态
 */
public enum CompanyStatusEnum {

    NORMAL(1, "正常"),
    FREEZE(2,"冻结")
    ;

    @JsonValue
    private final Integer code;
    private final String name;

    CompanyStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
