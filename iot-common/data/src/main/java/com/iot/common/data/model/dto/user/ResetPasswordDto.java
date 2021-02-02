package com.iot.common.data.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(name = "用户重置密码dto", title = "ResetPasswordDTO")
public class ResetPasswordDto {
    @NotNull(message = "用户id不能为空")
    @Schema(title = "用户id")
    private Long id;
    @NotBlank(message = "新密码不能为空")
    @Schema(title = "新密码")
    private String newPassword;
}
