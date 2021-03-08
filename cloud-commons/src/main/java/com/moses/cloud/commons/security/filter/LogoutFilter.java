package com.moses.cloud.commons.security.filter;

import com.google.common.collect.Lists;
import com.moses.cloud.commons.ApplicationContextHolder;
import com.moses.cloud.commons.exception.BusinessException;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.security.SecurityProperties;
import com.moses.cloud.commons.security.checker.LogoutChecker;
import com.moses.cloud.commons.security.token.TokenPrincipalCollection;
import com.moses.cloud.commons.utils.RedisUtils;
import com.moses.cloud.commons.utils.TokenUtils;
import com.moses.cloud.commons.utils.WebUtils;
import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/26 下午1:48
 * @Version 1.0
 **/
@Slf4j
public class LogoutFilter extends BaseFilter{


    private List<LogoutChecker> preLogoutChecks = Lists.newArrayList();
    private List<LogoutChecker> postLogoutChecks = Lists.newArrayList();

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue)
            throws Exception {
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        HttpServletResponse response = WebUtils.toHttp(servletResponse);

        // 检测是否为post请求
        String method = request.getMethod();
        if(!"POST".equalsIgnoreCase(method)){
            WebUtils.responseMsg(response, "Method Not Allowed", 405);
            return false;
        }

        try {
            String utoken = TokenUtils.getUToken();

            // 校验utoken是否为空
            if(StringUtils.isEmpty(utoken)){
                throw new BusinessException(ExceptionMsg.NULL_UTOKEN);
            }

            // 校验utoken是否合法
            SecurityProperties props = ApplicationContextHolder.getBean(SecurityProperties.class);
            try {
                @SuppressWarnings("unused")
                Claims claims = TokenUtils.decodeToken(utoken, props.getSecretkey());
            } catch (Exception e) {
                log.error("UTOKEN 解析失败",e);
                throw new BusinessException(ExceptionMsg.INVALID_UTOKEN);
            }

            // 删除缓存
            String key = TokenUtils.setTokenKey(utoken);
            TokenPrincipalCollection tokenPrincipalCollection = RedisUtils.get(key, TokenPrincipalCollection.class);
            if(tokenPrincipalCollection != null){
                String dbUtoken = tokenPrincipalCollection.getUtoken();
                if(!StringUtils.isEmpty(dbUtoken) && dbUtoken.equals(utoken)){
                    RedisUtils.del(key);
                }
            }
            WebUtils.responseMsg(response, ResponseData.newInstanceOfSuccess());
            return false;
        } catch (Exception e) {
            log.error("",e);
            ResponseData responseData = ResponseData.newInstanceOfDefaultError();
            responseData.setMsg(e.getMessage());
            WebUtils.responseMsg(response, responseData);
            return false;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
