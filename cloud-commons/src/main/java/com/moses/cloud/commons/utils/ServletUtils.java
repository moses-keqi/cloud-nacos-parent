package com.moses.cloud.commons.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author HanKeQi
 * @Date 2021/1/6 下午5:13
 * @Version 1.0
 **/
public class ServletUtils {

    /**
     * 获取header value
     * @param headerKey
     * @return
     */
    public static String getHeader(String headerKey){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if(requestAttributes==null){
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String value = request.getHeader(headerKey);
        if(StringUtils.isEmpty(value)){
            value = request.getParameter(headerKey);
        }
        return value;
    }
}
