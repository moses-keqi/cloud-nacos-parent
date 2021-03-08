package com.moses.cloud.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moses.cloud.commons.exception.BusinessException;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.utils.SecurityUtils;
import com.moses.cloud.security.config.DictUtilsConfig;
import com.moses.cloud.security.form.QueryDictForm;
import com.moses.cloud.security.mapper.DictMapper;
import com.moses.cloud.security.po.Dict;
import com.moses.cloud.security.service.IDictService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 上午11:12
 * @Version 1.0
 **/
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Resource
    private DictMapper dictMapper;

    @Autowired
    private DictUtilsConfig dictHelperConfig;

    /**
     * 功能 ：修改数据字典 逻辑 ：Code是否存在
     * 作者 ： LPF
     */
    @Override
    public void updateDict(Dict dict) {
        if (StringUtils.isBlank(dict.getNameCn())) {
            throw new BusinessException(ExceptionMsg.DICT_ERROR_001_CODE);
        } else if (StringUtils.isBlank(dict.getCode())) {
            throw new BusinessException(ExceptionMsg.DICT_ERROR_002_CODE);
        } else if (StringUtils.isBlank(dict.getDictValue())) {
            throw new BusinessException(ExceptionMsg.DICT_ERROR_003_CODE);
        } else if (dict.getSortOrder() == null) {
            throw new BusinessException(ExceptionMsg.DICT_ERROR_004_CODE);
        }
        if(StringUtils.isBlank(dict.getId())) {
            throw new BusinessException(ExceptionMsg.DICT_ERROR_006_CODE);
        }
        if (dict != null && dict.getCode() != null) {
            Dict EO = dictMapper.queryDictCode(dict.getCode());
            if (EO != null && !EO.getId().equals(dict.getId())) {
                throw new BusinessException(ExceptionMsg.DICT_ERROR_007_CODE);
            }
        }
        dict.setEnabled(dict.isEnabled());
        dict.setDeleted(false);
        dict.setUpdateBy(SecurityUtils.getCurrentUsername());
        dict.setUpdateName(SecurityUtils.getCurrentName());
        dict.setUpdateTime(new Date());
        dictMapper.updateById(dict);
        //刷新字段
        dictHelperConfig.updateVersion();
    }

    /**
     * 功能：新增数据字典
     * 作者： LPF
     */
    @Override
    public void insertDict(Dict dict) {
        if (StringUtils.isBlank(dict.getNameCn())) {
            throw new BusinessException(ExceptionMsg.DICT_ERROR_001_CODE);
        } else if (StringUtils.isBlank(dict.getCode())) {
            throw new BusinessException(ExceptionMsg.DICT_ERROR_002_CODE);
        } else if (StringUtils.isBlank(dict.getDictValue())) {
            throw new BusinessException(ExceptionMsg.DICT_ERROR_003_CODE);
        }
        else if (dict.getSortOrder() == null) {
            throw new BusinessException(ExceptionMsg.DICT_ERROR_004_CODE);
        }
        if (dict != null && dict.getCode() != null) {
            Dict eo = dictMapper.queryDictCode(dict.getCode());
            if (eo != null) {
                throw new BusinessException(ExceptionMsg.DICT_ERROR_007_CODE);
            }
        }
        if (StringUtils.isBlank(dict.getParentId())) {
            dict.setParentId("0");
        }
        dict.setCreateBy(SecurityUtils.getCurrentUsername());
        dict.setCreateName(SecurityUtils.getCurrentName());
        dict.setCreateTime(new Date());
        dict.setDeleted(false);
        dict.setEnabled(false);
        dictMapper.insert(dict);
        //刷新字段
        dictHelperConfig.updateVersion();
    }

    /**
     * 功能 ：通过dictId删除字典项(字典维护页面) 逻辑 ：确保没有子级
     * 作者 ： LPF
     */
    @Override
    public void deleteDictById(String dictId) {
        List<Dict> list = dictMapper.selectByParentId(dictId);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ExceptionMsg.DICT_ERROR_008_CODE);
        }
        dictMapper.deleteById(dictId);

        dictHelperConfig.updateVersion();
    }

    /**
     * 功能 ：分页查询
     * 作者 ： LPF
     */
    @Override
    public IPage<Dict> pageByCondition(QueryDictForm form) {
        return dictMapper.pageByCondition(form.toPage(),form);
    }

    /**
     *
     * 功能 ：是否可用 是    0 否    1
     * 作者 ： LPF
     */
    @Override
    public void updateDisabled(String id, Boolean disabled) {
        if(StringUtils.isBlank(id)) {
            throw new BusinessException("-1", "知道不到Id");
        }
        Dict dict =new Dict();
        dict.setId(id);
        dict.setEnabled(disabled);
        dict.setUpdateBy(SecurityUtils.getCurrentUsername());
        dict.setUpdateName(SecurityUtils.getCurrentName());
        dict.setUpdateTime(new Date());
        dictMapper.updateById(dict);
        dictHelperConfig.updateVersion();
    }

    @Override
    public List<Dict> findByParentCode(String code) {
        if(StringUtils.isBlank(code)){
            return null;
        } else {
            return baseMapper.findByParentCode(code);
        }
    }

    @Override
    public Dict findByCode(String code) {
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dict::getCode, code);
        wrapper.eq(Dict::isEnabled, Boolean.FALSE.booleanValue());
        wrapper.eq(Dict::getDeleted, Boolean.FALSE.booleanValue());
        return this.getOne(wrapper);
    }

    @Override
    public List<Dict> findByParentIdAndSystemFlag(String parentId, String systemFlag) {
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dict::getParentId, parentId);
        wrapper.eq(Dict::getSystemFlag, systemFlag);
        wrapper.eq(Dict::isEnabled, Boolean.FALSE.booleanValue());
        wrapper.eq(Dict::getDeleted, Boolean.FALSE.booleanValue());
        wrapper.orderByAsc(Dict::getSortOrder);
        return this.list(wrapper);
    }
}
