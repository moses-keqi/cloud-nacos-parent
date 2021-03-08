package com.moses.cloud.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moses.cloud.security.form.ResourceConditionForm;
import com.moses.cloud.security.po.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源表 Mapper 接口
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:05
 * @Version 1.0
 **/
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 功能 ：	通过条件查询分页
     */
    IPage<Resource> pageByCondition(Page<Resource> page, @Param("condition") ResourceConditionForm resourceCondition);

    /**
     * 功能 ：	校验资源、授权码是否存在
     */
    Resource queryResource(@Param("resourceString") String resourceString, @Param("permissionString") String permissionString);

    /**
     * 根据角色ID集合查询资源
     * @param roleIds
     * @return
     */
    List<Resource> findByRoleIds(@Param("roleIds") List<String> roleIds);

    /**
     * 查询所有有效资源
     * @return
     */
    List<Resource> findAllResouce(@Param("appType") String appType);

    /**
     * 根据资源ID更新资源是否可用
     * @param resource
     */
    void updateDisabledById(Resource resource);

    /**
     * 根据菜单ID查询已经绑定的资源
     * @param menuId
     * @return
     */
    List<Resource> findByMenuId(@Param("menuId") String menuId);
}
