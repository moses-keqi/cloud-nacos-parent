package com.moses.cloud.commons.utils;


import com.moses.cloud.commons.exception.BusinessException;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.security.model.SecurityModel;
import org.apache.shiro.UnavailableSecurityManagerException;

import java.util.UUID;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 上午11:21
 * @Version 1.0
 **/
public final class SecurityUtils {

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    /**
     * 获取当前登陆用户
     * @return
     */
    public static SecurityModel getCurrentUser(){
        SecurityModel user = null;
        try {
            user = (SecurityModel) org.apache.shiro.SecurityUtils.getSubject().getPrincipal();
            if(user == null){
                throw new BusinessException(ExceptionMsg.SECURITY_ERROR__NULL);
            }
        } catch (UnavailableSecurityManagerException e) {
            throw new BusinessException(ExceptionMsg.LOGIN_REQUEST_ERROR);
        }
        return user;
    }

    /**
     * 获取当前登陆用户ID
     * @return
     */
    public static String getCurrentUserId(){
        SecurityModel secUser = getCurrentUser();
        return secUser.getId();
    }

    /**
     * 获取当前登陆账号
     * @return
     */
    public static String getCurrentUsername(){
        SecurityModel secUser = getCurrentUser();
        return secUser.getUsername();
    }

    /**
     * 获取当前登陆人名
     * @return
     */
    public static String getCurrentName(){
        SecurityModel secUser = getCurrentUser();
        return secUser.getFullName();
    }

    public  static String getOrgType(){
        SecurityModel secUser = getCurrentUser();
        return secUser.getOrgType();
    }

    /**
     * 获取设备号
     * @return
     */
    public static String getCurrentClient(){
        SecurityModel secUser = getCurrentUser();
        return secUser.getClient();
    }

    /**
     * 随机密码
     */
    public static String randomPassword(){
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }
}
