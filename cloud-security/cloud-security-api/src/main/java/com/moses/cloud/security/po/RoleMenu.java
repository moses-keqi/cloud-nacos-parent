package com.moses.cloud.security.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moses.cloud.orm.entity.AbstractPo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:01
 * @Version 1.0
 **/
@Data
@TableName("SEC_ROLE_MENU")
@ApiModel(value="RoleMenu对象", description="")
public class RoleMenu extends AbstractPo {

    @TableField("ROLE_ID")
    private String roleId;

    @TableField("MENU_ID")
    private String menuId;
}
