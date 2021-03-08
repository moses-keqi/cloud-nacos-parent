package com.moses.cloud.commons.utils;

import com.google.common.base.Strings;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author HanKeQi
 * @Date 2020/1/06 下午1:46
 * @Version 1.0
 **/
public class UrlUtils {

    private static String parmPattern = "\\{(.+?)\\}";

    /**
     * 将url参数转换成map，取值按照插入顺序
     *
     * @param param
     *            aa=11&bb=22&cc=33
     * @return
     */
    public static Map<String, String> getUrlParamsInOrder(String param) {
        Map<String, String> map = new LinkedHashMap<>(0);
        if (org.apache.commons.lang3.StringUtils.isBlank(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            int index = params[i].indexOf('=');
            if (index == -1) {
                map.put(params[i], "");
            } else {
                //map.put(params[i].substring(0, index), params[i].substring(index + 1));
                String key = params[i].substring(0, index);
                String value = params[i].substring(index + 1);
                if (Strings.isNullOrEmpty(value)){
                    map.put(key, value);
                    continue;
                }
//
                map.put(key, value);

            }
            // 废弃，不适用于参数值有等号的情况
            // String[] p = params[i].split("=");
            // if (p.length == 2) {
            // map.put(p[0], p[1]);
            // }
        }
        return map;
    }

    /**
     *
     * getQueryParamValue:取url的参数
     *
     * @author lixiaozhong
     * @param key
     * @param url
     * @return 设定文件
     * @throws List
     *             <String> DOM对象
     * @since JDK 1.8
     */
    public static List<String> getQueryParamValue(String key, String url) {
        return getQueryParams(url).get(key);
    }

    /**
     *
     * getQueryParams: 将url参数转换成map，取值顺序不可预知
     *
     * @param url http://www.abc.com?a=12&b=23&c=hello
     * @return map，取值顺序不可预知
     * @since JDK 1.8
     */
    public static Map<String, List<String>> getQueryParams(String url) {
        try {
            Map<String, List<String>> params = new HashMap<>();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }
                    List<String> values = params.get(key);
                    if (values == null) {
                        values = new ArrayList<>();
                        params.put(key, values);
                    }
                    values.add(value);
                }
            }
            // log.debug(String.format("Parsed params: %s", params));
            return params;
        } catch (UnsupportedEncodingException ex) {
            // log.error("Cannot parse params!", ex);
            throw new AssertionError(ex);
        }
    }

    /**
     * 将map转换成url参数形式
     *
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = org.apache.commons.lang3.StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }


    /**
     * 将map转换成url参数形式
     *
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(String url, Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        if(isPathParamUrl(parmPattern, url)){
            List<String> pathParamList = getParams(parmPattern, url);
            if(pathParamList!=null && pathParamList.size()>0) {
                url = parse(parmPattern, url, map);
                pathParamList.forEach(e->{
                    map.remove(e);
                });
            }
        }
        StringBuilder sb = new StringBuilder(url);
        //Map<String, List<String>> queryParams = getQueryParams(url);
        if(!CollectionUtils.isEmpty(map)){
            sb.append("?");
        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = org.apache.commons.lang3.StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }

    /**
      * 根据正则表达式获取文字符串中的变量名列表
      * @param pattern
      * @param url
      * @return
      */
    public static boolean isPathParamUrl(String pattern, String url) {
        boolean result = false;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(url);
        if(m.find()) {
            result = true;
        }
        return result;
    }

    /**
      * 根据正则表达式获取文字符串中的变量名列表
      * @param pattern
      * @param content
      * @return
      */
    public static List<String> getParams(String pattern, String content) {
       Pattern p = Pattern.compile(pattern);
       Matcher m = p.matcher(content);
       List<String> result = new ArrayList<String>();
       while (m.find()) {
           result.add(m.group(1));
       }
       return result;
    }

    /**
      * 根据正则表达式将字符串中的变量使用实际的数据替换成无变量的字符串
      * @param pattern
      * @param content
      * @param data
      * @return
      */
    public static String parse(String pattern, String content, Map<String, Object> data) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String key = m.group(1);
            String value = (String) data.get(key);
            m.appendReplacement(sb, value == null ? "" : value);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * Get "http://domain:port/" from the request object
     */
    public static String getBasePath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
    }

}
