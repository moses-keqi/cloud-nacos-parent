package com.moses.cloud.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moses.cloud.commons.exception.BusinessException;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.utils.SecurityUtils;
import com.moses.cloud.security.form.MenuConditionForm;
import com.moses.cloud.security.form.MenuWithResourceForm;
import com.moses.cloud.security.mapper.*;
import com.moses.cloud.security.po.*;
import com.moses.cloud.security.service.IMenuService;
import com.moses.cloud.security.vo.PermissionTreeVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:28
 * @Version 1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private MenuResourceMapper menuResourceMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public IPage<Menu> pageByCondition(MenuConditionForm condition) {
        return baseMapper.pageByCondition(condition.toPage(),condition);
    }

    @Override
    public void createMenu(Menu menu) {
        Menu existingMenu = baseMapper.findByPermissionString(menu.getPermissionString());
        if(existingMenu != null){
            throw new BusinessException(ExceptionMsg.MENU_ERROR_001_CODE);
        }
        menu.setCreateBy(SecurityUtils.getCurrentUsername());
        menu.setCreateName(SecurityUtils.getCurrentName());
        menu.setCreateTime(new Date());
        baseMapper.insert(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        Menu existingMenu = baseMapper.findByPermissionString(menu.getPermissionString());
        if(existingMenu != null && !existingMenu.getId().equals(menu.getId())){
            throw new BusinessException(ExceptionMsg.MENU_ERROR_001_CODE);
        }
        this.updateById(menu);
    }

    @Override
    public void deleteMenu(String id) {
        // TODO 级联删除用户权限可提示不可删除
        // 1：判断是否存在子级菜单
        List<Menu> menuList=menuMapper.finChildMenu(id);
        if(menuList != null && menuList.size()>0){
            throw new BusinessException(ExceptionMsg.MENU_ERROR_002_CODE);
        }
        // 2：判断是否与用户存在绑定关系
        List<RoleMenu> roleMenuList=roleMenuMapper.findByMenuId(id);
        if (roleMenuList != null && roleMenuList.size() > 0) {
            throw new BusinessException(ExceptionMsg.MENU_ERROR_003_CODE);
        }
        // 3：判断是否与资源存在绑定关系
        List<MenuResource> menuResourceList=menuResourceMapper.findByMenuId(id);
        if (menuResourceList != null && menuResourceList.size() > 0) {
            throw new BusinessException(ExceptionMsg.MENU_ERROR_004_CODE);
        }
        this.removeById(id);
    }

    @Override
    public List<Menu> findByRoleIds(List<String> roleIds) {
        return baseMapper.findByRoleIds(roleIds);
    }

    /**
     * 功能：查询权限树（角色与菜单资源）
     * 作者：GJY
     */
    @Override
    public List<PermissionTreeVo> findMenuRoleResource(String roleId, String appType) {
        List<PermissionTreeVo> voList = this.findMenu(appType);
        List<RoleMenu> rmList = roleMenuMapper.findByRoleId(roleId);
        List<RoleResource> rList = roleResourceMapper.findByRoleId(roleId);
        for (PermissionTreeVo vo : voList) {
            if ("menu".equals(vo.getType())) {
                for (RoleMenu rm : rmList) {
                    if (vo.getId().equals(rm.getMenuId())) {
                        vo.setChecked(true);
                    }
                }
            }
            if ("resource".equals(vo.getType())) {
                for (RoleResource rr : rList) {
                    if (vo.getId().equals(rr.getResourceId())) {
                        vo.setChecked(true);
                    }
                }
            }
        }

        return voList;
    }


    /**
     * 功能：查询权限树（菜单与资源）
     * 作者：GJY
     */
    @Override
    public List<PermissionTreeVo> findMenu(String appType){
        List<PermissionTreeVo> permissionTreeVoList = new ArrayList<PermissionTreeVo>();

        // 查询所有有效菜单
        List<Menu> menuList = menuMapper.findAllMenu(appType);
        // 查询所有有效资源（关联菜单关联关系）
        List<Resource> resourceList = resourceMapper.findAllResouce(appType);
        // 查询菜单资源绑定关系
        List<MenuResource> menuResourceList = menuResourceMapper.findAllMenuResource();

        for (Menu m : menuList) {
            PermissionTreeVo vo = new PermissionTreeVo();
            vo.setId(m.getId());
            vo.setName(m.getName());
            vo.setParentId(m.getParentId());
            vo.setType("menu");
            vo.setAppType(m.getAppType());
            permissionTreeVoList.add(vo);
        }
        for (MenuResource mr : menuResourceList) {
            PermissionTreeVo vo = new PermissionTreeVo();
            vo.setId(mr.getResourceId());
            vo.setParentId(mr.getMenuId());
            vo.setType("resource");
            permissionTreeVoList.add(vo);
        }
        for (PermissionTreeVo vo : permissionTreeVoList) {
            for (Resource r : resourceList) {
                if ("resource".equals(vo.getType()) && vo.getId().equals(r.getId())) {
                    vo.setName(r.getName());
                    vo.setAppType(r.getAppType());
                }
            }
        }

        return permissionTreeVoList;
    }

    @Override
    public void saveMenuWithResource(MenuWithResourceForm menuWithResourceVo) {
        //删除菜单下原有的资源
        menuResourceMapper.deleteByMenuId(menuWithResourceVo.getMenuId());
        //资源为空表示清空菜单下所有资源,不进行插入
        if(menuWithResourceVo.getResourceIds().size()==0) {
            return;
        }
        //封装参数
        List<MenuResource> menuResources = new ArrayList<MenuResource>();
        for(String resourceId : menuWithResourceVo.getResourceIds()) {
            MenuResource menuResource = new MenuResource();
            menuResource.setMenuId(menuWithResourceVo.getMenuId());
            menuResource.setResourceId(resourceId);
            menuResources.add(menuResource);
        }
        //保存新信息
        menuResourceMapper.insertList(menuResources);
    }

    @Override
    public List<Resource> findResourceByMenuId(String menuId)
    {
        List<Resource> rList = resourceMapper.findByMenuId(menuId);
        return rList;
    }

    @Override
    public void deleteBindByResourceIdAndMenuId(MenuWithResourceForm menuWithResourceVo) {
        if(StringUtils.isEmpty(menuWithResourceVo.getMenuId())) {
            return;
        }
        if(CollectionUtils.isEmpty(menuWithResourceVo.getResourceIds())) {
            return;
        }
        for(String resourceId : menuWithResourceVo.getResourceIds()) {
            LambdaQueryWrapper<MenuResource> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(MenuResource::getMenuId, menuWithResourceVo.getMenuId());
            lambdaQueryWrapper.eq(MenuResource::getResourceId, resourceId);
            menuResourceMapper.delete(lambdaQueryWrapper);
        }
    }

}
