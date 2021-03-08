package com.moses.cloud.security.form;

import com.moses.cloud.orm.form.AbstractPageConditionForm;
import com.moses.cloud.security.po.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色查询条件
 * @Author HanKeQi
 * @Date 2020/12/30 上午10:47
 * @Version 1.0
 **/
@Data
public class RoleConditionForm extends AbstractPageConditionForm<Role> {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "角色授权码")
    private String roleString;

    @ApiModelProperty(value = "是否可用（0 不可用，1 可用）")
    private Boolean disabled;
}
