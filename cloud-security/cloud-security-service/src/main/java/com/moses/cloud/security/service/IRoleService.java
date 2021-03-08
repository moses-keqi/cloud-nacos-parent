package com.moses.cloud.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moses.cloud.security.form.RoleConditionForm;
import com.moses.cloud.security.form.RoleWithPermissionForm;
import com.moses.cloud.security.po.Role;

import java.util.List;

/**
 * 角色表 服务类
 * @Author HanKeQi
 * @Date 2020/12/30 上午10:53
 * @Version 1.0
 **/
public interface IRoleService extends IService<Role> {

    /**
     * 功能 ：	通过条件查询分页
     * 作者： LPF
     */
    IPage<Role> pageByCondition(RoleConditionForm roleCondition);

    /**
     * 功能：角色新增
     * @param role
     */
    void  insertRole(Role role);

    /**
     * 功能：角色修改
     * @param role
     */
    void  updateRole(Role role);

    /**
     * 功能：是否可用（0 不可用，1 可用）
     *
     */
    void  updateDisabled(String id, boolean disabled);

    /**
     * 功能：删除
     * 作者：LPF
     */
    public void  deleteById(String id);

    /**
     * 功能：通过ID查询
     * 作者：LPF
     * @param resource
     */
    Role  selectById(String id);

    /**
     * 根据用户ID和角色类型查询
     * @param userId
     * @param appType
     * @return
     */
    List<Role> findByUserIdAndAppType(String userId, String appType);

    /**
     * 功能：根据类型查询角色
     * 作者：GJY
     * @param appType 应用类型 （pc ，pad）
     * @return
     */
    List<Role> selectAllRole(String appType);

    /**
     *
     * 功能：保存角色与权限关联
     * 作者： GJY
     *
     * @param form
     */
    void saveRoleWithPermission(RoleWithPermissionForm form);

    /**
     *
     * 功能：根据RoleString查询角色信息*
     * @param
     */
    Role findByRoleString(String roleString);

    /**
     *
     * 功能：根据角色Id，用户Id，查询是否存在关联关系
     * @param
     */
    int findUserRole(String userId, String roleId);
}
