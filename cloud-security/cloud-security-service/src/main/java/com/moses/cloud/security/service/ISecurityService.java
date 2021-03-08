package com.moses.cloud.security.service;

import com.moses.cloud.security.po.User;
import com.moses.cloud.security.vo.MenuTreeVo;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 用户权限认证
 * @Author HanKeQi
 * @Date 2020/12/28 下午9:34
 * @Version 1.0
 **/
public interface ISecurityService {


    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findByUsernameAndOrgType(String username, String orgType);

    /**
     * 根据用户ID与系统类型查询资源授权码
     * @param userId
     * @return
     */
    Set<String> findResourceAuthorizationCode(String userId, String appType);

    /**
     * 根据用户ID与系统类型查询菜单授权码
     * @return
     */
    Set<String> findMenuAuthorizationCode(String userId, String appType);

    /**
     * 根据用户ID与系统类型查询角色授权码
     */
    Set<String> findRoleAuthorizationCode(String userId, String appType);

    /**
     * 根据用户与系统类型查询菜单
     * @param userId
     * @return
     */
    List<MenuTreeVo> findMenuByUserId(String userId, String appType);

    /**
     * 锁定用户
     * @param userId
     * @param username
     * @param name
     * @param lockTime
     */
    public void lockUser(String userId, String username, String name, Date lockTime);

    /**
     * 解锁用户
     * @param userId
     * @param username
     * @param name
     */
    void unLockUser(String userId, String username, String name);


}
