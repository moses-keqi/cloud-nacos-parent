package com.moses.cloud.security.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.moses.cloud.orm.entity.AbstractPo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:03
 * @Version 1.0
 **/
@Data
@TableName("SEC_ROLE_RESOURCE")
@ApiModel(value="RoleResource对象", description="")
public class RoleResource extends AbstractPo {

    @ApiModelProperty(value = "角色表主键")
    private String roleId;

    @ApiModelProperty(value = "资源表主键")
    private String resourceId;
}
