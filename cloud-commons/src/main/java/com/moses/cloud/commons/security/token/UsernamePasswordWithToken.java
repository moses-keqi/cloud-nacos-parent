package com.moses.cloud.commons.security.token;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * UsernamePasswordWithToken 扩展新字段
 * @Author HanKeQi
 * @Date 2020/12/26 下午12:05
 * @Version 1.0
 **/
@Data
public class UsernamePasswordWithToken extends UsernamePasswordToken {

    /**
     * token
     */
    private String token;

    /**
     * @see com.moses.cloud.commons.security.SecurityConstants#IN_SIDE
     * @see com.moses.cloud.commons.security.SecurityConstants#OUT_SIDE
     * 用户类型
     */
    private String type;

}
