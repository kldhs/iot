package com.iot.common.data.enums;


import com.iot.common.data.constant.IResult;
import com.iot.common.data.exception.BusinessException;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public enum ResultEnum  implements IResult {
    SUCCESS(10000, "成功"),
    NOT_FOUND(404, "404 Not Found"),

    METHOD_NOT_ALLOWED(10100, "Method Not Allowed"),
    UNKONW_ERROR(10101, "系统异常，请稍后重试"),
    FAILED(10102, "传入参数不合法"),

    ADD_FAILED(10103, "添加失败"),
    DELETE_FAILED(10104, "删除失败"),
    UPDATE_FAILED(10105, "更新失败"),
    SELECT_FAILED(10106, "查询失败"),
    UPLOAD_SUCCESS(10107, "上传成功"),
    UPLOAD_FAILED(10108, "上传失败"),
    PROCEDURE_FAILED(10112, "流程异常"),

    PARAMETER_ERROR(10113, "参数错误"),
    TOKEN_ERROR(10114, "用户身份过期,跳转登录页面"),
    TOKEN_NULL(10124, "The parameter for token is null!"),
    TOKEN_ANALYSE(10125, "解析token异常"),
    CLIENT_ERROR(10126, "客户端调用异常"),
    UNAUTHORIZED(10127, "Unauthorized"),
    USERNAME_PASSWORD_ERR(10128, "用户名或密码错误"),
    USER_NOT_LOGIN(10129, "用户未登录"),
    ACCESS_DENY(10130, "用户无权限访问"),
    VERIFY_CODE_INVALID(10131, "短信验证码错误"),
    TOKEN_AUTH_ERR(10132, "用户获取token失败,请稍后重试!"),
    REFRESH_TOKEN_ERR(10133, "刷新token失败"),

    EMAIL_SEND_FAILED(10134, "邮件发送失败"),
    TOKEN_INVALID_ERR(10135, "无效的token"),

    PARAM_ILLEGAL(10136, "传入参数不合法"),
    SYSTEM_BUSY_ERR(10137, "系统繁忙，请稍后重试"),
    NOT_IMPLEMENTED(10138, "服务未实现"),
    DATETIME_VALUE_ERROR(10139, "时间值错误"),
    FILE_READ_ERR(10140, "读文件错误"),
    EXCEL_FORMAT_ERR(10141, "只支持excel文件，格式为.xls或.xlsx"),
    FILE_NOT_EXISTS(10142, "The file not exists"),
    FILE_TYPE_ERROR(10143, "The file type error"),

    DELETE_FAILED_FIN_ID(10150, "回显失败ID不能存在"),
    UPDATE_FAILED_ID(10151, "编辑失败ID不存在"),
    DELETE_FAILED_ID(10152, "删除失败,ID不存在"),

    //Longer than
    LONGER_THAN(10160, "开始时间超出三年"),
    LONGER_NO_THAN(10161, "开始时间没超出三年"),

    API_SIGN_INVALID(10170, "invalid signature"),

    EMAIL_NOT_CORRECT(10180, "The email is not correct"),
    PHONE_NOT_CORRECT(10181, "The phone number is not correct"),

    /**
     * 用户相关 102xxx
     */
    SYSTEM_USER_NOT_EXIST(10200, "用户不存在!"),
    SYSTEM_USER_OLD_PASSWORD_ERROR(10201, "原密码输入错误!"),
    SYSTEM_USER_PASSWORD_NOT_EQUALLY(10202, "修改失败,新老密码不能一致"),
    SYSTEM_USER_LOGIN_PASSWORD_ERROR(10203, "登录密码输入错误!"),
    SYSTEM_USER_PHONE_ERROR(10204, "手机号校验失败,请输入正确的手机号"),
    SYSTEM_SEND_SMS_UPPER(10205, "今日获取验证码次数已达上限，请明日重试"),
    SYSTEM_SEND_SMS_DISABLE(10206, "短信服务暂不可用"),
    SYSTEM_SEND_SMS_VERIFICATION_ERROR(10207, "手机验证码错误"),
    USER_EMAIL_EXIST(10208, "邮箱已存在"),
    LOGIN_ERROR(10115, "该用户已经在别处登录"),
    SYSTEM_EMAIL_EXIT(10209, "邮箱已存在"),
    SYSTEM_PHONE_EXIT(10210, "手机号已存在"),
    USER_ACTIVE_URL_TIMEOUT(10211, "用户激活链接已失效"),
    USER_EXPIRED(10212, "用户已过期!"),
    USER_DISABLED(10213, "用户已禁用!"),
    VERIFICATION_CODE_ERROR(10214, "验证码错误!"),
    USER_LOCKED(10215, "用户已被锁定"),
    USER_NOT_ACTIVATE(10216, "用户未激活"),
    PASSWORD_FORMAT_ERROR(10217, "密码必须至少包含英文、数字或字符两种"),
    PASSWORD_REPEAT4(10218, "密码不能包含4位连续相同的字符"),
    PASSWORD_CONTINUOUS(10219, "密码不能包含4位连续递增或连续递减的数字或字母"),
    PASSWORD_LENGTH_ERROR(10220, "密码长度须在8~16个字符之间"),
    USER_CUSTOM_LOGIN_ERR(10221, " 用户登录失败"),
    CHANGE_FAIL(10222, "操作失败,每个月可变更1次!"),
    USER_DELTA_PERMISSION_CREATE_ERR(10223, "用户差分权限创建失败"),

    ADD_USER_FIRST_ACCOUNT_NOT_EXIST(10224, "请先创建一级账户"),
    USER_FIRST_ACCOUNT_EXIST(10225, "已存在一级账户"),
    SYSTEM_USERNAME_EXIT(10226, "用户名已存在"),
    PASSWORD_SAME_TO_OLD_ERROR(10227, "不能与原密码相同"),
    PASSWORD_OLD_ERROR(10228, "原密码不正确"),
    PHONE_LENGTH_ERROR(10229, "手机号码不能超过11位"),

    TEMPLATE_DATA_IS_EMPTY(10236, "模板不能为空"),

    //管理端角色相关  104xxx
    SYS_ADMIN_COMPANY_CODE_NOT_EXIT(10401, "管理端的企业code不存在"),
    SYS_FIRST_LEVEL_EXIT(10402, "一级角色信息已存在"),
    SYS_ROLE_NAME_EXIT(10403, "角色名称已存在"),
    SYS_FIRST_LEVEL_NOT_EXIT(10404, "暂无一级角色信息"),
    SYS_ROLE_LEVEL_NOT_EMPTY(10405, "角色级别不能为空"),
    SYS_ROLE_COMPANY_CODE_NOT_EMPTY(10406, "企业code不能为空"),
    SYS_ROLE_COMPANY_ID_NOT_EMPTY(10407,"企业id不能为空"),
    SYS_ROLE_FIRST_NEED_ERROR(10408,"一级角色不存在，需要先添加一级角色"),

    //用户端角色相关  105xxx
    USER_FIRST_LEVEL_EXIT(10501, "暂无一级角色信息"),
    ACCOUNT_COMPANY_CODE_NOT_EXIT(10502, "企业code不存在"),
    ROLE_NOT_EXISTS(10_503, "角色不存在"),
    FIRST_ROLE_NOT_COPY(10504, "一级角色信息无法进行复制"),
    ROLE_DELETE_FAIL(10505, "删除失败,当前角色下存在二级角色信息"),
    ROLE_USER_EXIT(10506, "删除失败,该角色已被使用"),

    // 对象存储相关 11200-11299
    FILE_URL_NOT_EXIST(11200, "文件url获取失败"),

    // 企业管理相关 11300-11399
    COMPANY_NOT_EXIST(11300, "企业信息不存在"),
    COMPANY_CODE_EXIST(11301, "企业编号已存在"),
    COMPANY_LOGOUT_ERR(11302, "企业已注销"),
    COMPANY_FREEZE_ERR(11303, "企业已被冻结"),
    ROLE_NOT_EXIST(11304, "账号原角色不存在"),
    COMPANY_NAME_EXIST(11305, "企业名称已存在"),
    COMPANY_CHIP_EXIST(11306, "企业已关联该芯片"),
    COMPANY_CODE_EMPTY(11307, "企业code不能为空"),
    //品牌
    COMPANY_BRAND_EXISTS(11320, "品牌已存在"),
    COMPANY_BRAND_NOT_EXIST(11321, "品牌不存在"),
    COMPANY_BRAND_USED_DELETE_ERR(11322, "品牌正在使用，无法删除"),
    //供应商
    COMPANY_SUPPLIER_CODE_EXIST(11330, "供应商编码已存在"),
    COMPANY_SUPPLIER_NAME_EXIST(11331, "供应商名称已存在"),
    COMPANY_SUPPLIER_NOT_EXIST(11332, "供应商不存在"),
    COMPANY_SUPPLIER_USED_DELETE_ERR(11333, "供应商已被使用,无法删除"),

    // 任务升级管理
    UPGRADE_TASK_NAME_EXISTS(11410, "任务名称存在"),
    UPGRADE_TASK_NUM_EXISTS_ID(11411, "任务ID不存在"),
    UPGRADE_TASK_NUM_STATUS(11412, "任务状态必须是任务结束或已取消才可以重新配置"),
    UPGRADE_TASK_NUM_ILLEGAL(11413, "请输入x.x.x 格式的任务序号"),
    UPGRADE_TASK_IMPORT_NAME_MISS(11414, "导入名单未找到"),
    UPGRADE_TASK_BATCH_NUM_ILLEGAL(11415, "升级批次数量不合法，最少2个，最多10个"),
    UPGRADE_TASK_BATCH_ILLEGAL(11416, "升级批次不合法"),
    UPGRADE_TASK_BATCH_DEVICE_NUM_ILLEGAL(11417, "任务升级批次设备数不合法"),
    UPGRADE_TASK_BATCH_DEVICE_TOTAL_NUM_DIFF(11418, "批次设备总数不一致"),
    UPGRADE_TASK_NUM_INIT_FAILD(11419, "获取任务序号失败"),
    UPGRADE_TASK_NOT_EXISTS(11420, "任务不存在"),
    UPGRADE_TASK_STATUS_NOT_ALLOW(11421, "任务状态不允许进行此操作"),
    UPGRADE_TASK_TIME_ERROR(11422, "任务开始时间不能大于任务结束时间"),
    UPGRADE_TASK_MAX_VALID_YEAR_ERROR(11423, "任务有效期时间最大不得超过三年"),
    UPGRADE_TASK_STRATEGY_CHANGED(11424, "策略不能修改"),
    UPGRADE_TASK_NEED_TIME_ERROR(11425, "灰度任务，有效期不能小于任务批次所需时间"),
    UPGRADE_TASK_TEMPORARY_ID_EMPTY(11426, "temporaryId不能为空"),
    UPGRADE_TASK_TASK_ID_EMPTY(11427, "taskId不能为空"),
    UPGRADE_TASK_DEVICE_GROUP_ID_EMPTY(11428, "deviceGroupId不能为空"),
    UPGRADE_TASK_NUM_EXISTS(11429, "任务序号存在"),

    //权限相关 11500-11599
    ROLE_ACCESS_DENY_PLATFORM(11500, "角色没有该平台访问权限!"),
    PERMISSION_UPDATE_FAILED(11501, "权限更新失败"),
    PERMISSION_DATA_PRODUCT_EMPTY(11502, "产品不能为空"),
    PERMISSION_DATA_PRODUCT_MAX_LIMIT(11503, "产品已达上限数,请重新选择"),
    PERMISSION_DATA_PART_MAX_LIMIT(11504, "产品已达上限数,请重新选择"),
    PERMISSION_DATA_USERNAME_EMPTY(11505, "用户名称不能为空"),
    PERMISSION_DATA_NOT_EXIST(11506, "角色数据权限不存在"),
    PERMISSION_DATA_ID_EMPTY(11507, "业务id不能为空"),
    PERMISSION_DATA_TYPE_EMPTY(11508, "业务类型不能为空"),

    //产品相关 11600-11699
    //产品行业类型
    PRODUCT_INDUSTRY_EXIST_ERR(11600, "行业类型已存在"),
    PRODUCT_INDUSTRY_NOT_EXISTS(11601, "行业类型不存在"),
    PRODUCT_INDUSTRY_USED_DELETED_FAILED(11602, "删除失败，行业类型已被使用"),
    PRODUCT_INDUSTRY_USED_EDIT_NAME(11603, " 行业类型已被使用，编辑名称失败"),
    //产品类型
    PRODUCT_TYPE_EXISTS(11610, "产品类型已存在"),
    PRODUCT_TYPE_NOT_EXIST(11611, "产品类型不存在"),
    PRODUCT_TYPE_USED_DELETE(11612, "删除失败, 产品类型正在被使用"),

    // 产品
    PRODUCT_EXISTS(11620, "产品已存在"),
    PRODUCT_NET_TYPE_NOT_SUPPORT(11621, "不支持的网络类型"),
    PRODUCT_ENCRYPT_TYPE_NOT_SUPPORT(11622, "不支持的解密方式"),
    PRODUCT_NOT_EXIST(11623, "产品不存在"),
    PRODUCT_ADD_ERROR(11624, "产品新增失败"),
    PRODUCT_HAS_DEVICE(11625, "产品存在未删除设备"),
    PRODUCT_HAS_PART(11626, "产品已关联零件"),
    PRODUCT_HAS_GROUP_ERR(11627, "产品已关联群组"),
    PRODUCT_HAS_STRATEGY_ERR(11628, "产品存在未删除升级策略"),


    //物模型
    THING_MODEL_FUNC_TYPE_NOT_SUPPORT(11640, "功能类型不支持"),
    THING_MODEL_FUNC_ID_EXISTS(11641, "标识符已存在"),
    TM_RW_MODE_ERR(10642, "读写类型错误"),
    TM_PROPERTY_MIN_NOT_NULL(11643, "属性最小值不能为空"),
    TM_PROPERTY_MAX_NOT_NULL(11644, "属性最大值不能为空"),
    TM_PROPERTY_STEP_NOT_NULL(11645, "属性步长不能为空"),
    TM_PROPERTY_UNIT_TOO_LENGTH(11646, "属性单位最大10个字符"),
    TM_PROPERTY_VALUE_AREA_ERR(11647, "属性取值范围不合法"),
    TM_PROPERTY_STEP_ERR(11648, "属性步长值不合法"),
    TM_PROPERTY_ENUM_NOT_NULL(11649, "枚举属性不能为空"),
    TM_PROPERTY_ENUM_ERR(10650, "枚举属性值不合法"),

    TM_PROPERTY_ERR(11651, "属性值不合法"),
    TM_CALL_TYPE_ERR(11652, "服务调用类型错误"),

    TM_PARAM_ERR(11653, "参数错误"),
    TM_PARAM_EXISTS(11654, "参数标识符重复"),
    TM_PARAM_NAME_NOT_NULL(11655, "参数名称不能为空"),
    TM_PARAM_ID_ERR(11656, "参数标识符不合法"),
    TM_PARAM_MIN_NOT_NULL(11657, "属性最小值不能为空"),
    TM_PARAM_MAX_NOT_NULL(11658, "属性最大值不能为空"),
    TM_PARAM_STEP_NOT_NULL(11659, "属性步长不能为空"),
    TM_PARAM_UNIT_NOT_EMPTY(11660, "属性单位不能为空"),
    TM_PARAM_VALUE_AREA_ERR(11661, "属性取值范围不合法"),
    TM_PARAM_STEP_ERR(11662, "属性步长值不合法"),
    TM_PARAM_ENUM_NOT_NULL(11663, "枚举属性不能为空"),
    TM_PARAM_ENUM_ERR(11664, "枚举属性值不合法"),

    TM_EVENT_TYPE_ERR(11665, "事件类型错误"),
    TM_FUNC_ID_ERR(11667, "功能标识符不合法"),
    TM_ATTR_DATA_TYPE_ERR(11668, "数据类型错误"),

    TM_NOT_EXISTS(11670, "物模型不存在"),
    TM_STANDARD_PARAM_ERR(11671, "标准物模型参数错误"),
    TM_FUNC_ID_EXISTS(11672, "模型标识符已存在"),
    TM_NOT_ALLOW_DELETE(11673, "删除失败, 物模型已被使用"),
    TM_NOT_ALLOW_UPDATE(11674, "更新失败, 物模型已被使用"),
    TM_NUMBER_SIZE_ERR(11675, "数值大小不合法"),
    TM_VALUE_FORMAT_ERR(11676, "值格式不合法"),
    TM_NOT_ALLOW_CREATE(11677, "创建失败, 产品下已有设备"),


    INCORRECT_FORMAT(12100, "输入序号格式不正确"),
    TOTAL_LENGTH(12101, "任务序号过长,不能超过100位"),

    /**
     * 消息通知管理
     */
    NOTICE_COMPANY_LIST_EMPTY(11700, "companyIdList不能为空"),
    NOTICE_INFO_NOT_EXIT(11701, "当前消息信息不存在"),
    NOTICE_UPDATE_FAIL(11702, "修改失败,已发布的消息不可更改"),
    NOTICE_VALIDITY_TIME_START_EMPTY(11703, "validityTimeStart不能为空"),
    NOTICE_VALIDITY_TIME_END_EMPTY(11704, "validityTimeEnd不能为空"),
    NOTICE_FORCE_VALID_EMPTY(11705, "forceValid不能为空"),
    NOTICE_FORCE_TIME_START_EMPTY(11706, "forcePushTimeStart不能为空"),
    NOTICE_FORCE_TIME_END_EMPTY(11707, "forcePushTimeEnd不能为空"),
    NOTICE_USER_READ(11708, "当前消息已读"),

    /**
     * 反馈意见管理
     */
    FEEDBACK_NOT_EXISTS(11800, "反馈意见不存在"),
    FEEDBACK_REPLYED(11801, "已回复，请勿重复回复"),

    // 场景联动 11840-11900
    NOTIFICATION_NOT_EXIST(11840, "The notification not exist"),

    NOTIFICATION_NAME_EXIST(11850, "The name already exists"),
    ALARM_LEVEL_NULL_ERR(11851, "The alarm level cannot be null"),
    NOTIFICATION_MODE_ERR(11852, "The notification mode error"),
    NOTIFICATION_PLATFORM_NOT_NULL(11853, "The platform url cannot be empty"),
    NOTIFICATION_MAIL_NOT_NULL(11854, "The mail cannot be empty"),
    NOTIFICATION_PHONE_NOT_NULL(11855, "The phone cannot be empty"),
    TRIGGER_TIMING_SIZE_ERR(11856, "all condition satisfy timing trigger at most 1"),
    NOTIFICATION_CONTENT_NOT_EMPTY(11857, "notification content cannot be empty"),
    TRIGGER_TIMING_DATE_ERR(11858, "trigger valid date error"),
    TRIGGER_TIMING_DAY_TYPE_ERR(11859, "trigger timing day type month or week"),
    TRIGGER_TIMING_PARAM_ERR(11860, "trigger timing param error"),
    TRIGGER_TIMING_TIME_NOT_NULL(11861, "trigger timing time cannot be null"),
    TRIGGER_PRODUCT_NOT_NULL(11862, "trigger product id cannot be null"),
    THING_EVENT_NOT_EXITS(11863, "The thing event not exist"),
    THING_EVENT_PARAM_NOT_EXISTS(11864, "The thing event param not exist"),
    TRIGGER_EVENT_PARAM_VALUE_ERR(11865, "The event param value error"),
    TRIGGER_PROPERTY_NOT_EXIST(11866, "The trigger property not exist"),
    THING_PROPERTY_VALUE_ERR(11867, "The trigger property value error"),
    TRIGGER_OPERATOR_ERR(11868, "The trigger operator not exist"),
    TRIGGER_DEVICE_ON_OFF_ERR(11869, "The device on off line error"),
    TRIGGER_MODE_ERR(11870, "scene trigger mode error"),
    TRIGGER_CONDITION_MODE_ERR(11871, "scene trigger condition mode error"),
    TRIGGER_CONDITION_SIZE_ERR(11872, "trigger condition size error"),
    SCENE_TASK_NOT_EXIST(11873, "The scene task not exist"),
    SCENE_JOB_COUNT_ERR(11874, "The scene jobs count 1 to 20"),
    SCENE_NOTIFICATION_TYPE_ERR(11875, "The scene notification type error"),
    SCENE_JOB_LAST_DELAY_ERR(11876, "The last jobs cannot be delay"),
    SCENE_EXECUTE_DIRECT_ERR(11877, "The scene not one click execute"),
    SCENE_JOB_DELAY_SEC_ERR(11878, "The scene job delay seconds 1 to 18000"),
    SCENE_ONE_CLICK_EXEC_ERR(11879, "The scene not support one-click execute"),
    ALARM_LOG_NOT_EXIST(11880, "The alarm log detail not exist"),

    //设备管理11900-11999
    PRODUCE_ORDER_NOT_EXISTS(11900, "生产订单不存在"),

    DEVICE_NAME_FORMAT_ERR(11916, "The device name error"),
    DEVICE_NAME_TOO_LONG(11917, "The device name too long"),
    PRODUCT_DEVICE_NOT_MATCH(11918, "产品与设备不匹配"),
    DEVICE_NOT_EXIST(11919, "设备不存在"),
    DEVICE_EXISTS_ERR(11920, "设备已存在"),
    DEVICE_DISABLED(11921, "设备已被禁用"),

    DEVICE_GROUP_EXIST_ERROR(11922, "群组名称已存在"),
    DEVICE_GROUP_NOT_EXIST(11923, "群组不存在"),
    DEVICE_ALREADY_IN_GROUP(11924, "设备已经在群组中"),
    DEVICE_NOT_ACTIVATE(11925, "设备未激活"),
    DEVICE_CERT_NOT_EXISTS(11926, "设备证书不存在"),
    DEVICE_NOT_ONLINE(11927, "The device offline, operation failed"),
    DEVICE_CERT_PARSE_ERR(11928, "The device cert parse failed"),

    DEVICE_CERT_CREATE_ERR(11930, "设备证书生成失败"),
    PROPERTY_READ_ONLY_ERR(11931, "不能设置只读属性值"),

    IMPORT_MAX_SIZE_ERR(11940, "最大导入10000行记录"),
    DEVICE_TRANSFER_STRATEGY_ERROR(11950, "操作失败,当前选中的设备存在测试中的策略"),
    DEVICE_TRANSFER_INCLUDE_TEST_ERROR(11951, "操作失败,包含测试设备"),
    DEVICE_TRANSFER_INCLUDE_OFFICAL_ERROR(11952, "操作失败,包含正式设备"),
    DEVICE_TRANSFER_INCLUDE_WAIT_ACTIVATE_ERROR(11953, "操作失败,包含未激活设备"),

    DEVICE_BIND_GROUP_DELETE_ERR(11970, "The device bind group, delete failed"),
    DEVICE_USED_BY_SCENE_ERR(11971, "The device being used by the scene"),
    GROUP_HAS_DEVICE(11972, "群组已添加设备"),
    GROUP_HAS_UPGRADE_TASK(11973, "群组已绑定升级任务"),
    GROUP_HAS_MESSAGE_PUSH(11974, "群组已绑定消息推送任务"),


    //************************** message push 12000-12200 *************************
    PUSH_TYPE_NOT_SUPPORT(12000, "The push type not support"),
    PUSH_UPGRADE_TASK_ERR(12001, "The push upgrade task state error"),
    PUSH_COMMAND_MODE_NOT_SUPPORT(12002, "The push command mode not support"),
    PUSH_DEVICE_NAME_NOT_NULL(12003, "The push deviceName at least one"),
    PUSH_COMMAND_PAYLOAD_NOT_EMPTY(12004, "The command push payload cannot be empty"),
    PUSH_COMMAND_PAYLOAD_LENGTH_ERR(12005, "The command push payload length 1 to 200"),
    PUSH_NAME_EXIST(12006, "The push name exists"),
    PUSH_UPGRADE_TASK_NOT_NULL(12007, "The push upgrade task cannot be null"),
    PUSH_UPGRADE_TASK_LINKED_ERR(12008, "The push upgrade task was linked one"),
    PUSH_NOT_EXISTS(12009, "The push not exists"),
    PUSH_OPERATE_ERR(12010, "The push status cannot operate"),
    PUSH_UPGRADE_TASK_NOT_RELEASED(12011, "The push upgrade task not released"),
    PUSH_TIME_NOT_NULL(12012, "The push time cannot be null"),
    PUSH_TIME_INVALID(12013, "The push time invalid"),
    PUSHING_NOT_DELETE(12014, "The message pushing cannot be delete"),
    PUSH_NOT_SUPPORT_GRAY_TASK(12015, "The push task not support gray upgrade"),
    PUSH_NOT_UPGRADE_TASK(12016, "The push task not upgrade task"),

    /**
     * 零件 221xx 222xx
     */
    E22101(22101, "该零件已关联固件版本，如需删除，请先取消关联"),
    E22102(22102, "该零件已关联产品配置，如需删除，请先取消关联"),
    E22103(22103, "零件名称+零件类型已存在"),
    E22104(22104, "零件不存在"),
    E22105(22105, "该零件已关联内容资源，如需删除，请先取消关联"),
    E22106(22106, "类型编码已存在"),

    /**
     * 产品配置与零件关联相关  222xx
     */
    E22201(22201, "该关系已关联策略,不可删除"),
    E22202(22202, "该关系已关联设备,不可删除"),
    E22203(22203, "关联零件数已达上限：20,新增失败"),
    E22204(22204, "主节点只能存在一个"),
    E22205(22205, "原主节点已关联策略,不可更改"),
    E22206(22206, "当前节点已关联策略,不可更改为主节点"),
    E22207(22207, "当前产品配置已关联设备,节点信息不可修改"),
    E22208(22208, "关联失败,当前产品配置关联零件超出上限"),
    E22209(22209, "该关系已关联资源策略,不可删除"),
    E22210(22210, "原主节点已关联资源策略,不可更改"),
    E22211(22211, "当前节点已关联资源策略,不可更改为主节点"),
    E22212(22212, "关联失败,列表中存在多个相同的零件"),
    E22213(22213, "该关联关系不存在"),

    /**
     * 固定版本相关提示信息 223xx
     */
    E22301(22301, "该版本资源已关联了有效的策略,资源文件不可变更"),
    E22302(22302, "新增失败,单个零件可添加版本超出上限"),
    E22303(22303, "新增失败,单个零件可添加的差分关系超出上限"),
    E22304(22304, "该固件版本不存在"),

    E22501(22501, "图片格式只能为jpg,jpeg,png,svg"),
    E22502(22502, "产品自定义配置数量已达上限"),
    E22503(22503, "自定义配置属性值重复"),
    E22504(22504, "该产品下已存在产品配置，不能删除"),
    E22505(22505, "该产品零件已存在关联，不可重复关联"),
    E22506(22506, "该产品配置已关联零件，不可删除"),
    E22507(22507, "该产品类型已关联产品，不可删除"),
    E22508(22508, "同一品牌下产品名称不可重复"),
    E22509(22509, "产品自定义名称不可重复"),

    /**
     * 厂商/芯片型号相关 E226xx
     */
    E22601(22601, "删除失败,当前芯片型号已有关联企业的信息"),
    E22602(22602, "修改失败,当前厂商已关联芯片信息"),
    E22603(22603, "修改失败,当前芯片型号已有关联企业的信息"),
    E22604(22604, "新增失败,芯片厂商名称已存在"),

    /**
     * 版本管理 301xx
     */
    E30101(30101, "当前版本已关联升级策略，不支持修改，请先在策略中取消关联"),
    E30102(30102, "当前版本已关联升级策略，不支持删除，请先在策略中取消关联"),
    E30103(30103, "该目标版本与源版本存在差分关系，不可重复添加"),
    E30104(30104, "该零件版本已经存在"),
    E30105(30105, "该差分关系已被策略使用，如需删除，请先取消策略"),
    E30106(30106, "请先去上传源包"),
    E30107(30107, "请先去上传目标包"),
    E30108(30108, "请上传差分包"),
    E30109(30109, "当前版本存在差分关系，不可删除"),
    E30110(30110, "暂不支持该文件格式"),
    E30111(30111, "读取文件错误"),
    E30112(20112, "压缩包内的文件最多为5个，最少为1"),
    E30113(30113, "pcf转换失败"),
    E30114(30114, "pcf文件写入错误"),
    E30115(30115, "文件md5 hash计算异常"),
    E30116(30116, "pcf组合写入错误"),
    E30117(30117, "pcf文件转换失败"),
    E30118(30118, "创建文件失败"),
    E30119(30119, "该固件版本不存在"),
    E30120(30120, "加密方式错误"),

    /**
     * 策略配置 305xx
     */
    E30500(30500, "策略名称不存在"),
    E30501(30501, "策略名称已存在"),
    E30502(30502, "策略已被使用,不能删除"),
    E30503(30503, "策略已被使用,不可修改"),
    E30504(30504, "策略状态错误,不可拷贝"),
    E30505(30505, "不存在差分关系"),
    E30506(30506, "参数异常"),
    E30507(30507, "只有新建状态的才能删除"),
    E30508(30508, "策略状态不是待测试，不能绑定车辆"),
    E30509(30509, "请先为零件配置所选语言信息"),
    E30510(30510, "请先为策略配置升级提示语"),
    E30511(30511, "请先为软件包配置多语言"),
    E30512(30512, "请先为软件包配置所选语言信息"),
    E30513(30513, "静默升级和强制升级,需要配置升级时间"),
    E30514(30514, "强制下载参数错误"),
    E30515(30515, "下载网络参数错误"),
    E30516(30516, "下载重试次数参数错误"),
    E30517(30517, "升级方式参数错误"),
    E30518(30518, "升级重试次数参数错误"),
    E30519(30519, "该升级策略不存在"),

    /**
     * 芯片管理 323xx
     */
    E32301(32301, "芯片厂商名称已存在"),
    E32303(32303, "芯片厂商、芯片类型、芯片型号三者唯一"),
    E32304(32304, "该芯片厂商下已存在相同芯片型号"),
    E32305(32305, "上传的c文件和h文件命名错误"),
    E32306(32306, "芯片型号已存在"),
    E32307(32307, "该芯片厂商已关联零件，不可以删除"),
    E32308(32308, "该芯片厂商已关联芯片型号，不可以删除"),
    E32309(32309, "芯片型号不存在"),
    E32310(32310, "芯片不支持该操作系统"),
    E32311(32311, "新增零件失败"),

    /**
     * 文件上传332xx
     */
    E33201(33201, "当前文件上传数目最大值为10"),
    E33202(33202, "文件token已失效"),
    E33203(33203, "临时文件大小上限为10M"),
    E33204(33204, "文件大小上限为3G"),
    E33205(33205, "文件类型未定义"),
    E33206(33206, "产品配置key不能为空"),
    E33207(33207, "fileId对应文件不存在"),
    E33208(33208, "文件不能为空"),

    /**
     * 导出excel333xx
     */
    E33301(33301, "已有一个导出任务进行"),
    E33302(33302, "无数据可导出"),
    E33303(33303, "请选择查询条件，查询相关的设备升级详情"),
    ;

    private final String msg;
    private final Integer code;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultEnum getInstance(Integer code) {
        for (var item : ResultEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        throw new BusinessException(ResultEnum.PARAM_ILLEGAL);
    }

    public static void checkCodeRepeat() {
        var cls = ResultEnum.class;
        var results = cls.getEnumConstants();
        Set<Integer> set = new TreeSet<>();
        for (ResultEnum result : results) {
            if (Objects.isNull(result.getCode()) || set.contains(result.getCode())) {
                throw new IllegalArgumentException("The ResultEnum=" + result + " is illegal or repeat");
            }
            set.add(result.getCode());
        }
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "ResultEnum{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
