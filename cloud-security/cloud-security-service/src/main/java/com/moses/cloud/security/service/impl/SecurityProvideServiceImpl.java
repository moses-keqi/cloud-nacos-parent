package com.moses.cloud.security.service.impl;

import com.moses.cloud.commons.exception.BusinessException;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.security.SecurityConstants;
import com.moses.cloud.commons.security.model.SecurityModel;
import com.moses.cloud.commons.utils.BeanUtils;
import com.moses.cloud.commons.utils.DictUtils;
import com.moses.cloud.commons.utils.ServletUtils;
import com.moses.cloud.commons.vo.CacheDictVo;
import com.moses.cloud.security.mapper.RoleMapper;
import com.moses.cloud.security.po.Dict;
import com.moses.cloud.security.po.Role;
import com.moses.cloud.security.po.User;
import com.moses.cloud.security.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 下午1:23
 * @Version 1.0
 **/
@Service
public class SecurityProvideServiceImpl implements ISecurityProvideService {

    @Resource
    protected ISecurityService securityService;

    @Resource
    protected IUserService userService;

    @Resource
    private RoleMapper roleMapper;

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IDictService dictService;

    @Override
    public String getAppType() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader(SecurityConstants.CLIENT);
    }

    @Override
    public SecurityModel findUserByUsernameAndOrgType(String username, String orgType) {
        User user = userService.findByUsernameAndOrgType(username, orgType);

        boolean roleString = getRoleString(user.getId());
        SecurityModel securityUser = BeanUtils.copy(user, SecurityModel.class);
        securityUser.setAdmin(roleString);
        securityUser.setFullName(user.getName());
        if (SecurityConstants.OUT_SIDE.equals(orgType)){
            try {
                String client = ServletUtils.getHeader(SecurityConstants.CLIENT);
                String deviceId = ServletUtils.getHeader(SecurityConstants.DEVICE_ID);
                securityUser.setClient(client);
                securityUser.setDeviceId(deviceId);
                boolean vCode = deviceService.sendSmsDevice(username, user.getPhoneNo(), client, deviceId);
                securityUser.setVCode(vCode);
            }catch (Exception e){
                throw new BusinessException(ExceptionMsg.DEVICE_NULL);
            }
        }
        return securityUser;
    }

    @Override
    public Set<String> findResourceByUsername(String username) {
        User user = securityService.findByUsername(username);
        Set<String> resources = securityService.findResourceAuthorizationCode(user.getId(),getAppType());
        Set<String> menus = securityService.findMenuAuthorizationCode(user.getId(),getAppType());
        Set<String> data = new HashSet<String>();
        if(CollectionUtils.isNotEmpty(resources)){
            data.addAll(resources);
        }
        if(CollectionUtils.isNotEmpty(menus)){
            data.addAll(menus);
        }
        return data;
    }

    @Override
    public Set<String> findRoleByUserName(String username) {
        User user = securityService.findByUsername(username);
        return securityService.findRoleAuthorizationCode(user.getId(),getAppType());
    }

    @Override
    public void lockUser(String userId, String username, String name , Date lockTime) {
        securityService.lockUser(userId, username,name,lockTime);
    }

    @Override
    public void unLockUser(String userId, String username, String name) {
        securityService.unLockUser(userId, username, name);
    }

    @Override
    public String getDictValue(String code) {
        CacheDictVo vo = DictUtils.findByCode(code);
        if(vo == null){
            return null;
        }
        return vo.getDictValue();
    }

    @Override
    public CacheDictVo getCacheDictVo(String code) {
        CacheDictVo vo = DictUtils.findByCode(code);
        if (vo == null){
            Dict dict = dictService.findByCode(code);
            if (dict != null){
                return BeanUtils.copy(dict, CacheDictVo.class);
            }
        }
        return null;
    }

    @Override
    public List<String> listDictCode(String parentCode) {
        List<CacheDictVo> children = DictUtils.findByCategoryCode(parentCode);
        if(children != null){
            List<String> codeList = children.stream().map(CacheDictVo::getCode).collect(Collectors.toList());
            return codeList;
        }
        return null;
    }

    @Override
    public List<CacheDictVo> listCacheDictVo(String parentCode) {
        List<CacheDictVo> children = DictUtils.findByCategoryCode(parentCode);
        if (CollectionUtils.isEmpty(children)){
            List<Dict> dictList = dictService.findByParentCode(parentCode);
            if (CollectionUtils.isNotEmpty(dictList)){
                return BeanUtils.copyList(dictList, CacheDictVo.class);
            }
        }
        return null;
    }

    @Override
    public void log(String type, String username, String name, String requestIp,String utoken) {

    }

    @Override
    public Boolean getRoleString(String userId) {
        Boolean isAdmin = Boolean.FALSE;
        List<Role> roleList= roleMapper.findByUserId(userId);
        if (roleList.size()!=0) {
            if( roleList.stream().filter(r -> "admin".equals(r.getRoleString())).findFirst().orElse(null)!=null) {
                isAdmin = Boolean.TRUE;
            }
        }
        return isAdmin;
    }
}
