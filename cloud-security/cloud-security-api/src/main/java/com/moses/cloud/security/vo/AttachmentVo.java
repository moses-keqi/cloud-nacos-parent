package com.moses.cloud.security.vo;

import com.moses.cloud.commons.vo.AbstractVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2021/2/18 下午3:21
 * @Version 1.0
 **/
@Data
public class AttachmentVo extends AbstractVo {

    private String id;
    @ApiModelProperty(value = "图片类型")
    private String type;
    @ApiModelProperty(value = "附件原来的名称")
    private String oldName;
    @ApiModelProperty(value = "附件名称")
    private String newName;
    @ApiModelProperty(value = "附件路径：附件相对路径")
    private String path;
    @ApiModelProperty(value = "附件链接")
    private String url;
    @ApiModelProperty(value = "图片位置")
    private String position;
    @ApiModelProperty(value = "缩略图url")
    private String thumbnailUrl;
}
