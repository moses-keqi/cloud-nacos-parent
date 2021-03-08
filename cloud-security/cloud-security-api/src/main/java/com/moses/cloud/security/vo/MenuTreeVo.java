package com.moses.cloud.security.vo;

import com.moses.cloud.commons.vo.AbstractVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 下午9:35
 * @Version 1.0
 **/
@Data
public class MenuTreeVo extends AbstractVo {

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "类型 （menu 菜单，resource 资源）")
    private String type;

    @ApiModelProperty(value = "父级ID")
    private String parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "授权码")
    private String permissionString;

    @ApiModelProperty(value = "排序")
    private Integer sortIndex;

    @ApiModelProperty(value = "链接路径")
    private String url;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "子集节点")
    private List<MenuTreeVo> children;
}
