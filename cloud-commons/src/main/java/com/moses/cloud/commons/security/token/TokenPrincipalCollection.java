package com.moses.cloud.commons.security.token;

import lombok.Data;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * @Author HanKeQi
 * @Date 2020/12/26 下午12:07
 * @Version 1.0
 **/
@Data
public class TokenPrincipalCollection extends SimplePrincipalCollection {

    private AuthorizationInfo authorizationInfo;

    private String utoken;
}
