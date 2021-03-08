package com.moses.cloud.security.form;

import com.moses.cloud.commons.vo.AbstractVo;
import lombok.Data;

import java.util.List;

/**
 * 角色与权限关联
 * @Author HanKeQi
 * @Date 2020/12/30 上午10:54
 * @Version 1.0
 **/
@Data
public class RoleWithPermissionForm extends AbstractVo {

    private String roleId;

    private List<String> menuIds;

    private List<String> resourceIds;
}
