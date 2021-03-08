package com.moses.cloud.security.form;

import com.moses.cloud.commons.validator.LengthFixed;
import com.moses.cloud.commons.validator.NotNullOrEmpty;
import com.moses.cloud.orm.form.AbstractForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2021/1/8 下午1:37
 * @Version 1.0
 **/
@Data
@ApiModel("验证码")
public class VCodeForm extends AbstractForm {

    @ApiModelProperty("验证码")
    @LengthFixed(fixed = 6, message = "验证不能少于{fixed}位")
    @NotNullOrEmpty(message = "验证码不能为空")
    private String vCode;

}
