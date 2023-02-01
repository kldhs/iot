package com.iot.common.data.constant;

public class ModelConstants {

    public static final String SCHEMA_PERMISSION = "user_permission";
    public static final String SCHEMA_UPGRADE = "upgrade";

    private ModelConstants() {
    }

    // Generic fields
    public static final String ID_PRIMARY_KEY = "id";
    public static final String UPDATE_UNAME_PROPERTY = "update_uname";
    public static final String CREATE_UNAME_PROPERTY = "create_uname";
    public static final String UPDATE_TIME_PROPERTY = "update_time";
    public static final String CREATE_TIME_PROPERTY = "create_time";
    public static final String DELETE_PROPERTY = "deleted";

    // table example
    public static final String EXAMPLE_COLUMN_FAMILY_NAME = "example";

    /* user_permission start  */

    public static final String SYS_USER_TABLE = "sys_user";
    public static final String SYS_ROLE_TABLE = "sys_role";
    public static final String SYS_USER_ROLE_TABLE = "sys_user_role";
    public static final String SYS_COMPANY_TABLE = "sys_company";
    public static final String SYS_PERMISSION_TABLE = "sys_permission";
    public static final String SYS_ROLE_PERMISSION_TABLE = "sys_role_permission";
    public static final String SYS_USER_LOGIN_LOG_TABLE = "sys_user_login_log";


    public static final String PERMISSION_DATA_STRATEGY_TABLE = "permission_data_strategy";
    public static final String PERMISSION_DATA_ROLE_TABLE = "permission_data_role";
    public static final String PERMISSION_DATA_TASK_TABLE = "permission_data_task";
    public static final String PERMISSION_DATA_WHITELIST_ENTITY = "permission_data_whitelist";

    /* user_permission end  */

    /**
     * 企业关联芯片
     */
    public static final String COMPANY_CHIP_MODEL_TABLE = "company_chip_model";
    /**
     * 企业关联芯片
     */
    public static final String COMPANY_SUPPLIER_TABLE = "company_supplier";

    /* device chip*/
    public static final String CHIP_MANUFACTURER_TABLE = "chip_manufacturer";

    public static final String CHIP_MODEL_TABLE = "chip_model";
    public static final String TEST_MODEL_TABLE = "test_model";
    public static final String CHIP_SERIES_TABLE = "chip_series";

    /**
     * product
     */
    public static final String TABLE_PRODUCT_INDUSTRY = "product_industry";
    public static final String TABLE_PRODUCT_TYPE = "product_type";
    //brand
    public static final String TABLE_COMPANY_BRAND = "company_brand";
    public static final String TABLE_PRODUCT_INFO = "product_info";
    /**
     * 物模型
     */
    public static final String PRODUCT_THING_MODEL = "thing_model";
    /**
     * 物模型，产品类型关联表
     */
    public static final String PRODUCT_TYPE_THING_MODEL = "product_type_thing_model";

    /**
     * 设备，生产订单
     */
    public static final String PRODUCE_ORDER = "produce_order";
    public static final String DEVICE_SN = "device_sn";
    public static final String DEVICE_INFO = "device_info";
    public static final String DEVICE_GROUP = "device_group";
    public static final String DEVICE_GROUP_JOIN = "device_group_join";
    public static final String DEVICE_BATCH_DETAIL = "device_batch_detail";
    public static final String DEVICE_TEMPORARY = "device_temporary";

    public static final String DEVICE_LOG_UPGRADE_REPORT_HISTORY = "device_log_upgrade_report_history";
    public static final String DEVICE_LOG_PART_REPORT_HISTORY = "device_log_part_report_history";
    public static final String DEVICE_LOG_PART_RESULT = "device_log_part_result";
    public static final String DEVICE_LOG_NETWORK = "device_log_network";
    public static final String DEVICE_LOG_CHECK_PARAMS = "device_log_check_params";
    public static final String DEVICE_LOG_UPGRADE_RESULT = "device_log_upgrade_result";
    public static final String DEVICE_PART_VERSION_HISTORY = "device_part_version_history";
    public static final String DEVICE_PART = "device_part";
    public static final String DEVICE_ALARM_HISTORY = "device_alarm_history";

    /**
     * MQTT
     */
    public static final String MQTT_USER = "mqtt_user";
    public static final String MQTT_ACL = "mqtt_acl";

    /**
     * upgrade
     */
    public static final String UPGRADE_FIRMWARE_VERSION = "upgrade_firmware_version";
    public static final String UPGRADE_STRATEGY = "upgrade_strategy";
    public static final String UPGRADE_STRATEGY_PART = "upgrade_strategy_part";
    public static final String UPGRADE_STRATEGY_LANGUAGE = "upgrade_strategy_language";
    public static final String UPGRADE_DEVICE_UPLOAD_ERROR_LOG = "upgrade_device_upload_error_log";

    public static final String UPGRADE_TASK_TABLE = "upgrade_task";
    public static final String UPGRADE_TASK_DEVICE_LIST_TABLE = "upgrade_task_device_list";
    public static final String UPGRADE_TASK_TEMPORARY_LIST_TABLE = "upgrade_task_temporary_list";

    public static final String UPGRADE_TASK_GRAY_BATCH_TABLE = "upgrade_task_gray_batch";
    public static final String UPGRADE_STRATEGY_TEST_DEVICE = "upgrade_strategy_test_device";
    public static final String PART_INFO = "part_info";
    public static final String PRODUCT_PART = "product_part";

    /**
     * stat
     */
    public static final String STATS_UPGRADE_TASK = "stats_upgrade_task";
    /**
     * file
     */
    public static final String FILE_INFO = "file_info";
    public static final String EXPORT_EXCEL_RECORDS = "file_export_excel_records";

    /**
     * operate log
     */
    public static final String OPERATE_LOG = "operate_log";

    /**
     * timescale table
     */
    public static final String TS_KV_DICT = "ts_kv_dict";

    public static final String TS_KV = "ts_kv";
    public static final String TS_KV_LATEST = "ts_kv_latest";
    public static final String TS_EVENT = "ts_event";
    public static final String TS_SERVICE = "ts_service";

    public static final String KV_BOOL_VALUE = "bool_v";
    public static final String KV_STRING_VALUE = "string_v";
    public static final String KV_INT_VALUE = "int_v";
    public static final String KV_LONG_VALUE = "long_v";
    public static final String KV_FLOAT_VALUE = "float_v";
    public static final String KV_DOUBLE_VALUE = "double_v";
    public static final String KV_JSON_VALUE = "json_v";

    /**
     * notice
     */
    public static final String NOTICE_INFO_TABLE = "notice_info";
    public static final String NOTICE_COMPANY_LINK_TABLE = "notice_company_link";
    public static final String NOTICE_USER_READ_TABLE = "notice_user_read";

    /**
     * feedback
     */
    public static final String FEEDBACK_TABLE = "feedback";

    //********************** notification ****************************
    public static final String NOTIFICATION = "notification";

    //********************** trigger *********************************
    public static final String SCENE_TASK_INFO = "scene_task_info";
    public static final String SCENE_JOB_INFO = "scene_job_info";
    public static final String SCENE_TRIGGER_INFO = "scene_trigger_info";

    public static final String SCENE_EXECUTE_HISTORY = "scene_execute_history";
    public static final String NOTIFICATION_ALARM = "alarm";
    public static final String SCENE_TASK_LOG = "scene_task_log";

    //********************** push ************************************
    public static final String MESSAGE_PUSH = "message_push";
    public static final String MESSAGE_PUSH_HISTORY = "message_push_history";
}
