package com.moses.cloud.security.form;

import com.moses.cloud.orm.form.AbstractForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2021/2/18 下午2:54
 * @Version 1.0
 **/
@Data
public class UploadForm extends AbstractForm {

    @ApiModelProperty(value = "关联id")
    private String objectId;
    @ApiModelProperty(value = "附件类型")
    private String type;
    @ApiModelProperty(value = "文件名称，支持pad上传时传递文件原始名称")
    private String name;
    @ApiModelProperty(value = "url")
    private String url;
}
