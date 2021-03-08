package com.moses.cloud.security.controller;

import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.security.model.SecurityModel;
import com.moses.cloud.security.service.ISecurityProvideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 下午1:45
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/api/security")
public class SecurityProvideController {

    @Autowired
    private ISecurityProvideService securityProvideService;

    /**
     * 获取系统类型
     * @return
     */
    @PostMapping(value = "getAppType")
    public String getAppType(){
        return securityProvideService.getAppType();
    }

    /**
     * 根据用户名与类型查询
     * @param username
     * @param orgType
     * @return
     */
    @PostMapping(value = "findUserByUsernameAndOrgType")
    public ResponseData<SecurityModel> findUserByUsernameAndOrgType(String username, String orgType){
        SecurityModel userByUsernameAndOrgType = securityProvideService.findUserByUsernameAndOrgType(username, orgType);
        return ResponseData.newInstanceOfSuccess(userByUsernameAndOrgType);
    }

    /**
     * 查询用户在特定系统下的资源
     * @param username 用户名
     * @return
     */
    @PostMapping(value = "findResourceByUsername")
    public Set<String> findResourceByUsername(String username){
        return securityProvideService.findResourceByUsername(username);
    }

    /**
     * 查询用户特定系统下的角色
     * @param username 用户名
     * @return
     */
    @PostMapping(value = "findRoleByUserName")
    public Set<String> findRoleByUserName(String username){
        return securityProvideService.findRoleByUserName(username);
    }

    /**
     * 锁定用户
     * @param userId
     * @param username
     * @param name
     * @param lockTime
     */
    @PostMapping(value = "lockUser")
    public void lockUser(String userId, String username, String name , Date lockTime){
        securityProvideService.lockUser(userId, username, name, lockTime);
    }

    /**
     * 解锁用户
     * @param userId
     */
    @PostMapping(value = "unLockUser")
    public void unLockUser(String userId, String username, String name){
        securityProvideService.unLockUser(userId, username, name);
    }

    /**
     * 获取字典值
     * @return
     */
    @PostMapping(value = "getDictValue")
    public String getDictValue(String code){
        return securityProvideService.getDictValue(code);
    }

    @PostMapping(value = "listDictCode")
    public List<String> listDictCode(String parentCode){
        return securityProvideService.listDictCode(parentCode);
    }


//    /**
//     * 获取字典值
//     * @return
//     */
//    @RequestMapping(value = "getCacheDictVo")
//    public CacheDictVo getCacheDictVo(String code){
//        return securityProvideService.getCacheDictVo(code);
//    }
//
//    @RequestMapping(value = "listCacheDictVo")
//    public List<CacheDictVo> listCacheDictVo(String parentCode){
//        return securityProvideService.listCacheDictVo(parentCode);
//    }


}
