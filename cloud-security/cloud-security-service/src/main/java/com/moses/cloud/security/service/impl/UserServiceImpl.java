package com.moses.cloud.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moses.cloud.commons.exception.BusinessException;
import com.moses.cloud.commons.utils.SecurityUtils;
import com.moses.cloud.security.mapper.RoleMapper;
import com.moses.cloud.security.mapper.UserMapper;
import com.moses.cloud.security.po.Role;
import com.moses.cloud.security.po.User;
import com.moses.cloud.security.service.IUserService;
import com.moses.cloud.security.vo.ExpireVo;
import com.moses.cloud.security.vo.UserWithRoleVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 下午9:44
 * @Version 1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }

    @Override
    public User findByUsernameAndOrgType(String username, String orgType) {
        return baseMapper.findByUsernameAndOrgType(username, orgType);
    }

    @Override
    public void batchDeleteUser(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)){
            this.removeByIds(ids);
        }
    }

    @Override
    public void setExpireTime(ExpireVo expire) {
        User user = baseMapper.selectById(expire.getUserId());
        user.setUpdateBy(SecurityUtils.getCurrentUserId());
        user.setUpdateName(SecurityUtils.getCurrentName());
        user.setUpdateTime(new Date());
        Date dtime = new Date();
        //比较当前时间，如果早于当前时间则过期
        if (expire.getExpireTime().after(dtime)) {
            user.setExpired(false);
            user.setExpireTime(expire.getExpireTime());
        } else {
            user.setExpired(true);
            user.setExpireTime(expire.getExpireTime());
        }
        baseMapper.setExpireTime(user);
    }

    @Override
    public void cancelExpireTime(String userId) {
        User user = baseMapper.selectById(userId);
        user.setUpdateBy(SecurityUtils.getCurrentUserId());
        user.setUpdateName(SecurityUtils.getCurrentName());
        user.setUpdateTime(new Date());
        user.setExpired(false);
        baseMapper.cancelExpireTime(user);
    }

    @Override
    public void lock(String userId) {
        User user = baseMapper.selectById(userId);
        user.setUpdateBy(SecurityUtils.getCurrentUserId());
        user.setUpdateName(SecurityUtils.getCurrentName());
        user.setUpdateTime(new Date());
        user.setLocked(true);
        user.setLockedTime(new Date());
        baseMapper.lock(user);
    }

    @Override
    public void unlock(String userId) {
        User user = baseMapper.selectById(userId);
        user.setUpdateBy(SecurityUtils.getCurrentUserId());
        user.setUpdateName(SecurityUtils.getCurrentName());
        user.setUpdateTime(new Date());
        user.setLocked(false);
        user.setLockedTime(null);
        baseMapper.unlock(user);
    }

    @Override
    public List<UserWithRoleVO> findUserWithRoles(String userId, String appType) {
        List<UserWithRoleVO> userWithRoleVOList = new ArrayList<UserWithRoleVO>();
        if(StringUtils.isBlank(userId)) {
            throw new BusinessException("10001", "用户ID不能为空");
        }
        // 查询用户类型
        User user = this.getById(userId);


        List<Role> roleList = new ArrayList<Role>();
        List<Role> userRoleList = new ArrayList<Role>();
        roleList= roleMapper.selectAllRole(appType);//根据类型查询所有的有效角色
        userRoleList = roleMapper.findByUserId(userId);//根据userId查询角色
        for (Role r : roleList) {
            UserWithRoleVO userWithRoleVO = new UserWithRoleVO();
            userWithRoleVO.setRoleId(r.getId());
            userWithRoleVO.setRoleName(r.getName());
            userWithRoleVO.setUserId(userId);
            userWithRoleVO.setOwn(false);
            userWithRoleVO.setAppType(r.getAppType());
            for (Role r2 : userRoleList) {
                if (r2.getId().equals(r.getId())) {
                    userWithRoleVO.setOwn(true);
                }
            }
            userWithRoleVOList.add(userWithRoleVO);
        }
        return userWithRoleVOList;
    }

    @Override
    public void saveUserWithRoles(List<UserWithRoleVO> userWithRoles) {

    }

    @Override
    public List<UserWithRoleVO> findRoleByUser(String userId) {
        return null;
    }

    @Override
    public void deleteUserRoleById(String userId) {

    }

    @Override
    public void deleteByUserRoleId(String userId, String roleId) {

    }
    
}
