package com.moses.cloud.commons.security;

import lombok.Getter;

/**
 * @Author HanKeQi
 * @Date 2020/12/26 下午12:25
 * @Version 1.0
 **/
public interface SecurityConstants {

    /**
     * 请求头-UTOKEN
     */
    String HEADER_UTOKEN = "UTOKEN";

    /**
     * 登陆-用户名
     */
    String USERNAME = "username";
    /**
     * 登陆-密码
     */
    String PASSWORD = "password";

    /**
     * 用户类型
     */
    String TYPE = "type";

    String V_CODE = "vCode";


    /**
     * UTOKEN 默认超时时间  7天
     */
    Long UTOKEN_EXPIRE_TIME = 60*60*24*7L;

    /**
     * UTOKEN JWT 默认秘钥KEY
     */
    String UTOKEN_SECRET_KEY = "JeF8U9wHFOMfs2Y8";

    /**
     * 登陆时临时存储的UTOKEN名称
     */
    String REQUEST_TOKEN_PARAMETER_NAME = "utoken";

    /**
     * 终端类型
     */
    String CLIENT = "CLIENT";

    @Getter
    enum ClientType{
        PC("pc"),
        APP("app"),
        H5("h5");

        private String name;

        ClientType(String name) {
            this.name = name;
        }
    }

    /**
     * 追踪Id
     */
    String REQUEST_NO = "REQUESTNO";

    /**
     * 设备号
     */
    String DEVICE_ID = "DEVICEID";


    String PARAM_SIGN = "sign";

    String PARAM_VALUE = "value";

    /**
     *  设备登录是否需要验证码
     * %s 工号
     * %s client 终端类型
     */
    String LOGIN_REDIS_V_CODE_FLAG = "login:redis_v_code_flag:%s:%s";


    /**
     * 设备登录验证码存储
     * %s 工号
     * %s client 终端类型
     */
    String LOGIN_REDIS_V_CODE = "login:redis_v_code:%s:%s";

    /**
     * 登录后是否校验过验证码
     */
    String LOGIN_REDIS_V_CODE_CHECK = "login:redis_v_code_check:%s:%s";

    /**
     * 获取用户请求浏览信息
     */
    String USER_AGENT = "User-Agent";

    /**
     * 内勤
     */
    String IN_SIDE = "INSIDE";

    /**
     * 外勤
     */
    String OUT_SIDE = "OUTSIDE";
}










