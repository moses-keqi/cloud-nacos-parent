package com.moses.cloud.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moses.cloud.security.form.MenuConditionForm;
import com.moses.cloud.security.form.MenuWithResourceForm;
import com.moses.cloud.security.po.Menu;
import com.moses.cloud.security.po.Resource;
import com.moses.cloud.security.vo.PermissionTreeVo;

import java.util.List;

/**
 * 服务类
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:21
 * @Version 1.0
 **/
public interface IMenuService  extends IService<Menu> {
    /**
     * 分页查询
     * @param condition
     * @return
     */
    IPage<Menu> pageByCondition(MenuConditionForm condition);

    /**
     * 新增菜单
     * @param menu
     */
    public void createMenu(Menu menu);

    /**
     * 修改菜单
     * @param menu
     */
    void updateMenu(Menu menu);

    /**
     * 根据id删除
     * @param id
     */
    void deleteMenu(String id);


    /**
     * 根据角色ID查询
     * @param roleIds
     * @return
     */
    List<Menu> findByRoleIds(List<String> roleIds);

    /**
     *
     * 功能：查询权限树
     * 作者： GJY
     *
     * @return
     */
    List<PermissionTreeVo> findMenu(String appType);

    /**
     * 保存菜单下的资源
     * @param menuWithResourceForm
     */
    void saveMenuWithResource(MenuWithResourceForm menuWithResourceForm);

    /**
     *
     * 功能：查询权限树（角色与菜单资源）
     *
     * @param roleId
     * @return
     */
    List<PermissionTreeVo> findMenuRoleResource(String roleId, String appType);

    /**
     *
     * 功能：根据菜单Id查询绑定的资源*
     * @param menuId
     * @return
     */
    List<Resource> findResourceByMenuId(String menuId);


    /**
     * 功能：根据菜单Id与资源Id删除菜单资源绑定关系
     * @param menuWithResourceForm
     */
    void deleteBindByResourceIdAndMenuId(MenuWithResourceForm menuWithResourceForm);
}
