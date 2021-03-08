package com.moses.cloud.security.service;

import com.moses.cloud.commons.security.model.SecurityModel;
import com.moses.cloud.commons.vo.CacheDictVo;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 提供Service
 * @Author HanKeQi
 * @Date 2020/12/30 下午1:20
 * @Version 1.0
 **/
public interface ISecurityProvideService {

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
    SecurityModel findUserByUsernameAndOrgType(String username, String orgType);

    /**
     * 查询用户在特定系统下的资源
     * @param username 用户名
     * @return
     */
    Set<String> findResourceByUsername(String username);

    default Set<String> findResourceByUsername(String username, String orgType){
        SecurityModel securityUser = findUserByUsernameAndOrgType(username, orgType);
        return findResourceByUsername(securityUser.getUsername());
    }

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
     * 获取code CacheDictVo
     * @param code
     * @return
     */
    CacheDictVo getCacheDictVo(String code);




    /**
     * 字典集合
     * @param parentCode
     * @return
     */
    List<String> listDictCode(String parentCode);

    /**
     * 字典集合
     * @param parentCode
     * @return
     */
    List<CacheDictVo> listCacheDictVo(String parentCode);

    /**
     * 登陆/登出日志
     * @param type 日志类型  login 登陆 logout 登出
     * @param username	账号
     * @param name	名称
     * @param requestIp	IP
     * @param utoken 令牌
     * @return
     */
    void log(String type, String username, String name, String requestIp, String utoken);

    /**
     * 获取用户角色是否是超级管理员
     * @param userId
     * @return
     */
    Boolean getRoleString(String userId);

}
