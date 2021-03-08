package com.moses.cloud.security.controller.web;

import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.security.SecurityConstants;
import com.moses.cloud.commons.security.model.SecurityModel;
import com.moses.cloud.commons.utils.RedisUtils;
import com.moses.cloud.commons.utils.SecurityUtils;
import com.moses.cloud.security.form.VCodeForm;
import com.moses.cloud.security.service.IDeviceService;
import com.moses.cloud.security.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author HanKeQi
 * @Date 2021/1/8 下午12:43
 * @Version 1.0
 **/
@Api(tags="用户管理")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IDeviceService deviceService;

    @ApiOperation(value="发送验证码",notes="\n发送验证码")
    @PostMapping(value="send-sms")
    public ResponseData<String> sendMsg(){
        SecurityModel model = SecurityUtils.getCurrentUser();
        String username = model.getUsername();
        String phoneNo = model.getPhoneNo();
        String client = model.getClient();
        String deviceId = model.getDeviceId();
        deviceService.sendSmsDevice(username, phoneNo, client, deviceId);
        return ResponseData.newInstanceOfSuccess();
    }

    @ApiOperation(value="验证码验证",notes="\n验证码")
    @PostMapping(value="send-check")
    public ResponseData<String> check(@Valid @RequestBody VCodeForm form, BindingResult result){
        if (result.hasErrors()){
            return ResponseData.newInstanceOfInvalid(result);
        }
        SecurityModel model = SecurityUtils.getCurrentUser();
        if (deviceService.sendCheckDevice(model.getPhoneNo(), form.getVCode())){
            RedisUtils.del(String.format(SecurityConstants.LOGIN_REDIS_V_CODE_FLAG, model.getUsername(), model.getClient()));
            return ResponseData.newInstanceOfSuccess();
        }

        return ResponseData.newInstanceError(ExceptionMsg.DEFAULT_ERROR.getCode(), "验证码输入错误");
    }

}
