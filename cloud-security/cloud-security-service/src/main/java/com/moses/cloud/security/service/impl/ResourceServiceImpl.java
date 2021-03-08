package com.moses.cloud.security.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moses.cloud.commons.exception.BusinessException;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.utils.SecurityUtils;
import com.moses.cloud.security.form.DisabledForm;
import com.moses.cloud.security.form.ResourceConditionForm;
import com.moses.cloud.security.mapper.MenuResourceMapper;
import com.moses.cloud.security.mapper.ResourceMapper;
import com.moses.cloud.security.po.MenuResource;
import com.moses.cloud.security.po.Resource;
import com.moses.cloud.security.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:41
 * @Version 1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private MenuResourceMapper menuResourceMapper;

    /**
     * 功能 ：分页查询
     */
    @Override
    public IPage<Resource> pageByCondition(ResourceConditionForm resourceCondition) {
        return resourceMapper.pageByCondition(resourceCondition.toPage(),resourceCondition);
    }

    /**
     * 功能：资源新增
     * @param resource
     */
    @Override
    public void insertResource(Resource resource) {
        if(resource!=null && resource.getPermissionString()!=null) {
            Resource result=resourceMapper.queryResource(null,resource.getPermissionString());
            if(result!=null) {
                throw new BusinessException(ExceptionMsg.RESOURCE_ERROR_001_CODE);
            }
        }
        if(resource!=null && resource.getResourceString()!=null){
            Resource result=resourceMapper.queryResource(resource.getResourceString(),null);
            if(result!=null) {
                throw new BusinessException(ExceptionMsg.RESOURCE_ERROR_001_CODE);
            }
        }
        resource.setCreateBy(SecurityUtils.getCurrentUserId());
        resource.setCreateName(SecurityUtils.getCurrentName());
        resource.setCreateTime(new Date());
        resourceMapper.insert(resource);
    }

    /**
     * 功能：资源修改
     * 作者：LPF
     * @param resource
     */
    @Override
    public void updateResource(Resource resource) {
        if(resource!=null && resource.getPermissionString()!=null) {
            Resource result=resourceMapper.queryResource(null,resource.getPermissionString());
            if(result!=null && !result.getId().equals(resource.getId())) {
                throw new BusinessException(ExceptionMsg.RESOURCE_ERROR_001_CODE);
            }
        }
        if(resource!=null && resource.getResourceString()!=null){
            Resource result=resourceMapper.queryResource(resource.getResourceString(),null);
            if(result!=null && !result.getId().equals(resource.getId())) {
                throw new BusinessException(ExceptionMsg.RESOURCE_ERROR_002_CODE);
            }
        }
        resource.setUpdateBy(SecurityUtils.getCurrentUserId());
        resource.setUpdateName(SecurityUtils.getCurrentUsername());
        resource.setUpdateTime(new Date());
        resourceMapper.updateById(resource);
    }

    /**
     * 功能：删除
     * 作者：LPF
     */
    @Override
    public void deleteById(String id) {
        List<MenuResource> list=menuResourceMapper.findByMenuId(id);
        if (list != null && list.size() > 0) {
            throw new BusinessException(ExceptionMsg.MENURESOURCE_ERROR_001_CODE);
        }
        resourceMapper.deleteById(id);
    }

    /**
     * 功能：通过ID查询
     * @param id
     */
    @Override
    public Resource selectById(String id) {
        Resource result=resourceMapper.selectById(id);
        return result;
    }

    @Override
    public List<Resource> findByRoleIds(List<String> roleIds) {
        return baseMapper.findByRoleIds(roleIds);
    }

    /**
     * 根据资源ID更新资源是否可用
     * @param disabled
     */
    @Override
    public void updateDisabledById(DisabledForm disabled) {
        //获取登录账号
        //封装更新参数
        Resource resource = new Resource();
        resource.setId(disabled.getId());
        resource.setDisabled(disabled.getDisabled());

        resource.setUpdateBy(SecurityUtils.getCurrentUserId());
        resource.setUpdateName(SecurityUtils.getCurrentName());
        resource.setUpdateTime(new Date());
        //更新
        resourceMapper.updateDisabledById(resource);
    }

}

