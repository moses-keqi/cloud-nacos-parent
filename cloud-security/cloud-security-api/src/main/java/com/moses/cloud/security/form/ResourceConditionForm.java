package com.moses.cloud.security.form;

import com.moses.cloud.orm.form.AbstractPageConditionForm;
import com.moses.cloud.security.po.Resource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:07
 * @Version 1.0
 **/
@Data
public class ResourceConditionForm extends AbstractPageConditionForm<Resource> {

    @ApiModelProperty(value = "标签")
    private String name;

    @ApiModelProperty(value = "资源")
    private String resourceString;

    @ApiModelProperty(value = "授权码")
    private String permissionString;

    @ApiModelProperty(value = "是否可用 false可用, true不可用")
    private Boolean enabled;

    @ApiModelProperty(value = "是否已分配 false 未分配, true 已分配")
    private Boolean used;

    @ApiModelProperty(value = "资源类型，pad前端，pc后台")
    private String appType;

}
