package com.moses.cloud.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moses.cloud.security.po.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 上午11:55
 * @Version 1.0
 **/
public interface UserMapper extends BaseMapper<User> {

    User findByUsername(String username);

    User findByUsernameAndOrgType(@Param("username") String username, @Param("orgType") String orgType);

    void setExpireTime(User user);

    void cancelExpireTime(User user);

    void lock(User user);

    void unlock(User user);

}
