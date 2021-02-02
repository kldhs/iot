package com.iot.common.data.model.dto.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDto {

    private Long permissionId;

    private String pkey;

    private String permissionKey;

    private String permissionName;

    private Integer permissionType;

    private String method;

    private String uri;

    private String icon;

    private Integer displayOrder;

    private Boolean isPublic;

    private Boolean isAdmin;

    private Boolean visible;
}
