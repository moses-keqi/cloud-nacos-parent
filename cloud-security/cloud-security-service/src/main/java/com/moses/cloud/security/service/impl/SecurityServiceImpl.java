package com.moses.cloud.security.service.impl;

import com.google.common.collect.Lists;
import com.moses.cloud.security.po.Menu;
import com.moses.cloud.security.po.Role;
import com.moses.cloud.security.po.User;
import com.moses.cloud.security.service.*;
import com.moses.cloud.security.vo.MenuTreeVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 下午9:36
 * @Version 1.0
 **/
@Service
public class SecurityServiceImpl implements ISecurityService {

    @Resource
    private IUserService userService;
    @Resource
    private IResourceService resourceService;
    @Resource
    private IMenuService menuService;
    @Resource
    private IRoleService roleService;

//    @Override
//    public String getAppType() {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String appType = request.getHeader("app_type");
//        return appType;
//    }

    @Override
    public User findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @Override
    public User findByUsernameAndOrgType(String username, String orgType) {
        return userService.findByUsernameAndOrgType(username, orgType);
    }

    @Override
    public Set<String> findResourceAuthorizationCode(String userId, String appType) {
        // 用户所属角色
        List<Role> roles = roleService.findByUserIdAndAppType(userId, appType);
        if(CollectionUtils.isNotEmpty(roles)){
            Set<String> roleIds = roles.stream().map(Role::getId).collect(Collectors.toSet());
            // 用户所属资源
            List<com.moses.cloud.security.po.Resource> resources = resourceService.findByRoleIds(new ArrayList<>(roleIds));
            return resources.stream().map(com.moses.cloud.security.po.Resource::getPermissionString).collect(Collectors.toSet());
        }
        return null;
    }

    @Override
    public Set<String> findMenuAuthorizationCode(String userId,String appType) {
        // 用户所属角色
        List<Role> roles = roleService.findByUserIdAndAppType(userId, appType);
        if(CollectionUtils.isNotEmpty(roles)){
            Set<String> roleIds = roles.stream().map(Role::getId).collect(Collectors.toSet());
            // 用户所属菜单
            List<Menu> menus = menuService.findByRoleIds(new ArrayList<String>(roleIds));
            return menus.stream().map(Menu::getPermissionString).collect(Collectors.toSet());
        }
        return null;
    }

    @Override
    public Set<String> findRoleAuthorizationCode(String userId,String appType) {
        // 用户所属角色
        List<Role> roles = roleService.findByUserIdAndAppType(userId, appType);
        if(CollectionUtils.isNotEmpty(roles)){
            return roles.stream().map(Role::getRoleString).collect(Collectors.toSet());
        }
        return null;
    }

    /**
     * 查询用户菜单
     * @param userId
     * @param appType 应用类型
     */
    @Override
    public List<MenuTreeVo> findMenuByUserId(String userId, String appType) {
        // 用户所属角色
        List<Role> roles = roleService.findByUserIdAndAppType(userId, appType);
        if(CollectionUtils.isNotEmpty(roles)){
            Set<String> roleIds = roles.stream().map(Role::getId).collect(Collectors.toSet());
            // 用户所属菜单
            List<Menu> menus = menuService.findByRoleIds(new ArrayList<String>(roleIds));

            List<MenuTreeVo> menuTree = buildTree(menus);
            if(CollectionUtils.isNotEmpty(menuTree)){
                // 第一层级菜单为大类菜单，第二层级才是真实的菜单
                return menuTree.get(0).getChildren();
            }
        }
        return Lists.newArrayList();
    }

    /**
     * 组装菜单树
     * @param menus
     * @return
     */
    private List<MenuTreeVo> buildTree(List<Menu> menus){
        List<MenuTreeVo> menuTree = new ArrayList<MenuTreeVo>();
        for (Menu menu : menus) {
            if(StringUtils.isBlank(menu.getParentId())){
                MenuTreeVo vo = copyMenuToMenuTreeVo(menu);
                List<MenuTreeVo> child = buildTreeChild(menus, menu.getId());
                if(child != null && child.size() > 0){
                    vo.setChildren(child);
                }
                menuTree.add(vo);
            }
        }
        return menuTree;
    }

    /**
     * 递归组装子节点
     * @param menus
     * @param parentId
     * @return
     */
    private List<MenuTreeVo> buildTreeChild(List<Menu> menus,String parentId){
        List<MenuTreeVo> menuTree = new ArrayList<MenuTreeVo>();
        for (Menu menu : menus) {
            if(StringUtils.isNotBlank(menu.getParentId()) && menu.getParentId().equals(parentId)){
                MenuTreeVo vo = copyMenuToMenuTreeVo(menu);
                List<MenuTreeVo> child = buildTreeChild(menus, menu.getId());
                if(child != null && child.size() > 0){
                    vo.setChildren(child);
                }
                menuTree.add(vo);
            }
        }
        return menuTree;
    }

    /**
     * 复制属性至MenuTreeVo
     * @param menu
     * @return
     */
    private MenuTreeVo copyMenuToMenuTreeVo(Menu menu){
        MenuTreeVo menuTree = new MenuTreeVo();
        menuTree.setId(menu.getId());
        menuTree.setName(menu.getName());
        menuTree.setPermissionString(menu.getPermissionString());
        menuTree.setUrl(menu.getUrl());
        menuTree.setIcon(menu.getIcon());
        menuTree.setSortIndex(menu.getSortIndex());
        return menuTree;
    }

    @Override
    public void lockUser(String userId, String username, String name , Date lockTime) {
        User user = new User();
        user.setId(userId);
        user.setLocked(true);
        user.setLockedTime(lockTime);
        user.setUpdateBy(username);
        user.setUpdateName(name);
        userService.updateById(user);
    }

    @Override
    public void unLockUser(String userId, String username, String name) {
        //userService.unlockUser(userId, username, name);
    }


}
