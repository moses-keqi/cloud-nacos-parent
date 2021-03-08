package com.moses.cloud.commons.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典vo
 * @Author HanKeQi
 * @Date 2020/12/28 上午9:39
 * @Version 1.0
 **/
@Data
public class CacheDictVo extends AbstractVo {

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "父字典ID")
    private String parentId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "中文名称")
    private String nameCn;

    @ApiModelProperty(value = "英文名称")
    private String nameEn;

    @ApiModelProperty(value = "字典项取值")
    private String dictValue;

    @ApiModelProperty(value = "字典描述")
    private String desc;

    @ApiModelProperty(value = "节点层级")
    private Integer nodeLevel;
}
