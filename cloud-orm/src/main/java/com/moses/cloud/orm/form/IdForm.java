package com.moses.cloud.orm.form;

import com.moses.cloud.commons.validator.NotNullOrEmpty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2021/1/5 下午2:15
 * @Version 1.0
 **/
@Data
public class IdForm extends AbstractForm{

    @ApiModelProperty(value = "唯一标识符", required = true)
    @NotNullOrEmpty(message = "ID不能为空")
    private String id;
}
