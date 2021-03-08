package com.moses.cloud.commons.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 下午9:41
 * @Version 1.0
 **/
@Data
public class IdVo extends AbstractVo {

    @ApiModelProperty(value="ID")
    private String id;

    @ApiModelProperty(value="appType 应用类型 （app ，pad）")
    private String appType;
}
