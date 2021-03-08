package com.moses.cloud.commons.exception;

import lombok.Getter;

/**
 * @Author HanKeQi
 * @Date 2020/12/17 下午7:58
 * @Version 1.0
 **/
public enum ExceptionMsg {

    SUCCESS("0", "成功"),
    ERROR_FAIL("10402", "失败"),
    ERROR_401("401", "权限异常"),

    /**
     * 重新登录
     */
    LOGIN_INVALID_UTOKEN("402", "登录信息已过期，请重新登录"),
    LOGIN_REQUEST_ERROR("402", "系统异常，请重新登录"),
    NULL_UTOKEN("402", "UTOKEN不能为空"),
    INVALID_UTOKEN("402", "无效的UTOKEN"),

    INVALID_URL("404", "无效URL"),

    /**
     * 中台返回没有值的情况,特殊处理
     */
    MIDDLE_NO_DATA_START("404", "没有值"),

    MIDDLE_INVALID_PARAM_START("400", "请求参数无效"),


    SECURITY_ERROR("10001", "用户名/密码输入错误，请重新输入。"),
    SECURITY_ERROR__NULL("10002", "用户名或密码不为空"),
    NOT_TYPE_NULL("10003", "用户类型不能为空"),
    NOT_TYPE_ERROR("1003", "用户类型不正确"),

    INVALID_PARAM("10004", "请求参数无效"),
    UN_ACCESSABLE("10005", "没有权限"),
    INVALID_REQUEST("10006", "请求不合法"),
    INVALID_SIGN("10007", "签名规则不合法"),
    INVALID_REQUEST_URL("10008", "匹配不到合适的URL，可能是GET/POST请求方式或者URL路径不正确"),
    SEND_VALIDATION_ERROR("10012", "发送信息过于频繁"),

    ARITHMETIC_EXCEPTION("20001", "数学运算异常！"),
    SQL_EXCEPTION("20002", "操作数据库异常！"),
    SECURITY_EXCEPTION("20003", "违背安全原则异常！"),

    NO_SUCH_ELEMENT("20007", "找不到对象"),
    ELEMENT_ALREADY_EXISTS("20008", "对象已经存在了"),


    FUTURE_FUNCTION_EXCEPTION("99987", "未完成或未开放的功能或方法"),
    NULL_VALUE_EXCEPTION("99988", "参数不能为空值"),
    IO_EXCEPTION("99989", "IO异常！"),
    JVM_INTERNAL_ERROR("99990", "Java虚拟机发生了内部错误"),
    CONNECT_TIMEOUT("99991", "连接到数据库异常"),
    METHOD_NOT_FOUND("99992", "方法末找到异常！"),
    CLASS_CAST_EXCEPTION("99993", "类型强制转换错误！"),
    ILLEGAL_ARGUMENT_EXCEPTION("99994", "方法的参数错误！"),
    ARRAY_INDEX_OUTOFBOUND("99995", "数组下标越界!"),
    NULL_POINT_EXCEPTION("99996", "发生了空指针异常"),
    CLASS_NOT_FOUND("99997", "程序某个类找不到"),
    BUSINESS_ERROR("99998", "业务逻辑异常"),
    DEFAULT_ERROR("99999", "系统异常，请稍后再试！"),


    //数据字典模块
    DICT_ERROR_001_CODE("30001", "中文名字不能为空"),
    DICT_ERROR_002_CODE("30002", "编码不能为空"),
    DICT_ERROR_003_CODE("30003", "取值项不能为空"),
    DICT_ERROR_004_CODE("30004", "排序不能为空"),
    DICT_ERROR_005_CODE("30005", "是否显示不能为空"),
    DICT_ERROR_006_CODE("30006", "ID不能为空"),
    DICT_ERROR_007_CODE("30007", "编码重复"),
    DICT_ERROR_008_CODE("30008", "有子集无法删除"),

    /**
     * 角色
     */
    ROLE_ERROR_001_CODE("30009", "角色授权码重复"),
    ROLE_ERROR_002_CODE("30010", "角色ID不能为空"),
    ROLE_ERROR_003_CODE("30011", "角色与用户存在绑定关系"),

    //菜单
    MENU_ERROR_001_CODE("30012", "授权码重复"),
    MENU_ERROR_002_CODE("30013", "存在子菜单"),
    MENU_ERROR_003_CODE("30014", "存在关联用户"),
    MENU_ERROR_004_CODE("30015", "存在关联资源"),

    //资源
    RESOURCE_ERROR_001_CODE("30016", "授权码重复"),
    RESOURCE_ERROR_002_CODE("30017", "资源重复"),

    //资源菜单
    MENURESOURCE_ERROR_001_CODE("30018", "资源与菜单存在绑定关系"),



    AGENT_CODE_NULL("40001", "代理人工号不能为空。"),
    //家庭模块
    FAMILY_NAME_NULL("40002", "请完整填写信息。"),
    FAMILY_MAIN_CUSTOMERNO_NULL("40003", "家庭主成员ID不能为空。"),
    FAMILY_CUSTOMERINFO_LIST_NULL("40004", "家庭成员ID列表不能为空。"),
    FAMILY_MATE_COUNT("40005", "关系为配偶只能出现1次。"),
    FAMILY_CHILDREN_COUNT("40006", "关系为子女不超过5次。"),
    FAMILY_PARENT_COUNT("40007", "关系为父母不超过2次。"),
    FAMILY_MEMBER_COUNT("40008", "一个家庭不超过9人。"),
    FAMILY_NAME_COUNT("40009", "  家庭名称不超过20个字。"),
    FAMILY_RELATION_COUNT("40010", "与主成员关系不能超过5个字。"),
    FAMILY_NAME_EXIST("40011", "已存在该名称的家庭，请重新输入。"),
    FAMILY_NULL("40012", "家庭不存在。"),
    FAMILY_RELATION_CODE("40013", "请完整填写信息。"),
    FAMILY_RELATION_CN("40014", "家庭关系只能为汉字。"),
    FAMILY_RELATION_SYSTEM_EXIST("40015", "不能填写配偶、子女、父母，请在下拉框中选择。"),
    //标签
    TAG_NAME("40020", "该标签名字已存在，请重新输入。"),
    TAG_TYPE("40021", "标签类型不存在!"),
    TAG_LENGTH("40022", "标签长度超过20，请重新输入。"),
    TAG_EXIST("40023", "标签不存在或者该标签不属于你!"),
    TAG_NAME_NULL("40024", "标签名称不能为空。"),
    //客户
    CUSTOMER_NOT_EXIST("40030", "客户信息不存在！"),

    //日程
    SCHEDULE_DATE("40040","时间范围选择错误！"),
    SCHEDULE_TIME("40040","开始时间必须小于结束时间！"),
    //消息
    MESSAGE_EXIST("40100","消息不存在!"),
    MESSAGE_NONE("40101","暂无消息"),
    //文件
    FILE_STORAGE_ERROR("40110","文件存储失败"),
    FILE_RED_ERROR("40111","文件读取失败"),
    FILE_UNSUPPORTED_CLASSIFICATION_ERROR("40112","不支持的文件分类"),
    FILE_UNSUPPORTED_CLASSIFICATION_WHITE_LIST_ERROR("40113","文件分类未配置文件类型白名单"),


    //客户备注
    REMARK_NULL("40040","备注的标题、内容不能同时为空"),
    REMARK_TITLE_TOO_LONG("40041","备注标题过长，请重新输入。"),
    REMARK_CONTENT_TOO_LONG("40042","备注内容过长，请重新输入。"),
    REMARK_COUNT_UP("40043","客户备注不能超过十个"),
    ABOUT_VERSION_REPEAT("40044","版本重复"),

    //设备
    DEVICE_NULL("40050","手机设备异常"),

    //首页常用按钮
    COMMONFUNCTION_NOT_EXIST("40051","首页常用按钮ID不存在。"),
    COMMONFUNCTIONTYPE_NOT_EXIST("40052","字典中首页常用按钮类型不存在。");


    @Getter
    private String code;

    @Getter
    private String msg;

    ExceptionMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
