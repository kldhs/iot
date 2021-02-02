package com.iot.common.data.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UpdatePhoneDTO", title = "变更手机号dto")
public class UpdatePhoneDto {

    @Schema(title = "登录密码")
    private String password;

    @Schema(title = "用户名")
    private String username;
    
    @Schema(title = "新手机号")
    private String newPhone;
}
