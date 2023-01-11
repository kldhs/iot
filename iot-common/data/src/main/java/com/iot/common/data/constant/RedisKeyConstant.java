package com.iot.common.data.constant;

import java.time.Duration;

public class RedisKeyConstant {

    public static final String SYS_USER_LOGIN_DATA_PREFIX = "USER:LOGIN:DATA:";
    public static final Duration SYS_USER_LOGIN_DATA_PREFIX_EX = Duration.ofHours(4);


    //用户登录失败计数
    public static final String LOGIN_FAILED_USERNAME = "login:failed:username:";
    //登录失败过期时间
    public static final Duration LOGIN_FAILED_TIME = Duration.ofMinutes(10);


    //图片验证码
    public static final String LOGIN_CAPTCHA =  "login:auth:sign:code:";
    public static final Duration LOGIN_CAPTCHA_EXPIRE = Duration.ofMinutes(10);

    //角色权限，:roleId
    public static final String ROLE_PERMISSION = "permission:role:";
    public static final Duration ROLE_PERMISSION_EX = Duration.ofHours(2);

    //平台public权限 platform:SysScopeEnum
    public static final String PUBLIC_PERMISSION = "permission:public:platform:";
    public static final Duration PUBLIC_PERMISSION_EX = Duration.ofHours(2);

    //平台所有权限 not public all:sysScopeEnum
    public static final String PLATFORM_ALL_PERMISSION =  "permission:platform:all:";
    public static final Duration PLATFORM_ALL_PERMISSION_EX = Duration.ofHours(2);

    //平台所有权限 all:sysScopeEnum
    public static final String ALL_PERMISSIONS =  "permission:all";
    public static final Duration ALL_PERMISSIONS_EX = Duration.ofHours(24);


    //文件上传数量、文件上传token、上传后的文件信息
    public static final String FILE_UPLOAD_COUNT_PREFIX = "file:upload:count";
    public static final String FILE_UPLOAD_TOKEN_PREFIX = "file:upload:token:";


    public static final String UPGRADE_STRATEGY_PRE_DEVICE_ID_KEY = "strategy:preDeviceIdList:";

}
