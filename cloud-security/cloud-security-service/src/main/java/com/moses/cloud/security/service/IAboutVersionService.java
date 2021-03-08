package com.moses.cloud.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moses.cloud.security.form.AddAboutVersionForm;
import com.moses.cloud.security.form.DisabledForm;
import com.moses.cloud.security.form.FindAboutVersionAppForm;
import com.moses.cloud.security.po.AboutVersion;
import com.moses.cloud.security.vo.FindAboutVersionAppVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HanKeQi
 * @since 2021-02-01
 */
public interface IAboutVersionService extends IService<AboutVersion> {

    /**
     * 下载URL
     * @param client 终端
     * @return
     */
    AboutVersion getVersionDescription(String client);

    /**
     * H5下载URL
     * @return
     */
    List<AboutVersion> getVersionH5ListDescription();

    /**
     * 新增版本
     * @param aboutVersionVo
     */
    void addVersionAppDescription(AddAboutVersionForm aboutVersionVo);


    /**
     * 分页查询版本
     * @param findAboutVersionAppForm
     * @return
     */
    IPage<FindAboutVersionAppVo> findVersionAppDescription(FindAboutVersionAppForm findAboutVersionAppForm);

    /**
     * 启用禁用版本
     * @param disabledForm
     */
    void updateDisabledVersion(DisabledForm disabledForm);
}
