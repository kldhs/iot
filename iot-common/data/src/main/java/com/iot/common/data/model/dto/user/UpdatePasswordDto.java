package com.iot.common.data.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Schema(name = "用户修改密码dto", title = "UpdatePasswordDto")
@Data
public class UpdatePasswordDto {
    @Schema(title = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(title = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @Schema(title = "原密码")
    @NotBlank(message = "原密码不能为空")
    private String oldPassword;
}
