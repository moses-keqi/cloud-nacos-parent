package com.moses.cloud.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moses.cloud.security.po.User;
import com.moses.cloud.security.vo.ExpireVo;
import com.moses.cloud.security.vo.UserWithRoleVO;

import java.util.List;

/**
 *
 * 用户信息
 * @Author HanKeQi
 * @Date 2020/12/28 下午9:37
 * @Version 1.0
 **/
public interface IUserService extends IService<User> {

    /**
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    User findByUsernameAndOrgType(String username, String orgType);

    void batchDeleteUser(List<String> ids);

    /**
     * 功能：设置过期时间
     * @param expire
     * @return
     */
    void setExpireTime(ExpireVo expire);

    /**
     * 功能：取消过期时间
     * @param userId
     * @return
     */
    void cancelExpireTime(String userId);

    /**
     * 功能：锁定账户
     * 作者：gjy
     * @param userId
     * @return
     */
    void lock(String userId);

    /**
     * 功能：解锁账号
     * 作者：gjy
     * @param userId
     * @return
     */
    void unlock(String userId);


    /**
     * 功能：查询用户与角色关联
     * 作者：gjy
     * @param userId
     * @param appType
     * @return
     */
    List<UserWithRoleVO> findUserWithRoles(String userId, String appType);

    /**
     * 功能：保存用户与角色关联
     * 作者：gjy
     * @param userWithRoles
     * @return
     */
    void saveUserWithRoles(List<UserWithRoleVO> userWithRoles);

    /**
     * 功能：根据用户查询已绑定角色
     * 作者：gjy
     * @param userId
     * @return
     */
    List<UserWithRoleVO> findRoleByUser(String userId);

    /**
     * 功能：根据用户删除与角色关联关系
     * 作者：gjy
     * @param userId
     */
    void deleteUserRoleById(String userId);

    /**
     * 功能：根据用户ID和角色ID删除与角色关联关系
     * 作者：gjy
     * @param userId
     * @param roleId
     */
    void deleteByUserRoleId(String userId, String roleId);

}
