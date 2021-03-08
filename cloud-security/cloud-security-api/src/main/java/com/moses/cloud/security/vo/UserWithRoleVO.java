package com.moses.cloud.security.vo;

import com.moses.cloud.commons.vo.AbstractVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 下午9:40
 * @Version 1.0
 **/
@Data
@ApiModel(value="UserWithRoleVO对象", description="")
public class UserWithRoleVO extends AbstractVo {

    /** 用户ID */
    @ApiModelProperty(value="用户ID",required=true)
    private String userId;
    /** 角色ID */
    @ApiModelProperty(value="角色ID",required=true)
    private String roleId;
    /** 角色名称 */
    @ApiModelProperty(value="角色名称",required=false)
    private String roleName;
    /** 是否拥有 */
    @ApiModelProperty(value="是否拥有",required=true)
    private Boolean own;
    /** appType */
    @ApiModelProperty(value="appType",required=true)
    private String appType;
}
