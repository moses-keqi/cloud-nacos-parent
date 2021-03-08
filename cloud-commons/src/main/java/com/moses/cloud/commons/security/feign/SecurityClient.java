package com.moses.cloud.commons.security.feign;

import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.security.model.SecurityModel;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * 用户以及权限数据库操作
 * @Author HanKeQi
 * @Date 2020/12/26 下午12:50
 * @Version 1.0
 **/
public interface SecurityClient {


    /**
     * 获取系统类型
     * @return
     */
    String getAppType();

    /**
     * 根据用户名与类型查询
     * @param username
     * @param orgType
     * @return
     */
    ResponseData<SecurityModel> findUserByUsernameAndOrgType(String username, String orgType);

    /**
     * 查询用户在特定系统下的资源
     * @param username 用户名
     * @return
     */
    Set<String> findResourceByUsername(String username);

    /**
     * 查询用户特定系统下的角色
     * @param username 用户名
     * @return
     */
    Set<String> findRoleByUserName(String username);

    /**
     * 锁定用户
     * @param userId
     * @param username
     * @param name
     * @param lockTime
     */
    void lockUser(String userId, String username, String name, Date lockTime);

    /**
     * 解锁用户
     * @param userId
     */
    void unLockUser(String userId, String username, String name);

    /**
     * 获取字典值
     * @return
     */
    String getDictValue(String code);

    /**
     * 返回集合value
     * @param parentCode
     * @return
     */
    List<String> listDictCode(String parentCode);


    /**
     * 中台登录获token
     * @param username
     * @param password
     * @return
     */
    Boolean getMiddleToken(String username, String password);
//    /**
//     * 返回对象
//     * @param code
//     * @return
//     */
//    CacheDictVo getCacheDictVo(String code);
//
//    /**
//     * 获取list 对象
//     * @param parentCode
//     * @return
//     */
//    List<CacheDictVo> listCacheDictVo(String parentCode);

}
