package com.moses.cloud.security.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moses.cloud.orm.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author renker
 * @since 2019-09-12
 */
@Data
@TableName("SYS_ATTACHMENT")
@ApiModel(value="Attachment对象", description="")
public class Attachment extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关联id")
    @TableField("OBJECT_ID")
    private String objectId;

    @ApiModelProperty(value = "附件原来的名称")
    @TableField("OLD_NAME")
    private String oldName;

    @ApiModelProperty(value = "附件名称")
    @TableField("NEW_NAME")
    private String newName;

    @ApiModelProperty(value = "附件类型")
    @TableField("TYPE_")
    private String type;

    @ApiModelProperty(value = "文件类型：jpg；jpeg；png等")
    @TableField("FILE_TYPE")
    private String fileType;

    @ApiModelProperty(value = "类型存储：1 本地；2 腾讯云")
    @TableField("STORAGE_TYPE")
    private Integer storageType;

    @ApiModelProperty(value = "缩略图url")
    @TableField("THUMBNAIL_URL")
    private String thumbnailUrl;

    @ApiModelProperty(value = "附件路径：附件相对路径")
    @TableField("PATH_")
    private String path;

    @ApiModelProperty(value = "附件链接")
    @TableField("URL_")
    private String url;

    @ApiModelProperty(value = "图片位置")
    @TableField("POSITION")
    private String position;

    @ApiModelProperty(value = "排序")
    @TableField("SORT_INDEX")
    private Integer sortIndex;

    @TableField("ID")
    private String id;

    @TableField("BUCKET_NAME")
    private String bucketName;

}
