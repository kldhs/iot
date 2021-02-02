package com.iot.common.data.model.dto.user;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;

@Data
public class UserInfoDto {
    private Long userId;

    private String username;

    private String companyCode;

    private Long companyId;

    private Integer roleLevel;

    private HttpServletRequest request;

    private String token;

    private Boolean admin;


    public UserInfoDto() {
    }

}
