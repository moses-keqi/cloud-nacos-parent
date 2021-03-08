package com.moses.cloud.security.form;

import com.moses.cloud.orm.form.AbstractPageConditionForm;
import com.moses.cloud.security.po.Menu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:22
 * @Version 1.0
 **/
@Data
public class MenuConditionForm extends AbstractPageConditionForm<Menu> {

    @ApiModelProperty(value = "名称")
    private String name;

}
