package com.iot.common.data.model.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysPermissionDto{

    private Long permissionId;
    private String pkey;
    private String permissionName;
    private String permissionKey;
    private String uri;
    private String method;
    private Integer permissionType;
    private String icon;
    private Integer displayOrder;
    private Boolean isPublic;
    private Boolean isAdmin;
    private Boolean visible;

    public SysPermissionDto(String targetURI, String method) {
        this.uri = targetURI;
        this.method = method;
    }

    public enum SysPermissionTypeEnum {

        SYS_MENU(1, "menu"),
        SYS_BUTTON(2, "button"),
        SYS_MODULE(3, "module");

        private final Integer code;

        private final String name;

        SysPermissionTypeEnum(Integer code, String name) {
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
}
