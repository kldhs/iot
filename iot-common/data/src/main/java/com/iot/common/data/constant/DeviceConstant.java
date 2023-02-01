package com.iot.common.data.constant;

public class DeviceConstant {
    private DeviceConstant() {
    }

    public static final Integer SN_CREATE_SUCCESS = 1;
    public static final Integer SN_CREATE_FAILED = 2;

    //sn激活状态
    //待激活
    public static final Integer SN_WAIT_ACTIVATE = 1;
    //已激活
    public static final Integer SN_ACTIVATED = 2;
    //激活失败
    public static final Integer SN_FAILED_ACTIVATE = 3;

    //************************* 设备属性 ********************************
    //正式设备
    public static final Integer OFFICIAL = 1;
    //测试设备
    public static final Integer TEST = 2;

    //************************** 设备状态 *******************************
    //待激活
    public static final Integer WAIT_ACTIVATE = 1;
    //离线
    public static final Integer OFFLINE = 2;
    //在线
    public static final Integer ONLINE = 3;

    //************************** 设备认证方式 ****************************
    //一机一密
    public static final Integer SECURE_MODE_EACH = 1;
    //一型一密
    public static final Integer SOURCE_MODE_MANY = 2;

    //************************** mqtt auth result **********************
    //allow，认证成功
    public static final String MQTT_AUTH_ALLOW = "allow";

    public static final String MQTT_AUTH_DENY = "deny";

    public static final String MQTT_AUTH_IGNORE = "ignore";

    //************************** mqtt authz result **********************
    //allow
    public static final String MQTT_AUTHZ_ALLOW = "allow";

    public static final String MQTT_AUTHZ_DENY = "deny";

    public static final String MQTT_AUTHZ_IGNORE = "ignore";

    //************************* 设备mqtt连接用户名, $deviceId&$productKey****
    public static final String MQTT_USERNAME_JOINER = "&";
    public static final String MQTT_CLIENT_ID_JOINER = "\\.";

    //无分页查询返回数据条数限制
    public static final int PAGE_SIZE_LIMIT = 100;

    //默认任务Id
    public static final Long DEFAULT_TASK_ID = -1L;

    //**************************** 设备批量导入 *************************


    //**************************** 设备上下线 ***************************
    public static final String CONNECTED_KEY_ID = "connected";
    public static final String DISCONNECTED_KEY_ID = "disconnected";
    //上下线事件类型 0
    public static final Integer CONNECTED_ENTITY_TYPE = 0;

    //***************************** 设备影子 *********************
    //影子key，不能重复
    public static final String SHADOW_KEY_ID = "__shadow_json";
    //影子reported最大字段数
    public static final int SHADOW_MAX_REPORTED_SIZE = 128;

    //shadow operation type
    //device update shadow
    public static final Integer SHADOW_OP_DEVICE_UPDATE = 1;
    //app update shadow
    public static final Integer SHADOW_OP_APP_UPDATE = 2;
    //device clean shadow desired
    public static final Integer SHADOW_OP_DEVICE_CLEAN = 3;
    //device delete shadow reported
    public static final Integer SHADOW_OP_DEVICE_DELETE = 4;


    //************************* common **************************

    public static final Integer DEVICE_NAME_MIN_LENGTH = 4;
    public static final Integer DEVICE_NAME_MAX_LENGTH = 50;

    public static final String DEVICE_NAME_REGEX = "^[a-zA-Z0-9-_.]{4,50}$";

    public static final int DEVICE_DESC_MAXIMUM = 1000;
}
