package com.moses.cloud.orm.crypt.annotation;

import java.lang.annotation.*;

/**
 * 数据库层加解密注解
 * @Author HanKeQi
 * @Date 2021/1/8 下午5:35
 * @Version 1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
public @interface CryptField {

    /**
     * 注解的属性
     * @return
     */
    String value() default "";

    /**
     * 加密
     * @return
     */
    boolean encrypt() default true;

    /**
     * 解密
     * @return
     */
    boolean decrypt() default true;

}
