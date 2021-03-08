package com.moses.cloud.security.vo;

import com.moses.cloud.commons.vo.AbstractVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author HanKeQi
 * @Date 2021/1/5 下午5:25
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceVo extends AbstractVo {

    @ApiModelProperty(value = "是否需要验证码")
    private boolean vCode;

    @ApiModelProperty(value = "是否加密")
    private boolean encryption;

    @ApiModelProperty("用户是否存在")
    private boolean userExists;

    @ApiModelProperty("手机号")
    private String phoneNo;


}
