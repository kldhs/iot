package com.iot.common.data.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Schema(name = "UserEmailDto", title = "邮箱")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEmailDto {

    @NotBlank(message = "邮箱不能为空")
    @Schema(title = "邮箱")
    private String email;
}
