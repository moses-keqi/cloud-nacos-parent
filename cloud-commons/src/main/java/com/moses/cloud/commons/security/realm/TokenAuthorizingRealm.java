package com.moses.cloud.commons.security.realm;

import com.moses.cloud.commons.ApplicationContextHolder;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.security.SecurityProperties;
import com.moses.cloud.commons.security.feign.SecurityClient;
import com.moses.cloud.commons.security.model.SecurityModel;
import com.moses.cloud.commons.security.token.TokenPrincipalCollection;
import com.moses.cloud.commons.security.token.UsernamePasswordWithToken;
import com.moses.cloud.commons.utils.RedisUtils;
import com.moses.cloud.commons.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

/**
 * @Author HanKeQi
 * @Date 2020/12/26 下午12:46
 * @Version 1.0
 **/
@Slf4j
public class TokenAuthorizingRealm extends AbstractAuthorizingRealm {

    /**
     * 限定只支持UsernamePasswrodWithToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordWithToken;
    }

    /**
     * 加载用户权限信息
     */
    @Override
    protected AuthorizationInfo doAuthorization(PrincipalCollection principals) {
        TokenPrincipalCollection tokenPrincipalCollection = (TokenPrincipalCollection) principals;
        SecurityClient securityClient = ApplicationContextHolder.getBean(SecurityClient.class);

        if(tokenPrincipalCollection.getAuthorizationInfo() == null){
            log.info("加载用户资源信息");
            SecurityProperties props = ApplicationContextHolder.getBean(SecurityProperties.class);
            SecurityModel user = (SecurityModel) tokenPrincipalCollection.getPrimaryPrincipal();
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.setRoles(securityClient.findRoleByUserName(user.getUsername()));
            info.setStringPermissions(securityClient.findResourceByUsername(user.getUsername()));

            // 更新入缓存
            tokenPrincipalCollection.setAuthorizationInfo(info);
            String key = TokenUtils.setTokenKey(tokenPrincipalCollection.getUtoken());
            RedisUtils.set(key, tokenPrincipalCollection,props.getExpireTime());
        }

        return tokenPrincipalCollection.getAuthorizationInfo();
    }

    /**
     * 用户凭证验证
     */
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken token) {
        SecurityClient securityClient = ApplicationContextHolder.getBean(SecurityClient.class);
        UsernamePasswordWithToken usernamePasswordWithToken = (UsernamePasswordWithToken) token;

        // 1 验证用户类型不以为空
        if(StringUtils.isEmpty(usernamePasswordWithToken.getType())){
            throw new AccountException(ExceptionMsg.NOT_TYPE_NULL.getMsg());
        }

        // 2 验证用户名与密码是否为空
        if(StringUtils.isEmpty(usernamePasswordWithToken.getUsername()) || usernamePasswordWithToken.getPassword() == null || StringUtils.isEmpty(new String(usernamePasswordWithToken.getPassword())) ){
            throw new AccountException(ExceptionMsg.SECURITY_ERROR__NULL.getMsg());
        }

        // 3 验证用户名和密码
        ResponseData<SecurityModel> responseData = securityClient.findUserByUsernameAndOrgType(usernamePasswordWithToken.getUsername(),usernamePasswordWithToken.getType());
        if(!ExceptionMsg.SUCCESS.getCode().equals(responseData.getCode()) ){
            throw new UnknownAccountException(ExceptionMsg.SECURITY_ERROR.getMsg());
        }
        SecurityModel user = responseData.getData();

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return authenticationInfo;
    }
}
