package com.moses.cloud.commons.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @Author HanKeQi
 * @Date 2020/12/26 下午1:46
 * @Version 1.0
 **/
public class WebUtils {

    public static String getJson(HttpServletRequest request){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String inputstr;
            while ((inputstr = reader.readLine()) != null) {
                stringBuilder.append(inputstr);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void responseMsg(HttpServletResponse response, Object result){
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print(JSON.toJSONString(result));
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException("响应消息异常",e);
        }
    }

    public static void responseMsg(HttpServletResponse response,Object result,int status){
        try {
            response.setStatus(status);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print(JSON.toJSONString(result));
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException("响应消息异常",e);
        }
    }

    public static void response401(HttpServletResponse response,String message){
        try {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print(JSON.toJSONString(message));
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException("响应消息异常",e);
        }
    }

    public static HttpServletRequest toHttp(ServletRequest request) {
        return (HttpServletRequest)request;
    }

    public static HttpServletResponse toHttp(ServletResponse response) {
        return (HttpServletResponse)response;
    }


}
