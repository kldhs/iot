package com.iot.common.data.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserLoginDto(
        String username,
        String password,
        @Schema(title = "uuid",nullable = true)
        String uuid,
        @Schema(title = "验证码",nullable = true)
        String verificationCode
) {
}
