package com.moses.cloud.security.form;


import com.moses.cloud.orm.form.AbstractPageConditionForm;
import com.moses.cloud.security.vo.FindAboutVersionAppVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sunxiangkai
 */
@Data
public class FindAboutVersionAppForm extends AbstractPageConditionForm<FindAboutVersionAppVo> {

    @ApiModelProperty("版本")
    private Float version;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("发布状态（false-未上架；true -已上架）")
    private Boolean enabled;

    /**
     * H5 升级 zip升级
     * IOS 升级 ipa
     * ANDROID 升级 apk 升级
     */
    @ApiModelProperty("类型: h5、ios 、android")
    private String client;


}
