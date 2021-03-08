package com.moses.cloud.security.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.moses.cloud.commons.vo.AbstractVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author HanKeQi
 * @Date 2021/2/2 下午2:53
 * @Version 1.0
 **/
@Data
public class AboutVersionAppVo extends AbstractVo {

    @ApiModelProperty("版本")
    private Float version;

    @ApiModelProperty("0：强制更新 1：一般更新 2：静默更新")
    private String updateType;

    @ApiModelProperty("更新描述")
    private String description;

    @ApiModelProperty("产品名称")
    private String productName;

    /**
     * H5 升级 zip升级
     * IOS 升级 ipa
     * ANDROID 升级 apk 升级
     */
    @ApiModelProperty("类型: h5、ios 、android")
    private String client;


    @ApiModelProperty("下载地址")
    private String downUrl;

    @ApiModelProperty("时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
