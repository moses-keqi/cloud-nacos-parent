package com.moses.cloud.commons.msg;

import com.moses.cloud.commons.exception.ExceptionMsg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/17 下午8:06
 * @Version 1.0
 **/
@Data
@ApiModel(value = "resultBody", description = "响应信息封装")
public class ResponseData<T> implements Serializable {

    @ApiModelProperty(notes = "响应代码")
    private String code;

    @ApiModelProperty(notes = "响应信息")
    private String msg;
    @ApiModelProperty(notes = "响应数据")
    private T data;


    public ResponseData() {
    }

    public ResponseData(Builder<T> builder) {
        this.code = builder.code;
        this.msg = builder.msg;
        this.data = builder.data;
    }




    public static <T> ResponseData<T> newInstanceOfSuccess() {
        return ResponseData.<T>builder().success().build();
    }

    public static <T> ResponseData<T> newInstanceOfSuccess(T data) {
        return ResponseData.<T>builder().success().data(data).build();
    }

    public static <T> ResponseData<T> newInstanceOfExceptionMsg(ExceptionMsg exceptionMsg){
        return newInstanceError(exceptionMsg.getCode(), exceptionMsg.getMsg());
    }

    public static <T> ResponseData<T> newInstanceOfDefaultError(){
        return newInstanceOfExceptionMsg(ExceptionMsg.DEFAULT_ERROR);
    }

    public static <T> ResponseData<T> newInstanceError(String code , String msg){
        return ResponseData.<T>builder().code(code)
                .msg(msg).build();
    }

    public static <T> ResponseData<T> newInstanceOfInvalid(BindingResult result){
        if (result != null){
            List<ObjectError> allErrors = result.getAllErrors();
            if (!CollectionUtils.isEmpty(allErrors)){
                for (ObjectError oe : allErrors) {
                    if (!StringUtils.isEmpty(oe.getDefaultMessage())){
                        return ResponseData.<T>builder().code(ExceptionMsg.INVALID_PARAM.getCode()).msg(oe.getDefaultMessage()).build();
                    }
                }
            }
        }
        return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.DEFAULT_ERROR);
    }

    public static<T> Builder<T> builder(){
        return new Builder<T>();
    }

    public static class Builder<T>{

        private String code;

        private String msg;

        private T data;

        public Builder<T> success() {
            this.code = ExceptionMsg.SUCCESS.getCode();
            this.msg = ExceptionMsg.SUCCESS.getMsg();
            return this;
        }

        public Builder<T> code(String code) {
            this.code = code;
            return this;
        }
        public Builder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }
        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ResponseData<T> build(){
            return new ResponseData<T>(this);
        }

    }


}
