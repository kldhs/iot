package com.iot.common.data.constant;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author wzh
 * @date 2022/7/25 17:45
 * content:账户状态
 */
public enum SysUserStatusEnum {

    NORMAL(1,"正常"),
    DISABLE(2,"禁用"),
    EXPIRE(3,"过期");


    @JsonValue
    private final Integer code;

    private final String name;

    SysUserStatusEnum(Integer code, String name) {
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
