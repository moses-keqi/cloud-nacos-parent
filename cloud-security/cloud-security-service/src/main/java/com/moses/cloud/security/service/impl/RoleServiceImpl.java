package com.moses.cloud.security.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moses.cloud.commons.exception.BusinessException;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.utils.SecurityUtils;
import com.moses.cloud.security.form.RoleConditionForm;
import com.moses.cloud.security.form.RoleWithPermissionForm;
import com.moses.cloud.security.mapper.RoleMapper;
import com.moses.cloud.security.mapper.RoleMenuMapper;
import com.moses.cloud.security.mapper.RoleResourceMapper;
import com.moses.cloud.security.mapper.UserRoleMapper;
import com.moses.cloud.security.po.Role;
import com.moses.cloud.security.po.RoleMenu;
import com.moses.cloud.security.po.RoleResource;
import com.moses.cloud.security.po.UserRole;
import com.moses.cloud.security.service.IRoleService;
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
 * @Date 2020/12/30 上午10:55
 * @Version 1.0
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 功能 ：分页查询 作者 ： LPF
     */
    @Override
    public IPage<Role> pageByCondition(RoleConditionForm roleCondition) {
        return roleMapper.pageByCondition(roleCondition.toPage(), roleCondition);
    }

    /**
     * 功能：角色新增
     *
     */
    @Override
    public void insertRole(Role role) {
        if (role != null && role.getRoleString() != null) {
            Role result = roleMapper.queryRole(role.getRoleString());
            if (result != null) {
                throw new BusinessException(ExceptionMsg.ROLE_ERROR_001_CODE);
            }
        }
        role.setDeleted(false);
        role.setCreateBy(SecurityUtils.getCurrentUserId());
        role.setCreateName(SecurityUtils.getCurrentName());
        role.setCreateTime(new Date());
        roleMapper.insert(role);
    }

    /**
     * 功能：角色修改
     *
     */
    @Override
    public void updateRole(Role role) {
        if (StringUtils.isBlank(role.getId())) {
            throw new BusinessException(ExceptionMsg.ROLE_ERROR_002_CODE);
        }
        if (role != null && role.getRoleString() != null) {
            Role result = roleMapper.queryRole(role.getRoleString());
            if (result != null && !result.getId().equals(role.getId())) {
                throw new BusinessException(ExceptionMsg.ROLE_ERROR_001_CODE);
            }
        }
        role.setUpdateBy(SecurityUtils.getCurrentUserId());
        role.setUpdateName(SecurityUtils.getCurrentUsername());
        role.setUpdateTime(new Date());
        roleMapper.updateById(role);
    }

    /**
     * 功能：删除
     */
    @Override
    public void deleteById(String id) {
        if(null==id || "".equals(id)) {
            throw new BusinessException(ExceptionMsg.ROLE_ERROR_002_CODE);
        }
        //1.查询角色是否被使用
        List<UserRole> userRoles = userRoleMapper.selectByRoleId(id);
        if(null!=userRoles && 0<userRoles.size()) {
            throw new BusinessException(ExceptionMsg.ROLE_ERROR_003_CODE);
        }
        //2.删除角色
        roleMapper.deleteById(id);
        //3.删除角色菜单关联关系
        roleMenuMapper.deleteByRoleId(id);
        //4.删除角色资源关联关系
        roleResourceMapper.deleteByRoleId(id);
    }

    /**
     * 功能：通过ID查询
     *
     */
    @Override
    public Role selectById(String id) {
        return roleMapper.selectById(id);
    }

    /**
     * 功能：是否可用（0 不可用，1 可用） 作者：LPF
     *
     */
    @Override
    public void updateDisabled(String id, boolean disabled) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException(ExceptionMsg.ROLE_ERROR_002_CODE);
        }
        Role role = this.selectById(id);
        role.setDisabled(disabled);
        role.setUpdateBy(SecurityUtils.getCurrentUserId());
        role.setUpdateName(SecurityUtils.getCurrentUsername());
        role.setUpdateTime(new Date());
        roleMapper.updateById(role);
    }

    /**
     * 功能：根据类型查询角色
     *
     * @param appType 应用类型 （pc ，pad）
     */
    @Override
    public List<Role> selectAllRole(String appType) {
        List<Role> roleList = new ArrayList<Role>();

        return roleList;
    }

    @Override
    public List<Role> findByUserIdAndAppType(String userId, String appType) {
        return baseMapper.findByUserIdAndAppType(userId, appType);
    }

    /**
     * 功能：保存角色与权限关联 作者：GJY
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRoleWithPermission(RoleWithPermissionForm form) {
        String roleId = form.getRoleId();
        // 1.删除角色与菜单关联关系
        roleMenuMapper.deleteByRoleId(roleId);

        // 2.删除角色与资源关联关系
        roleResourceMapper.deleteByRoleId(roleId);

        // 3.保存角色与菜单关联关系
        if (form.getMenuIds() != null && form.getMenuIds().size() > 0) {
            for (String menuId : form.getMenuIds()) {
                RoleMenu m = new RoleMenu();
                m.setRoleId(roleId);
                m.setMenuId(menuId);
                roleMenuMapper.insert(m);
            }
        }

        // 4.保存角色与资源关联关系
        if (form.getResourceIds() != null && CollectionUtils.isNotEmpty(form.getResourceIds())) {
            for (String resourceId : form.getResourceIds()) {
                RoleResource m = new RoleResource();
                m.setRoleId(roleId);
                m.setResourceId(resourceId);
                roleResourceMapper.insert(m);
            }
        }

    }

    @Override
    public Role findByRoleString(String roleString) {
        return this.baseMapper.findByRoleString(roleString);
    }

    @Override
    public int findUserRole(String userId,String roleId) {
        return this.baseMapper.findUserRole(userId, roleId);
    }

}
