package com.moses.cloud.commons.exception;

import com.moses.cloud.commons.msg.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * @Author HanKeQi
 * @Date 2020/12/25 下午10:17
 * @Version 1.0
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 系统异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value={Exception.class})
    public ResponseData<String> exception(Exception ex){
        log.error("系统级异常",ex);
        if (ex instanceof NullPointerException) {
            return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.NULL_POINT_EXCEPTION);
        } else if (ex instanceof NoSuchElementException) {
            return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.NO_SUCH_ELEMENT);
        } else if (ex instanceof IOException) {
            return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.IO_EXCEPTION);
        } else if (ex instanceof ClassNotFoundException) {
            return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.CLASS_NOT_FOUND);
        } else if (ex instanceof ArithmeticException) {
            return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.ARITHMETIC_EXCEPTION);
        } else if (ex instanceof ArrayIndexOutOfBoundsException) {
            return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.ARRAY_INDEX_OUTOFBOUND);
        } else if (ex instanceof IllegalArgumentException) {
            return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.ILLEGAL_ARGUMENT_EXCEPTION);
        } else if (ex instanceof ClassCastException) {
            return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.CLASS_CAST_EXCEPTION);
        } else if (ex instanceof SecurityException) {
            return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.SECURITY_EXCEPTION);
        }
        //404单独处理
        //else if (ex instanceof NoHandlerFoundException){
        //   return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.INVALID_URL);
        //}
        else if (ex instanceof HttpMessageNotReadableException){
            return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.NULL_VALUE_EXCEPTION);
        }else if (ex instanceof HttpRequestMethodNotSupportedException){
            return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.INVALID_REQUEST_URL);
        }
        return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.DEFAULT_ERROR);
    }


    /**
     * 404异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseData<String> errorHandler(NoHandlerFoundException ex) {
        log.error("404异常单独处理",ex);
        return ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.INVALID_URL);
    }



    /**
     * 系统异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value={BusinessException.class})
    public ResponseData<String> exception(BusinessException ex){
        log.error("业务异常",ex);
        return ResponseData.newInstanceError(ex.getCode(), ex.getMessage());
    }

}
