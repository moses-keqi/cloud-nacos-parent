package com.moses.cloud.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moses.cloud.security.po.MenuResource;

import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:31
 * @Version 1.0
 **/
public interface MenuResourceMapper extends BaseMapper<MenuResource> {

    /**
     * 根据菜单ID删除菜单下的所有资源
     * @param menuId
     */
    void deleteByMenuId(String menuId);

    /**
     * 批量插入菜单资源关系
     * @param menuResources
     */
    void insertList(List<MenuResource> menuResources);

    /**
     * 功能：查询菜单资源绑定关系
     * @return
     */
    List<MenuResource> findAllMenuResource();

    /**
     * 功能：根据MenuId查询菜单资源绑定关系
     * @return
     */
    List<MenuResource> findByMenuId(String menuId);

    /**
     * 功能：根据ResourceId查询菜单资源绑定关系
     * @return
     */
    List<MenuResource> findByResourceId(String resourceId);
}
