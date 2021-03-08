package com.moses.cloud.security.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.moses.cloud.orm.entity.AbstractPo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:31
 * @Version 1.0
 **/
@Data
@TableName("SEC_MENU_RESOURCE")
@ApiModel(value="MenuResource对象", description="")
public class MenuResource extends AbstractPo {

    private String menuId;

    private String resourceId;
}
