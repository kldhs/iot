package com.iot.security.service;


import com.iot.common.data.constant.CommonResult;

import javax.servlet.http.HttpServletResponse;

public interface AuthTokenService {

    /**
     * 获取图形验证码
     *
     * @param uuid     code id
     * @param response code
     */
    void getLoginCaptcha(String uuid, HttpServletResponse response);


    /**
     * 用户登录
     *
     * @param username 用户名
     * @return session
     */
    CommonResult<Object> login(String username, String password);


    /**
     * 用户注销
     *
     * @return 注销结果
     */
    CommonResult<Object> logout();


    /**
     * 创建超级管理员
     */
    void createSuperAdminUser();
}
