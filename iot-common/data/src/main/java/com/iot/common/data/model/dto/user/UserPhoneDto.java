package com.iot.common.data.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Schema(name = "UserPhoneDto", title = "手机号")
public class UserPhoneDto {

    @Schema(title = "手机号")
    @NotBlank(message = "手机号不能为空")
    @Length(max = 11, message = "手机号上限11")
    private String phone;

    @Schema(title = "用户id")
    private Long id;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserPhoneDto{" +
                "phone='" + phone + '\'' +
                ", id=" + id +
                '}';
    }
}
