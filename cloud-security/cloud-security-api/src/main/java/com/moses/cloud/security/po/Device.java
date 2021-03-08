package com.moses.cloud.security.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.moses.cloud.orm.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2021/1/6 下午7:50
 * @Version 1.0
 **/
@Data
@TableName("SEC_DEVICE")
@ApiModel(value="Device对象", description="")
public class Device extends AbstractEntity {

    @ApiModelProperty("设备号")
    private String deviceId;

    @ApiModelProperty("终端类型")
    private String client;

    @ApiModelProperty("登录名称")
    private String username;

    @ApiModelProperty("用户请求头, 后期推送需要")
    private String userAgent;
}
