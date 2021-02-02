package com.iot.common.data.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "找回密码 -- 用户重置密码dto")
@Data
public class RetrieveResetPasswordDto {
    private String key;
    private String password;

}
