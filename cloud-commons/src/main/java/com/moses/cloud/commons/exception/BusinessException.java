package com.moses.cloud.commons.exception;

import lombok.Data;

/**
 *
 * 业务异常返回
 * @Author HanKeQi
 * @Date 2020/12/25 下午10:22
 * @Version 1.0
 **/
@Data
public class BusinessException extends RuntimeException{

    private String code;

    public BusinessException(String code , String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String code, String message,Throwable throwable){
        super(message, throwable);
        this.code = code;
    }

    public BusinessException(ExceptionMsg message){
        super(message.getMsg());
        this.code = message.getCode();
    }

    public BusinessException(ExceptionMsg message, Throwable throwable){
        super(message.getMsg(), throwable);
        this.code = message.getCode();
    }

}
