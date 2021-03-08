package com.moses.cloud.security.vo;

import com.moses.cloud.commons.vo.AbstractVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:23
 * @Version 1.0
 **/
@Data
public class PermissionTreeVo extends AbstractVo {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "父级ID")
    private String parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型 menu 菜单 ，resource 资源")
    private String type;

    @ApiModelProperty(value = "是否选择  True是，false否")
    private Boolean checked;

    @ApiModelProperty(value = "菜单类型，前端pad，后台pc")
    private String appType;
}
