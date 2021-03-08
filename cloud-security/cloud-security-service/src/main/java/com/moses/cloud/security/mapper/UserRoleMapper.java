package com.moses.cloud.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moses.cloud.security.po.UserRole;

import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午10:57
 * @Version 1.0
 **/
public interface UserRoleMapper extends BaseMapper<UserRole> {

        /**
         *
         * 功能：根据用户ID删除关联关系
         *
         * @param userId
         */
        void deleteByUserId(String userId);

        /**
         * 功能：根据角色ID查询关联关系
         * @param roleId
         * @return
         */
        List<UserRole> selectByRoleId(String roleId);

        /**
         *
         * 功能：根据用户ID和角色ID删除关联关系
         *
         * @param userId 用户id
         * @param roleId 权限id
         */
        public void deleteByUserRoleId(String userId, String roleId);

    }
