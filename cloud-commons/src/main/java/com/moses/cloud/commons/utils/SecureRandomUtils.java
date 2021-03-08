package com.moses.cloud.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;

/**
 * @Author HanKeQi
 * @Date 2021/1/8 下午5:22
 * @Version 1.0
 **/
public class SecureRandomUtils {


    /**
     * 获取6位验证码
     * @return
     */
    public static String getVCode(){
        SecureRandom random = new SecureRandom();
        return StringUtils.leftPad(Integer.toString(random.nextInt((int) Math
                .round(Math.pow(10, 6)))), 6, '0');

    }


    /**
     * 获取几位数字
     * @param num
     * @return
     */
    public static String getVCode(int num){
        SecureRandom random = new SecureRandom();
        return StringUtils.leftPad(Integer.toString(random.nextInt((int) Math
                .round(Math.pow(10, num)))), num, '0');

    }

    public static final String DEF_V_CODE = "123456";

}
