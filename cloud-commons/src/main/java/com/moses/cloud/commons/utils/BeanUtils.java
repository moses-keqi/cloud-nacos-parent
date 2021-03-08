package com.moses.cloud.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 上午11:32
 * @Version 1.0
 **/
public class BeanUtils {

    private static DozerBeanMapper dozer = new DozerBeanMapper();

//	static{
//		SerializeConfig.getGlobalInstance().setAsmEnable(false);
//		ParserConfig.getGlobalInstance().setAsmEnable(false);
//	}

    public static <T> T copy(Object source, Class<T> destinationClass) {
        if(source == null){
            return null;
        }
        return dozer.map(source, destinationClass);
    }

    public static <T> List<T> copyList(Collection<?> sourceList, Class<T> destinationClass) {
        if(sourceList == null){
            return null;
        }
        List<T> destinationList = new ArrayList<T>();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    public static <T> T copy(Object source, TypeReference<T> typeReference){
        if(source == null){
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 设置反序列化时忽略找不到的字段
            String sourceJson = mapper.writeValueAsString(source);
            return mapper.readValue(sourceJson, typeReference);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
