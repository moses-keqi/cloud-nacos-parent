package com.moses.cloud.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moses.cloud.security.form.DisabledForm;
import com.moses.cloud.security.form.ResourceConditionForm;
import com.moses.cloud.security.po.Resource;

import java.util.List;

/**
 * 资源表
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:39
 * @Version 1.0
 **/
public interface IResourceService extends IService<Resource> {

    /**
     * 功能 ：	通过条件查询分页
     */
    IPage<Resource> pageByCondition(ResourceConditionForm resourceCondition);

    /**
     * 功能：资源新增
     * @param resource
     */
    void  insertResource(Resource resource);

    /**
     * 功能：资源修改
     * @param resource
     */
    void  updateResource(Resource resource);

    /**
     * 功能：删除
     */
    void  deleteById(String id);

    /**
     * 功能：通过ID查询
     * @param id
     */
    Resource  selectById(String id);

    /**
     * 根据资源ID查询
     * @param roleIds
     * @return
     */
    List<Resource> findByRoleIds(List<String> roleIds);

    /**
     * 根据资源ID更新资源是否可用
     * @param disabled
     */
    void updateDisabledById(DisabledForm disabled);

}
