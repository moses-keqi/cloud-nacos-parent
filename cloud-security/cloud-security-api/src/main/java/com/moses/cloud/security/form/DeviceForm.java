package com.moses.cloud.security.form;

import com.moses.cloud.commons.validator.NotNullOrEmpty;
import com.moses.cloud.orm.form.AbstractForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2021/1/6 下午8:00
 * @Version 1.0
 **/
@Data
public class DeviceForm extends AbstractForm {

    @NotNullOrEmpty(message = "工号不能为空")
    @ApiModelProperty("登录工号")
    private String username;

}
