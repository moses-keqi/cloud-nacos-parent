package com.moses.cloud.security.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.moses.cloud.orm.entity.AbstractPo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午10:58
 * @Version 1.0
 **/
@Data
@TableName("SEC_USER_ROLE")
@ApiModel(value="UserRole对象", description="")
public class UserRole extends AbstractPo {

    private String userId;

    private String roleId;
}
