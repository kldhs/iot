package com.iot.common.data.constant;

public class UserConstant {

    //密码错误次数达到3次，下次需要验证码
    public static final int PASSWORD_ERROR_TIMES = 3;
    //同一用户登录错误5次，锁定用户
    public static final int LOGIN_RESTRICT = 10;

    //需要验证码时data
    public static final String VERIFICATION_CODE = "verificationCode";

    //手机号码最长11位
    public static final int PHONE_LENGTH_LIMIT = 11;

}
