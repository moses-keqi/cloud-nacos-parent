package com.moses.cloud.security.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.moses.cloud.orm.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:05
 * @Version 1.0
 **/
@Data
@TableName("SEC_RESOURCE")
@ApiModel(value="Resource对象", description="")
public class Resource extends AbstractEntity {

    @ApiModelProperty(value = "标签")
    private String name;

    @ApiModelProperty(value = "资源")
    private String resourceString;

    @ApiModelProperty(value = "授权码")
    private String permissionString;

    @ApiModelProperty(value = "资源类型（api 接口资源 , element 元素资源）")
    private String type;

    @ApiModelProperty(value = "应用类型 （pc ，pad）")
    private String appType;

    @ApiModelProperty(value = "是否可用 false启用 true禁用")
    private Boolean disabled;
}
