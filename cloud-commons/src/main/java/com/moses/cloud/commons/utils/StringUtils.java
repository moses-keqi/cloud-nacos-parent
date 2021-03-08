package com.moses.cloud.commons.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 上午10:22
 * @Version 1.0
 **/
public class StringUtils {


    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 工具类 驼峰转下划线
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    /**
     * 工具类 驼峰转下划线
     * @param strs
     * @return
     */
    public static String[] humpToLine(String[] strs) {
        String[] result = new String[strs.length];
        for(int i = 0;i<strs.length;i++){
            result[i]=humpToLine(strs[i]);
        }
        return result;
    }

    /**
     * 工具类 判断是否手机号
     * @param strs
     * @return
     */
    public static boolean isMobile(String strs) {
        String regex = "^1{1}\\d{10}$";
        return strs.matches(regex);
    }

    /**
     * 拆分字符串
     * @param str 待拆分的字符串
     * @param length 按长度拆分段
     * @return
     * @throws UnsupportedEncodingException
     */
    public static List<String> split(String str, int length, String charset) throws UnsupportedEncodingException{
        if(org.apache.commons.lang.StringUtils.isNotBlank(str)){
            char [] chars =  str.toCharArray();
            List<String> res = new ArrayList<>();
            StringBuffer sb = new StringBuffer();
            int len = 0;
            int offset  = 1;
            for (char ca : chars) {
                offset = String.valueOf(ca).getBytes(charset).length;
                if(ca>256){
                    offset  = 3;
                }
                len = len+offset ;
                if(len > length){
                    res.add(sb.toString());
                    sb = new StringBuffer();
                    sb.append(ca);
                    len = offset;
                } else {
                    sb.append(ca);
                }
            }

            res.add(sb.toString());

            return res;
        }
        return null;
    }
}
