package com.moses.cloud.commons.security;

import com.google.common.collect.Lists;
import com.moses.cloud.commons.security.checker.AuthenticationTokenCheckService;
import com.moses.cloud.commons.security.filter.*;
import com.moses.cloud.commons.security.matcher.AbstractCredentialsMatcher;
import com.moses.cloud.commons.security.matcher.SM3CredentialsMatcher;
import com.moses.cloud.commons.security.realm.TokenAuthorizingRealm;
import com.moses.cloud.commons.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author HanKeQi
 * @Date 2020/12/25 下午10:51
 * @Version 1.0
 **/
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
@ConditionalOnProperty(prefix="shiro.security.token",name="enabled",havingValue="true")
@Slf4j
public class ShiroAutoConfiguration {

    /**
     * shiro bean生命周期管理
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(defaultWebSecurityManager);
        return advisor;
    }

    @Bean
    public TokenAuthorizingRealm tokenAuthorizingRealm(SecurityProperties props){
        TokenAuthorizingRealm realm = new TokenAuthorizingRealm();
        realm.setCredentialsMatcher(getCredentialsMatcher(props));
//		realm.setPreAuthenticationChecks(preAuthenticationChecks);
        // 登陆成功后检查
        List<AuthenticationTokenCheckService> postAuthenticationChecks = Lists.newArrayList();
        realm.setPostAuthenticationChecks(postAuthenticationChecks);
        return realm;
    }



    /**
     * 获取用户凭证匹配器
     * @return
     */
    public CredentialsMatcher getCredentialsMatcher(SecurityProperties props){
        AbstractCredentialsMatcher credentialsMatcher;
        //自定义
        SecurityProperties.CredentialsMatcher customCredentialsMatcher = props.getCredentialsMatcher();
        switch (customCredentialsMatcher) {
            default:
                credentialsMatcher = new SM3CredentialsMatcher();
        }
        // 验证或失败成功后检查
        List<AuthenticationTokenCheckService> postCredentialsMatchFailedChecks = new ArrayList<AuthenticationTokenCheckService>();
        credentialsMatcher.setPostCredentialsMatchFailedChecks(postCredentialsMatchFailedChecks);
        return credentialsMatcher;
    }

    /**
     * 安全管理器
     * @return
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(SecurityProperties props){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(tokenAuthorizingRealm(props));
        class TokenSubjectFactory extends DefaultWebSubjectFactory{
            @Override
            public Subject createSubject(SubjectContext context) {
                // Subject生成策略调整为不生成
                context.setSessionCreationEnabled(false);
                return super.createSubject(context);
            }
        }
        class TokenSubjectDao extends DefaultSubjectDAO{
            /**
             * 设置不存储session
             */
            @Override
            protected boolean isSessionStorageEnabled(Subject subject) {
                return false;
            }

            @Override
            protected void saveToSession(Subject subject) {
                super.saveToSession(subject);
            }
        }
        manager.setSubjectFactory(new TokenSubjectFactory());
        manager.setSessionManager(defaultSessionManager());
        // ((DefaultSessionStorageEvaluator)((DefaultSubjectDAO)manager.getSubjectDAO()).getSessionStorageEvaluator()).setSessionStorageEnabled(false);;
        manager.setSubjectDAO(new TokenSubjectDao());
        return manager;
    }

    /**
     * 会话管理器
     * @return
     */
    @Bean
    public DefaultSessionManager defaultSessionManager(){
        class TokenSessionManager extends DefaultWebSessionManager{
            @Override
            public Serializable getSessionId(SessionKey key) {
                return super.getSessionId(key);
            }
            /**
             * 重写获取sessionId规则，从请求头中获取 （意义转化 sessionId 即为 utoken）
             */
            @Override
            protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
                String utoken = TokenUtils.getUToken(); //从请求头获取UTOKEN
                if(!StringUtils.isEmpty(utoken)){
                    return utoken;
                }
                return null;
            }
        }
        class TokenSessionDao extends EnterpriseCacheSessionDAO{
            /**
             * sessionId（即utoken） 转换成 StatelessSession
             * @param sessionId
             * @return
             */
            @Override
            protected Session doReadSession(Serializable sessionId) {
                StatelessSession session = new StatelessSession(sessionId);
                return session;
            }
        }

        TokenSessionManager tokenSessionManager = new TokenSessionManager();
        tokenSessionManager.setSessionValidationSchedulerEnabled(false);
        tokenSessionManager.setSessionDAO(new TokenSessionDao());
        return tokenSessionManager;
    }


    /**
     * shiro web过滤器
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityProperties props){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(defaultWebSecurityManager(props));

        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("login", new LoginFilter());


        // 登出拦截器
        LogoutFilter logoutFilter =  new LogoutFilter();

        filters.put("logout",logoutFilter);

        //manage
        filters.put("mlogin", new ManageLoginFilter());

        filters.put("user", new UserFilter());
        filters.put("utoken", new UtokenFilter());

        factoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = new  LinkedHashMap<>();
        filterChainDefinitionMap.put(props.getLoginUrl(), "login");
        filterChainDefinitionMap.put(props.getLogoutUrl(), "logout");
        filterChainDefinitionMap.put(props.getManageLoginUrl(), "mlogin");
        filterChainDefinitionMap.put(props.getManageLogoutUrl(), "logout");


        List<String> filterChainDefinitions = props.getFilterChainDefinitions();
        if (!CollectionUtils.isEmpty(filterChainDefinitions)){
            appendToMap(filterChainDefinitionMap, filterChainDefinitions);
        }
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        log.info("########################### [begin] load filterChainDefinitions ###########################");
        for (String key : filterChainDefinitionMap.keySet()) {
            log.info(key+" = "+filterChainDefinitionMap.get(key));
        }
        log.info("########################### [ end ] load filterChainDefinitions ###########################");

        return factoryBean;
    }

    private void appendToMap(Map<String, String> map, List<String> params){
        String regex = "([a-zA-Z0-9-_\\*\\/]+?)=(\\w*)";
        Pattern p = Pattern.compile(regex);
       params.forEach(str->{
           Matcher m = p.matcher(str);
           if (m.find()) {
               map.put(m.group(1), m.group(2));
           }
       });

    }

}
