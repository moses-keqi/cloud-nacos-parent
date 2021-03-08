package com.moses.cloud.commons.security.checker;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author HanKeQi
 * @Date 2020/12/26 下午1:49
 * @Version 1.0
 **/
public class LogoutChecker implements AuthenticationTokenCheckService{
    @Override
    public void authenticationTokenCheck(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) throws AuthenticationException {

    }
}
