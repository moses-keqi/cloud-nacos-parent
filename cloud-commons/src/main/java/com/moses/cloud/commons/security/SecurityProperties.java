package com.moses.cloud.commons.security;

import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 *
 * security 自定义属性配置
 * @Author HanKeQi
 * @Date 2020/12/25 下午10:52
 * @Version 1.0
 **/
@Data
@ConfigurationProperties(prefix="shiro.security.token")
public class SecurityProperties {

    private boolean enabled = false;


    /** 登陆URL */
    private String loginUrl = "/login";
    /** 登出URL */
    private String logoutUrl = "/logout";

    private String manageLoginUrl = "/mapi/login";

    private String manageLogoutUrl = "/mapi/logout";
    /**
     *  权限拦截  ：/app/**=utoken
     *  权限放行  ：/app/test1 = anon
     *  注：有顺序要求
     */
    private List<String> filterChainDefinitions;
    /** 超时时间 */
    private Long expireTime = 60*60*24*7L;
    /** 加解密令牌 */
    private String secretkey = "JeF8U9wHFOMfs2Y8";
    /** 凭证匹配器 默认为 SM3 国标加密后与库中数据匹配 */
    private CredentialsMatcher credentialsMatcher = CredentialsMatcher.SM3;
    /** token 模式 */
    private Model model = Model.MULTIPLE;

    /**
     * zuul 网关路由与权限前缀映射
     */
    private Map<String, String> zuulRouteMapping = Maps.newConcurrentMap();




    public enum CredentialsMatcher{
        SM3("sm3"),
        LDAP("ldap"),
        MIDDLE("MIDDLE"),
        /** 短信验证码验证*/
        CAPTCHA("captcha");
        private final String value;
        private CredentialsMatcher(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Model{
        /** 一个用户只有一个token */
        SINGLE("single"),
        /** 一个用户有多个token */
        MULTIPLE("multiple");
        private final String value;
        private Model(String value){
            this.value = value;
        }
        public String getValue(){
            return value;
        }
    }
}
