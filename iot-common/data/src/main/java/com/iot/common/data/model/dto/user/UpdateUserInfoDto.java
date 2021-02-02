package com.iot.common.data.model.dto.user;

public record UpdateUserInfoDto(
        Long userId,

        String departmentName,

        String positionName,

        String name
){


}
