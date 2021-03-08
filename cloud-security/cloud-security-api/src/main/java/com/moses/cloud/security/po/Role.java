package com.moses.cloud.security.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moses.cloud.orm.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 下午9:54
 * @Version 1.0
 **/
@Data
@TableName("SEC_ROLE")
@ApiModel(value="Role对象", description="")
public class Role extends AbstractEntity {

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "角色授权码")
    private String roleString;

    @ApiModelProperty(value = "是否可用（0 可用，1 不可用）")
    private Boolean disabled;

    @ApiModelProperty(value = "应用类型 （pc ，pad）")
    @TableField("APP_TYPE")
    private String appType;

    @ApiModelProperty(value = "是否已删除 （0 未删除，1 已删除）")
    @TableField("DELETED")
    private Boolean deleted;

    @ApiModelProperty(value = "备注")
    @TableField("DESC_")
    private String desc;
}
