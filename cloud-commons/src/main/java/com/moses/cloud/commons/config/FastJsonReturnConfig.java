package com.moses.cloud.commons.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2021/1/6 下午5:22
 * @Version 1.0
 **/
@Configuration
public class FastJsonReturnConfig implements WebMvcConfigurer {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverter(){
        //定义一个转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //添加fastJson的配置信息 比如 ：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                //结果是否格式化,默认为false
                SerializerFeature.PrettyFormat,
                //List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty,
                //字符类型字段如果为null,输出为”“,而非null
                SerializerFeature.WriteNullStringAsEmpty,
                // Boolean字段如果为null,输出为false,而非null
                SerializerFeature.WriteNullBooleanAsFalse,
                //是否输出值为null的字段,默认为false
                SerializerFeature.WriteMapNullValue,
                //消除对同一对象循环引用的问题，默认为false
                SerializerFeature.DisableCircularReferenceDetect
        );
        List<MediaType> mediaTypes = Lists.newArrayList();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.parseMediaType(String.format("%s;charset=UTF-8", MediaType.APPLICATION_JSON)));
        fastConverter.setSupportedMediaTypes(mediaTypes);
        //在转换器中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);

    }

}
