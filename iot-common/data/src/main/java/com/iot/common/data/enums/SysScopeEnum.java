package com.iot.common.data.enums;

import java.util.List;
import java.util.Objects;

/**
 * 域，与用户角色是否是管理员admin
 */
public enum SysScopeEnum {

    SYS_ADMIN(Boolean.TRUE, "SYS_ADMIN"),
    SYS_CLIENT(Boolean.FALSE, "SYS_CLIENT");

    private final Boolean admin;

    private final String name;

    SysScopeEnum(Boolean admin, String name) {
        this.admin = admin;
        this.name = name;
    }

    public Boolean getValue() {
        return admin;
    }

    public String getName() {
        return name;
    }


    public static SysScopeEnum findScope(Boolean flag){
        if (Objects.isNull(flag))
            return SYS_ADMIN;
        for (SysScopeEnum resultEnum : SysScopeEnum.values()){
            if (resultEnum.getValue().equals(flag)){
                return resultEnum;
            }
        }
        return SYS_CLIENT;
    }

    public static Boolean findIsAdmin(List<String> list){
        for (String str:list){
            if(SYS_ADMIN.getName().equals(str))
                return SYS_ADMIN.getValue();
        }
        return SYS_CLIENT.getValue();
    }

    public static void main(String[] args) {
        System.out.println(findScope(true));
    }
}
