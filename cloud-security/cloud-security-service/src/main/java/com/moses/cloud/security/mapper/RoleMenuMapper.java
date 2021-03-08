package com.moses.cloud.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moses.cloud.security.po.RoleMenu;

import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:00
 * @Version 1.0
 **/
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     *
     * 功能：根据角色ID删除角色菜单绑定关系
     *
     * @param roleId
     */
    void deleteByRoleId(String roleId);

    /**
     *
     * 功能：根据角色ID查询角色菜单绑定关系
     *
     * @param roleId
     */
    List<RoleMenu> findByRoleId(String roleId);

    /**
     *
     * 功能：根据菜单ID查询角色菜单绑定关系
     *
     * @param menuId
     */
    List<RoleMenu> findByMenuId(String menuId);
}
