package com.moses.cloud.security.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moses.cloud.commons.validator.NotNullOrEmpty;
import com.moses.cloud.orm.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzs
 * @since 2021-02-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("SYS_COMMON_FUNCTION")
@ApiModel(value="SysCommonFunction对象", description="")
public class SysCommonFunction extends AbstractEntity {

    @NotNullOrEmpty(message = "按钮类型不能为空")
    @TableField("FUNC_TYPE")
    private String funcType;

    @NotNullOrEmpty(message = "按钮名字不能为空")
    @TableField("FUNC_NAME")
    private String funcName;

    @TableField("LINK_TYPE")
    private String linkType;

    @NotNullOrEmpty(message = "按钮链接不能为空")
    @TableField("LINK_URL")
    private String linkUrl;

    @NotNullOrEmpty(message = "按钮图标不能为空")
    @TableField("FUNC_ICO")
    private String funcIco;

    @NotNullOrEmpty(message = "按钮是否启用不能为空")
    @TableField("ENABLED")
    private Boolean enabled;

    @NotNullOrEmpty(message = "按钮排序不能为空")
    @TableField("SORT")
    private Integer sort;

    @NotNullOrEmpty(message = "按钮是否固定不能为空")
    @TableField("IS_COMMON")
    private Boolean isCommon;


}
