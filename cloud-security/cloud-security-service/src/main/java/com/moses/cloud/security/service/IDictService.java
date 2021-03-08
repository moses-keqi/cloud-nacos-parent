package com.moses.cloud.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moses.cloud.security.form.QueryDictForm;
import com.moses.cloud.security.po.Dict;

import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 上午11:10
 * @Version 1.0
 **/
public interface IDictService extends IService<Dict> {

    /**
     * 功能：修改数据字典
     * @param dict
     */
    void updateDict(Dict dict);

    /**O
     * 功能：新增数据字典
     * @param dict
     */
    void insertDict(Dict dict);

    /**
     * 功能 ：通过dictId删除字典项(字典维护页面)
     * @param dictId
     */
    void deleteDictById(String dictId);

    /**
     * 功能 ：	通过条件查询分页
     */
    IPage<Dict> pageByCondition(QueryDictForm queryDictVO);

    /**
     * 功能：修改数据字典
     * 作者： LPF
     * @param id
     * @param disabled
     */
    void updateDisabled(String id, Boolean disabled);

    /**
     * 根据父级code查询
     */
    List<Dict> findByParentCode(String code);

    /**
     * 根据code查询
     * @param code
     * @return
     */
    Dict findByCode(String code);

    /**
     * 根据父级CODE与系统标识查询
     * @param parentId
     * @param systemFlag
     * @return
     */
    List<Dict> findByParentIdAndSystemFlag(String parentId, String systemFlag);

}
