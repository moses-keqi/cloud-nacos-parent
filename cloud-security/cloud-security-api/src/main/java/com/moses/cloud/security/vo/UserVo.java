package com.moses.cloud.security.vo;

import com.moses.cloud.commons.vo.AbstractVo;
import com.moses.cloud.orm.crypt.annotation.CryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author ：doublejia
 * @since : 2021/1/11
 */
@Data
public class UserVo extends AbstractVo {
    @ApiModelProperty(value = "机构类型 （inside 内勤 ，outside 外勤）")
    private String orgType;

    @ApiModelProperty(value = "分公司编码")
    private String branchCode;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "性别（man 男 ，woman 女）")
    private String sex;

    @ApiModelProperty(value = "手机号")
    @CryptField
    private String phoneNo;

    @ApiModelProperty(value = "邮箱")
    @CryptField
    private String email;

    @ApiModelProperty(value = "区部编码")
    private String districtCode;

    @ApiModelProperty(value = "区部名称")
    private String districtName;

    @ApiModelProperty(value = "机构编码")
    private String mechanismCode;

    @ApiModelProperty(value = "机构名称")
    private String mechanismName;

    @ApiModelProperty(value = "入职日期")
    private Date entryDate;
}
