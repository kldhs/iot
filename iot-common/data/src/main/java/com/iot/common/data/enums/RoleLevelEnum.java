package com.iot.common.data.enums;


public enum RoleLevelEnum {
    FIRST(1, "一级角色"),
    SECOND(2, "二级角色");
    private final Integer code;
    private final String name;

    RoleLevelEnum(Integer code, String name) {
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
