package com.moses.cloud.commons.config;

import com.moses.cloud.commons.security.SecurityConstants;
import com.moses.cloud.commons.utils.ServletUtils;
import com.moses.cloud.commons.utils.TokenUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @Author HanKeQi
 * @Date 2021/1/6 下午5:11
 * @Version 1.0
 **/
@Component
public class FeignConfiguration implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        String utoken = TokenUtils.getUToken();
        String client = ServletUtils.getHeader(SecurityConstants.CLIENT);
        String requestNo = ServletUtils.getHeader(SecurityConstants.REQUEST_NO);
        String deviceId = ServletUtils.getHeader(SecurityConstants.DEVICE_ID);
        if(StringUtils.isNotBlank(utoken)){
            template.header(SecurityConstants.HEADER_UTOKEN, utoken);
        }
        if(StringUtils.isNotBlank(client)){
            template.header(SecurityConstants.CLIENT, client);
        }
        if(StringUtils.isNotBlank(requestNo)){
            template.header(SecurityConstants.REQUEST_NO, requestNo);
        }
        if(StringUtils.isNotBlank(deviceId)){
            template.header(SecurityConstants.DEVICE_ID, deviceId);
        }
    }
}
