package com.iot.security.controller;

import com.iot.common.data.constant.CommonResult;
import com.iot.common.data.model.dto.security.UserLoginDto;
import com.iot.common.microservice.util.UserInfoContext;
import com.iot.security.service.impl.AuthTokenServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

@RestController
@Validated
@RequestMapping("/auth")
@Tag(name = "登录模块")
@Slf4j
public class AuthTokenController {

    @Resource
    private AuthTokenServiceImpl authTokenService;

    @Operation(summary = "获取图形验证码")
    @Parameter(name = "uuid", in = ParameterIn.QUERY, required = true, description = "验证码编号")
    @GetMapping("/signcode")
    public void getCaptchaCode(@NotBlank(message = "图形验证码id不能为空") String uuid, HttpServletResponse response) {
        authTokenService.getLoginCaptcha(uuid, response);
    }

    @PostMapping(value = "/login")
    @ResponseBody
    @Operation(summary = "登录")
    public CommonResult<?> login(@Validated @RequestBody UserLoginDto userLoginDto) {
        return authTokenService.login(userLoginDto.username(),userLoginDto.password());
    }

    @GetMapping("/logout")
    @Operation(summary = "登出")
    public CommonResult<Object> logout() {
        log.info("user logout userId={}, username={}",UserInfoContext.getUserId(), UserInfoContext.getUsername());
        return authTokenService.logout();
    }

   }
