package com.moses.cloud.commons.security.realm;

import com.moses.cloud.commons.ApplicationContextHolder;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.security.SecurityConstants;
import com.moses.cloud.commons.security.SecurityProperties;
import com.moses.cloud.commons.security.checker.AuthenticationTokenCheckService;
import com.moses.cloud.commons.security.model.SecurityModel;
import com.moses.cloud.commons.security.token.TokenPrincipalCollection;
import com.moses.cloud.commons.security.token.UsernamePasswordWithToken;
import com.moses.cloud.commons.utils.RedisUtils;
import com.moses.cloud.commons.utils.TokenUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/25 下午11:04
 * @Version 1.0
 **/
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractAuthorizingRealm extends AuthorizingRealm {

    /**
     * 用户认证前置检查
     */
    private List<AuthenticationTokenCheckService> preAuthenticationChecks;

    /**
     * 用户认证后检查
     */
    private List<AuthenticationTokenCheckService> postAuthenticationChecks;

    /**
     * 加载用户权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return doAuthorization(principals);
    }

    protected abstract AuthorizationInfo doAuthorization(PrincipalCollection principals);

    /**
     * 用户凭证验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        AuthenticationInfo authcInfo =this.doAuthenticate(token);

        if (!CollectionUtils.isEmpty(preAuthenticationChecks)) {
            for (AuthenticationTokenCheckService authentication : preAuthenticationChecks) {
                authentication.authenticationTokenCheck(token, authcInfo);
            }
        }
        return authcInfo;
    }

    protected abstract AuthenticationInfo  doAuthenticate(AuthenticationToken token);

    /**
     * 重新父类方法，添加密码验证后的后续验证机制
     */
    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
            throws AuthenticationException {
        CredentialsMatcher cm = getCredentialsMatcher();
        if (cm != null) {
            if (!cm.doCredentialsMatch(token, info)) {
                //not successful - throw an exception to indicate this:
                String msg = ExceptionMsg.SECURITY_ERROR.getMsg();
                throw new IncorrectCredentialsException(msg);
            } else {
                // 登陆成功后生成token
                UsernamePasswordWithToken usernamePasswordWithToken = (UsernamePasswordWithToken) token;
                SimpleAuthenticationInfo simpleAuthenticationInfo = (SimpleAuthenticationInfo) info;
                SecurityModel user = (SecurityModel) simpleAuthenticationInfo.getPrincipals().getPrimaryPrincipal();

                SecurityProperties props = ApplicationContextHolder.getBean(SecurityProperties.class);
                String utoken = TokenUtils.generateToken(
                        user.getId(),
                        props.getExpireTime(),
                        props.getSecretkey(),
                        "moses"
                );

                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                request.setAttribute(SecurityConstants.REQUEST_TOKEN_PARAMETER_NAME, utoken);

                TokenPrincipalCollection tokenPrincipalCollection = new TokenPrincipalCollection();
                user.setPassword(null);
                tokenPrincipalCollection.add(user, getName());
                tokenPrincipalCollection.setUtoken(utoken);
                String key = TokenUtils.setTokenKey(utoken);
                RedisUtils.set(key, tokenPrincipalCollection,props.getExpireTime());

                usernamePasswordWithToken.setToken(utoken);

                log.info("账号登陆成功");

                if (!CollectionUtils.isEmpty(postAuthenticationChecks)) {
                    for (AuthenticationTokenCheckService authenticationTokenCheckService : postAuthenticationChecks) {
                        authenticationTokenCheckService.authenticationTokenCheck(token, info);
                    }
                }
            }
        } else {
            throw new AuthenticationException("A CredentialsMatcher must be configured in order to verify " +
                    "credentials during authentication.  If you do not wish for credentials to be examined, you " +
                    "can configure an " + AllowAllCredentialsMatcher.class.getName() + " instance.");
        }

    }

    /**
     * 重写校验规则，必须完整匹配
     */
    @Override
    public boolean isPermitted(Permission permission, AuthorizationInfo info) {
        Collection<Permission> perms = getPermissions(info);
        if (perms != null && !perms.isEmpty()) {
            for (Permission perm : perms) {
                if (perm.toString().equals(permission.toString())) {
                    return true;
                }
            }
        }
        return false;
    }


}
