package com.moses.cloud.security.client;

import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.security.model.SecurityModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author HanKeQi
 * @Date 2020/12/26 下午4:02
 * @Version 1.0
 **/
@FeignClient(name="security-service",contextId="securityProvideClient")
public interface ISecurityProvideClient {

    /**
     * 获取系统类型
     * @return
     */
    @PostMapping(value = "/api/security/getAppType")
    String getAppType();

    /**
     * 根据用户名与类型查询
     * @param username
     * @param orgType
     * @return
     */
    @PostMapping(value = "/api/security/findUserByUsernameAndOrgType")
    ResponseData<SecurityModel> findUserByUsernameAndOrgType(@RequestParam("username") String username, @RequestParam("orgType") String orgType);

    /**
     * 查询用户在特定系统下的资源
     * @param username 用户名
     * @return
     */
    @PostMapping(value = "/api/security/findResourceByUsername")
    Set<String> findResourceByUsername(@RequestParam("username") String username);

    /**
     * 查询用户特定系统下的角色
     * @param username 用户名
     * @return
     */
    @PostMapping(value = "/api/security/findRoleByUserName")
    Set<String> findRoleByUserName(@RequestParam("username") String username);

    /**
     * 锁定用户
     * @param userId
     * @param username
     * @param name
     * @param lockTime
     */
    @PostMapping(value = "/api/security/lockUser")
    void lockUser(@RequestParam("userId") String userId, @RequestParam("username") String username, @RequestParam("name") String name, @RequestParam("lockTime") Date lockTime);

    /**
     * 解锁用户
     * @param userId
     */
    @PostMapping(value = "/api/security/unLockUser")
    void unLockUser(@RequestParam("userId") String userId, @RequestParam("username") String username, @RequestParam("name") String name);

    /**
     * 获取字典值
     * @return
     */
    @PostMapping(value = "/api/security/getDictValue")
    String getDictValue(@RequestParam("code") String code);

    @PostMapping(value = "/api/security/listDictCode")
    List<String> listDictCode(@RequestParam("parentCode") String parentCode);

    /**
     * 中台
     * @param username 用户名密码
     * @param password
     * @return
     */
    @PostMapping(value = "/api/security/getMiddleToken")
    Boolean getMiddleToken(@RequestParam("username") String username, @RequestParam("password") String password);

//
//    @PostMapping(value = "/api/security/getCacheDictVo")
//    CacheDictVo getCacheDictVo(@RequestParam("code")String code);
//
//    @PostMapping(value = "/api/security/listCacheDictVo")
//    List<CacheDictVo> listCacheDictVo(@RequestParam("parentCode")String parentCode);


}
