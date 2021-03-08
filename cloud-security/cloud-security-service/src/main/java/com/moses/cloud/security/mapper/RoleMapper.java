package com.moses.cloud.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moses.cloud.security.form.RoleConditionForm;
import com.moses.cloud.security.po.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午10:45
 * @Version 1.0
 **/
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 功能 ：	通过条件查询分页
     * 作者 ： LPF
     */
    public IPage<Role> pageByCondition(Page<Role> page, @Param("condition") RoleConditionForm form);

    /**
     * 功能 ：	查询角色授权码是否存在
     * 作者 ： LPF
     */
    public Role queryRole(@Param("roleString") String roleString);

    /**
     * 根据用户ID查询角色
     * @param userId
     * @return
     */
    public List<Role> findByUserId(String userId);

    /**
     * 根据用户ID和应用类型查询
     * @param userId
     * @param appType
     * @return
     */
    public List<Role> findByUserIdAndAppType(@Param("userId") String userId, @Param("appType") String appType);

    /**
     * 功能：根据类型查询角色
     * 作者：GJY
     * appType 应用类型 （pc ，pad）
     * @return
     */
    public List<Role> selectAllRole(@Param("appType") String appType);

    /**
     *
     * 功能：根据RoleString查询角色信息
     * 作者： GJY
     *
     * @param roleString
     */
    Role findByRoleString(@Param("roleString") String roleString);

    /**
     *
     * 功能：根据角色Id，用户Id查询是否存在绑定关系
     * 作者： GJY
     *
     * @param
     */
    int findUserRole(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * 查询内勤可挂PAD角色
     * @return
     */
    List<Role> findPadRole(@Param("roleCodeList") List<String> roleCodeList);

}
