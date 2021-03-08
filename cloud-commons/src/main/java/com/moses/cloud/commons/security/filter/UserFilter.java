package com.moses.cloud.commons.security.filter;

import com.moses.cloud.commons.ApplicationContextHolder;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.security.SecurityProperties;
import com.moses.cloud.commons.security.token.TokenPrincipalCollection;
import com.moses.cloud.commons.utils.RedisUtils;
import com.moses.cloud.commons.utils.TokenUtils;
import com.moses.cloud.commons.utils.WebUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author HanKeQi
 * @Date 2020/12/26 下午1:57
 * @Version 1.0
 **/
@Slf4j
public class UserFilter extends BaseFilter{

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
//		String utoken = WebUtils.toHttp(request).getHeader(SecurityConstants.HEADER_UTOKEN);
        String utoken = TokenUtils.getUToken();
        SecurityProperties props = ApplicationContextHolder.getBean(SecurityProperties.class);
        // 1 验证token
        // 1-1 解析token
//        SecurityModel model = SecurityHelper.getCurrentUser();
//        String s = RedisHelper.get(String.format(SecurityConstants.LOGIN_REDIS_V_CODE_CHECK, model.getUsername(), model.getClient()), String.class);
//        if (String)

        if(StringUtils.isEmpty(utoken)){
            WebUtils.responseMsg(WebUtils.toHttp(response), ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.NULL_UTOKEN));
            return false;
        }
        Claims claims;
        try {
            claims = TokenUtils.decodeToken(utoken, props.getSecretkey());
        } catch (Exception e) {
            log.error("UTOKEN 解析失败",e);
            WebUtils.responseMsg(WebUtils.toHttp(response), ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.INVALID_UTOKEN));
            return false;
        }

        // 1-2 检查token
        String key = TokenUtils.setTokenKey(utoken);
        TokenPrincipalCollection dbToken = RedisUtils.get(key, TokenPrincipalCollection.class);
        if(dbToken !=null && dbToken.getUtoken().equals(utoken)){
            RedisUtils.expire(key, props.getExpireTime());
        } else {
            WebUtils.responseMsg(WebUtils.toHttp(response), ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.LOGIN_INVALID_UTOKEN));
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }
}
