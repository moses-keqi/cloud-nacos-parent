package com.moses.cloud.commons.security.filter;

import com.moses.cloud.commons.ApplicationContextHolder;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.security.SecurityConstants;
import com.moses.cloud.commons.security.SecurityProperties;
import com.moses.cloud.commons.utils.ServletUtils;
import com.moses.cloud.commons.utils.TokenUtils;
import com.moses.cloud.commons.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author HanKeQi
 * @Date 2020/12/26 下午2:01
 * @Version 1.0
 **/
@Slf4j
public class UtokenFilter extends UserFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {

        // 1 验证token
        boolean result = super.isAccessAllowed(request, response, mappedValue);
        if(!result){
            return false;
        }
        // 2 鉴权
        HttpServletRequest servletRequest = WebUtils.toHttp(request);
        String servletPath = servletRequest.getServletPath();
        // 授权码
        String permissionCode = "";
        // 微服务-服务路由ID
        String serverId = TokenUtils.getServerId(servletPath);

        // 服务真实请求路径地址
        String realPath = TokenUtils.getRealPath(servletPath);
        SecurityProperties securityProperties = ApplicationContextHolder.getBean(SecurityProperties.class);
        String serverPrefix = null;
        if(!CollectionUtils.isEmpty(securityProperties.getZuulRouteMapping())){
            serverPrefix = securityProperties.getZuulRouteMapping().get(serverId);
            if(StringUtils.isNotBlank(serverPrefix)){
                permissionCode = convertToPerString("/"+serverPrefix+realPath);
            }
        }else {
            permissionCode = convertToPerString(realPath);
        }

        //h5需要延签
        String header = ServletUtils.getHeader(SecurityConstants.CLIENT);
        if (SecurityConstants.ClientType.PC.getName().equalsIgnoreCase(header)){
            if(getSubject(request, response).isPermitted(permissionCode)){
                return true;
            }else {
                log.error("权限异常,未知授权码["+permissionCode+"]");
                WebUtils.responseMsg(WebUtils.toHttp(response), ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.ERROR_401));
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

    /**
     * url 转成 urlString
     */
    private static String formateUrl(String url){
        if(url == null || "".equals(url.trim())){
            return null;
        }
        String res = url.replaceAll("\\/*([a-zA-Z0-9\\{\\}]+)\\/*", "/$1");
        return res;
    }


    /**
     * url 转 PerString
     * @param url
     * @return
     */
    public static String convertToPerString(String url){
        if(url == null || "".equals(url.trim())){
            return null;
        }

        String res = url.replaceAll("\\/*([a-zA-Z0-9\\{\\}]+)\\/*", ":$1");
        return res.replaceFirst(":", "");
    }

}
