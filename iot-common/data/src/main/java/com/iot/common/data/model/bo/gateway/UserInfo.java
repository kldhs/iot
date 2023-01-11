package com.iot.common.data.model.bo.gateway;

public record UserInfo(
        Long userId,
        Long companyId,
        Long roleId,
        Boolean admin
) {

}
