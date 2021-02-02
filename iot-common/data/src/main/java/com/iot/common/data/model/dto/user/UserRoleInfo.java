package com.iot.common.data.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleInfo {

    private Long userId;

    private Long roleId;

    private Boolean admin;

    private Integer roleLevel;

}
