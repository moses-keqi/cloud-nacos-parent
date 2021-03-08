package com.moses.cloud.commons.security.checker;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

/**
 *
 * @Author HanKeQi
 * @Date 2020/12/26 下午12:15
 * @Version 1.0
 **/
public interface AuthenticationTokenCheckService {

    /**
     * token 认证检查接口
     * @param authenticationToken
     * @param authenticationInfo
     * @throws AuthenticationException
     */
    void authenticationTokenCheck(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) throws AuthenticationException;

}
