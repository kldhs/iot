package com.iot.common.data.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UpdateEmailDTO", title = "换绑邮箱请求")
public class UpdateEmailDto {
    @Schema(title = "用户名")
    private String username;

    @Schema(title = "密码")
    private String password;

    @Schema(title = "新邮箱")
    private String newEmail;
}
