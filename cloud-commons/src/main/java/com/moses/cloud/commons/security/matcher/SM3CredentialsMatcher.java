package com.moses.cloud.commons.security.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author HanKeQi
 * @Date 2020/12/26 下午12:59
 * @Version 1.0
 **/
public class SM3CredentialsMatcher extends AbstractCredentialsMatcher {

    @Override
    protected boolean match(AuthenticationToken token, AuthenticationInfo info) {
//        UsernamePasswordWithToken utoken = (UsernamePasswordWithToken) token;
//        String encryptPassword = SM3Util.encrypt(String.valueOf(utoken.getPassword()));
//        String dbPassword = (String) info.getCredentials();
//        if(encryptPassword.equals(dbPassword)){
//            return true;
//        }
        return true;
    }
}
