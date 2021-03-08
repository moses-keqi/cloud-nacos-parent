package com.moses.cloud.security.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.moses.cloud.orm.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 上午10:08
 * @Version 1.0
 **/
@Data
@TableName("SYS_DICT")
@ApiModel(value="Dict对象", description="")
public class Dict extends AbstractEntity {

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "中文名称")
    private String nameCn;

    @ApiModelProperty(value = "英文名称")
    private String nameEn;

    @ApiModelProperty(value = "字典项取值")
    private String dictValue;

    @ApiModelProperty(value = "父字典ID")
    private String parentId;

    @ApiModelProperty(value = "节点层级")
    private Integer nodeLevel;

    @ApiModelProperty(value = "排序")
    private Integer sortOrder = 0 ;

    @ApiModelProperty(value = "字典描述")
    private String remarks;

    @ApiModelProperty(value = "是否可用 （0 可用 1 不可用）")
    private boolean enabled = Boolean.FALSE.booleanValue();

    @ApiModelProperty(value = "字典类型  0 系统级，1选项级 ")
    private Integer systemFlag;

}
