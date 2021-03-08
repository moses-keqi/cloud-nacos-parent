package com.moses.cloud.security.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.moses.cloud.orm.crypt.annotation.CryptField;
import com.moses.cloud.orm.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author HanKeQi
 * @Date 2020/12/25 下午9:02
 * @Version 1.0
 **/
@Data
@TableName("SEC_USER")
@ApiModel(value="SecUser对象", description="")
public class User extends AbstractEntity {

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

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "加密盐")
    private String salt;

    @ApiModelProperty(value = "性别（man 男 ，woman 女）")
    private String sex;

    @ApiModelProperty(value = "手机号")
    @CryptField
    private String phoneNo;

    @ApiModelProperty(value = "邮箱")
    @CryptField
    private String email;

    @ApiModelProperty(value = "是否锁定 （0 未锁定，1 已锁定）")
    private Boolean locked;

    @ApiModelProperty(value = "锁定时间")
    private Date lockedTime;

    @ApiModelProperty(value = "是否过期（0 未过期，1 已过期）")
    private Boolean expired;

    @ApiModelProperty(value = "过期时间")
    private Date expireTime;

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
    
    @ApiModelProperty(value = "头像url")
    private String avatarUrl;

}
