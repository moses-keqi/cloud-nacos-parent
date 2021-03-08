package com.moses.cloud.commons.security.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author HanKeQi
 * @Date 2020/12/25 下午10:50
 * @Version 1.0
 **/
@Data
public class SecurityModel implements Serializable {

    private String id;

    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String fullName;

    /**
     * 类型
     */
    private String orgType;

    /**
     * 是不是admin 用户  后期manage
     */
    private boolean admin;

    /**
     * 终端
     */
    private String client;

    /**
     * 请求ID
     */
    private String requestNo;

    /**
     * 入职日期
     */
    private Date entryDate;

    /**
     * 头像url
     */
    private String avatarUrl;

    /**
     * 设备号
     */
    private String deviceId;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 是否需要验证码
     */
    private boolean vCode;
}
