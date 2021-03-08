package com.moses.cloud.security.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.moses.cloud.orm.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午10:51
 * @Version 1.0
 **/
@Data
@TableName("SEC_MENU")
@ApiModel(value="Menu对象", description="")
public class Menu extends AbstractEntity {

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

    @ApiModelProperty(value = "是否可用 0 可用 1 不可用")
    private Boolean disabled;

    @ApiModelProperty(value = "应用类型 （pc ，pad）")
    private String appType;
}
