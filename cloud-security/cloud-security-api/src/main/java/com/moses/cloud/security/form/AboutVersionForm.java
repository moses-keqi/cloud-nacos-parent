package com.moses.cloud.security.form;

import com.moses.cloud.commons.vo.AbstractVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author HanKeQi
 * @Date 2021/2/1 下午2:53
 * @Version 1.0
 **/
@Data
public class AboutVersionForm extends AbstractVo {

//    /**
//     * 小版本每次小数点后最后一位加一, 大版本小数前加一
//     */
//    @ApiModelProperty("版本号")
//    private Float version;

    @ApiModelProperty("终端类型：h5 ios android")
    @NotEmpty
    private String client;
}
