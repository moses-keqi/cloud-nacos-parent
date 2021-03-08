package com.moses.cloud.commons.utils;

import com.alibaba.fastjson.JSON;
import com.moses.cloud.commons.ApplicationContextHolder;
import com.moses.cloud.commons.security.SecurityConstants;
import com.moses.cloud.commons.security.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author HanKeQi
 * @Date 2020/12/26 下午12:29
 * @Version 1.0
 **/
@SuppressWarnings("checkstyle:JavadocType")
@Slf4j
public class TokenUtils {

    /** 令牌签发者 */
    public static final String ISS = "iss";
    /** 令牌签发时间 */
    public static final String IAT = "iat";
    /** 令牌用户id */
    public static final String USER_ID = "userId";
    /** 令牌超时时间 */
    public static final String EXPIRE_TIME = "exp";


    // 表达式对象
    private static final Pattern PATTERN_REGULAR = Pattern.compile("(^\\/([a-zA-Z0-9-_]*))(.*)");

    /**
     * 生成token key
     * @param token
     * @return
     */
    public static String setTokenKey(String token){
        SecurityProperties props = ApplicationContextHolder.getBean(SecurityProperties.class);

        // 一个账号只有一个token,之前的token会被覆盖
        if(props.getModel().getValue().equals(SecurityProperties.Model.SINGLE.getValue())){
            try {
                String currentClient = SecurityUtils.getCurrentClient();
                Claims claims = decodeToken(token, props.getSecretkey());
                return String.format("shiro-token-%s-%s:", currentClient, claims.get(USER_ID, String.class));
            } catch (Exception e) {
                log.error("UTOKEN 解析失败",e);
            }
        }
        return String.format("shiro-token:%s", token);
    }

    /**
     * 加密 生成token
     * @param userId
     * @param expireTime 超时时间
     * @param secretKey 密钥
     * @param iss 签发者
     * @return
     */
    public static String generateToken(String userId,long expireTime,String secretKey,String iss) {
        Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        // JWT 头部
        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");
        // JWT 载荷
        Map<String, Object> payloadMap = new HashMap<String, Object>();
        payloadMap.put(USER_ID, userId);	// 用户id
        payloadMap.put(EXPIRE_TIME, System.currentTimeMillis()+expireTime*1000);	// 过期时间
        payloadMap.put(ISS, iss);	// 签发者
        payloadMap.put(IAT, System.currentTimeMillis()); // 签发时间
        String token = Jwts.builder().setHeader(headerMap).setPayload(JSON.toJSONString(payloadMap))
                // JWT 签证
                .signWith(SignatureAlgorithm.HS256, key).compact();
        return token;
    }

    /**
     * 解密 token
     * @param token
     */
    public static Claims decodeToken(String token,String secretKey) {
        Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return body;
    }

    /**
     * 获取UTOKEN
     * @return
     */
    public static String getUToken(){
        return ServletUtils.getHeader(SecurityConstants.HEADER_UTOKEN);
    }

    /**
     * spring cloud 支持
     * 获取真实服务接口地址
     * @param servletPath
     * @return
     */
    public static String getRealPath(String servletPath){
        // 创建 Matcher 对象
        Matcher m = PATTERN_REGULAR.matcher(servletPath);
        if(m.find()){
            return m.group(3);
        }
        return null;
    }


    /**
     * spring cloud 支持
     * 获取zull网关服务ID
     * @param servletPath
     * @return
     */
    public static String getServerId(String servletPath){
        // 创建 Matcher 对象
        Matcher m = PATTERN_REGULAR.matcher(servletPath);
        if(m.find()){
            return m.group(2);
        }
        return null;
    }

    public static String getAppType(){
        return ServletUtils.getHeader(SecurityConstants.CLIENT);
    }

}
