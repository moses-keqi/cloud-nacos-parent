package com.moses.cloud.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moses.cloud.security.form.FindAboutVersionAppForm;
import com.moses.cloud.security.po.AboutVersion;
import com.moses.cloud.security.vo.FindAboutVersionAppVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HanKeQi
 * @since 2021-02-01
 */
public interface AboutVersionMapper extends BaseMapper<AboutVersion> {

    /**
     * 分页查询版本
     * @param toPage
     * @param findAboutVersionAppForm
     * @return
     */
    IPage<FindAboutVersionAppVo> findVersionAppDescription(Page<FindAboutVersionAppVo> toPage, @Param("pageForm") FindAboutVersionAppForm findAboutVersionAppForm);

    /**
     * 禁用版本
     * @param aboutVersion
     */
    void updateDisabledVersion(AboutVersion aboutVersion);
}
