package com.moses.cloud.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moses.cloud.security.po.Device;
import com.moses.cloud.security.vo.DeviceVo;

/**
 * @Author HanKeQi
 * @Date 2021/1/6 下午8:06
 * @Version 1.0
 **/
public interface IDeviceService extends IService<Device> {

    /**
     * 校验用户是否存在、当前设备、终端登录是否需要验证码，是否需要加密
     * @param device
     * @return
     */
    DeviceVo findByDeviceCheck(Device device);

    /**
     * 根据设备发短信
     * @param username
     * @param phoneNo
     * @param client
     * @param deviceId
     * @return
     */
    boolean sendSmsDevice(String username, String phoneNo, String client, String deviceId);

    /**
     * 根据设备校验短信
     * @param phoneNo
     * @param vCode
     * @return
     */
    boolean sendCheckDevice(String phoneNo, String vCode);
}
