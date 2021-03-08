package com.moses.cloud.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.moses.cloud.commons.security.SecurityConstants;
import com.moses.cloud.commons.utils.DictUtils;
import com.moses.cloud.commons.utils.RedisUtils;
import com.moses.cloud.commons.utils.ServletUtils;
import com.moses.cloud.commons.vo.CacheDictVo;
import com.moses.cloud.security.mapper.DeviceMapper;
import com.moses.cloud.security.po.Device;
import com.moses.cloud.security.po.User;
import com.moses.cloud.security.service.IDeviceService;
import com.moses.cloud.security.service.IUserService;
import com.moses.cloud.security.vo.DeviceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Author HanKeQi
 * @Date 2021/1/6 下午8:06
 * @Version 1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

    @Autowired
    private IUserService userService;

    @Override
    public DeviceVo findByDeviceCheck(Device device) {
        String username = device.getUsername();
        String client = device.getClient();

        DeviceVo deviceVo = new DeviceVo(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,null);
        User user = userService.findByUsername(device.getUsername());
        if (user == null){
            return deviceVo;
        }
        String phoneNo = user.getPhoneNo();
        if (!Strings.isNullOrEmpty(phoneNo)){
            deviceVo.setPhoneNo(phoneNo.substring(phoneNo.length() -4));
            deviceVo.setUserExists(true);
        }
        //把大写转成小写
        CacheDictVo cacheDictVo = DictUtils.findByCode(String.format("aes256.%s.enabled", client.toLowerCase()));
        if (cacheDictVo != null && !Strings.isNullOrEmpty(cacheDictVo.getDictValue())){
            deviceVo.setEncryption(Boolean.valueOf(cacheDictVo.getDictValue()).booleanValue());
        }
        Boolean flag = RedisUtils.get(String.format(SecurityConstants.LOGIN_REDIS_V_CODE_FLAG, username, client), Boolean.class);
        if (flag != null && flag){
            deviceVo.setVCode(flag);
            return deviceVo;
        }
        //用户请求头
        String userAgent = ServletUtils.getHeader(SecurityConstants.USER_AGENT);
//        String deviceId = ServletHelper.getHeader(SecurityConstants.DEVICE_ID);
        LambdaQueryWrapper<Device> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Device::getUsername, username);
        wrapper.eq(Device::getDeviceId, device.getDeviceId());
        wrapper.eq(Strings.isNullOrEmpty(client), Device::getClient, client);
        Device one = this.getOne(wrapper);
        if (one == null){
            deviceVo.setVCode(Boolean.TRUE);
            device.setCreateBy(username);
            device.setCreateName(user.getName());
            device.setDeleted(Boolean.FALSE);
            device.setCreateTime(new Date());
            device.setUserAgent(userAgent);
            super.save(device);
            //进入Redis
            RedisUtils.set(String.format(SecurityConstants.LOGIN_REDIS_V_CODE_FLAG, username, client), Boolean.TRUE);

        }

        return deviceVo;
    }

    @Override
    public boolean sendSmsDevice(String username, String phoneNo, String client, String deviceId) {
        Device device = new Device();
        device.setClient(client);
        device.setDeviceId(deviceId);
        device.setUsername(username);
        DeviceVo deviceVo = this.findByDeviceCheck(device);
        if (deviceVo != null){
            boolean vCode = deviceVo.isVCode();
            try {
                if (vCode){
                    //登录成功立马发送
                    String vCodeValueFlag = DictUtils.findByValue("moses.sms.enabled");
                    if (!StringUtils.isEmpty(vCodeValueFlag) && Boolean.valueOf(vCodeValueFlag).booleanValue()){
                        return Boolean.valueOf(vCodeValueFlag).booleanValue();
                    }
                }
                return vCode;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return Boolean.FALSE.booleanValue();
    }

    @Override
    public boolean sendCheckDevice(String phoneNo, String vCode) {
        try {
            String vCodeValueFlag = DictUtils.findByValue("moses.sms.enabled");
            if (!StringUtils.isEmpty(vCodeValueFlag) && Boolean.valueOf(vCodeValueFlag).booleanValue()){
                return Boolean.valueOf(vCodeValueFlag).booleanValue();
            }
        }catch (Exception e){

        }
        return Boolean.TRUE.booleanValue();
    }

}
