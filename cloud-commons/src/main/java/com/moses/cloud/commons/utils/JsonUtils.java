package com.moses.cloud.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * @Author HanKeQi
 * @Date 2021/1/6 下午3:06
 * @Version 1.0
 **/
public class JsonUtils {

    /**
     * JSON转对象
     * @param clazz
     * @return
     */
    public static <T> T parseObject(String text,Class<T> clazz){
        return JSON.parseObject(text, clazz);
    }

    /**
     * 对象转JSON
     * @param object
     * @return
     */
    public static String toJSONString(Object object){
        return JSON.toJSONString(object);
    }

    /**
     * 对象转JSON 支持泛型
     * @param text
     * @param type
     * @return
     */
    public static <T> T parseObject(String text, TypeReference<T> type){
        return JSON.parseObject(text, type);
    }


    /**
     *
     * @param strMaps 转Map<String, Object> 返回json
     * @return
     */
    public static String mapToJSONString(Map<String, String> strMaps){
        Map<String, Object> object = Maps.newHashMap();
        strMaps.forEach((key, value)->{
            Object valueObject = value;
            if (!Strings.isNullOrEmpty(value)){
                if (value.startsWith("[")){
                    valueObject = JSONArray.parseArray(value);
                }
                if (value.startsWith("{")){
                    valueObject = JSONObject.parseObject(value);
                }
                object.put(key, valueObject);
            }else {
                object.put(key, value);
            }
        });
        return toJSONString(object);
    }

    /**
     * object 转byte[]
     * @param object
     * @return
     * @throws IOException
     */
    public static byte[] objectToByte(Object object) throws IOException {
        ByteArrayOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            os = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(os);
            oos.writeObject(object);
            return os.toByteArray();
        }finally {
            if (os != null){
                os.close();
            }
            if (oos != null){
                oos.close();
            }
        }
    }
}
