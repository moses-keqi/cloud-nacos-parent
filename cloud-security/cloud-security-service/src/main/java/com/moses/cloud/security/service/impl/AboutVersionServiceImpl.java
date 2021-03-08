package com.moses.cloud.security.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.moses.cloud.commons.exception.BusinessException;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.utils.BeanUtils;
import com.moses.cloud.commons.utils.Md5EncoderUtils;
import com.moses.cloud.commons.utils.SecurityUtils;
import com.moses.cloud.security.form.AddAboutVersionForm;
import com.moses.cloud.security.form.DisabledForm;
import com.moses.cloud.security.form.FindAboutVersionAppForm;
import com.moses.cloud.security.mapper.AboutVersionMapper;
import com.moses.cloud.security.po.AboutVersion;
import com.moses.cloud.security.po.Attachment;
import com.moses.cloud.security.service.IAboutVersionService;
import com.moses.cloud.security.service.IAttachmentService;
import com.moses.cloud.security.vo.FindAboutVersionAppVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HanKeQi
 * @since 2021-02-01
 */
@Service
@Slf4j
public class AboutVersionServiceImpl extends ServiceImpl<AboutVersionMapper, AboutVersion> implements IAboutVersionService {

    @Autowired
    private AboutVersionMapper aboutVersionMapper;

    @Autowired
    private IAttachmentService attachmentService;

    @Override
    public AboutVersion getVersionDescription(String client) {
        LambdaQueryWrapper<AboutVersion> aboutVersionLambdaQueryWrapper = Wrappers.lambdaQuery();
        aboutVersionLambdaQueryWrapper.eq(AboutVersion::getClient, client);
        aboutVersionLambdaQueryWrapper.eq(AboutVersion::getDeleted, Boolean.FALSE.booleanValue());
        aboutVersionLambdaQueryWrapper.eq(AboutVersion::getEnabled, Boolean.TRUE.booleanValue());
        //每一次取最新版本
        List<AboutVersion> aboutVersions = super.list(aboutVersionLambdaQueryWrapper);
        AboutVersion aboutVersion = aboutVersions.stream().max(Comparator.comparing(AboutVersion::getVersion)).get();
        return aboutVersion;
    }

    @Override
    public List<AboutVersion> getVersionH5ListDescription() {
        LambdaQueryWrapper<AboutVersion> aboutVersionLambdaQueryWrapper = Wrappers.lambdaQuery();
        aboutVersionLambdaQueryWrapper.eq(AboutVersion::getClient, "h5");
        aboutVersionLambdaQueryWrapper.eq(AboutVersion::getDeleted, Boolean.FALSE.booleanValue());
        aboutVersionLambdaQueryWrapper.eq(AboutVersion::getEnabled, Boolean.TRUE.booleanValue());
        aboutVersionLambdaQueryWrapper.orderByDesc(AboutVersion::getVersion);
        List<AboutVersion> list = super.list(aboutVersionLambdaQueryWrapper);
        Map<String, List<AboutVersion>> mapGroupVersion = list.stream().collect(Collectors.groupingBy(AboutVersion::getProductName));
        List<AboutVersion> listVersion = Lists.newArrayList();
        mapGroupVersion.forEach((key, value)->{
            AboutVersion maxVersionObject = value.stream().max(Comparator.comparing(AboutVersion::getVersion)).get();
            listVersion.add(maxVersionObject);
        });
        return listVersion;
    }

    @Override
    public void addVersionAppDescription(AddAboutVersionForm aboutVersionVo) {
        log.info("新增版本入参{}", JSON.toJSONString(aboutVersionVo));
        byte[] bytes = attachmentService.findFileByObjectId(aboutVersionVo.getId());
        String md5 = Md5EncoderUtils.fileMd5(bytes);
        List<Attachment> attachments = attachmentService.findByObjectId(aboutVersionVo.getId());
        Attachment attachment = attachments.get(0);
        AboutVersion aboutVersion = BeanUtils.copy(aboutVersionVo, AboutVersion.class);
        List<AboutVersion> list = queryVersionList(aboutVersion);
        if (!ObjectUtils.isEmpty(list)){
            throw new BusinessException(ExceptionMsg.ABOUT_VERSION_REPEAT);
        }
        aboutVersion.setDownUrl(attachment.getPath());
        aboutVersion.setMd5Secret(md5);
        aboutVersion.setEnabled(Boolean.FALSE.booleanValue());
        aboutVersion.setCreateBy(SecurityUtils.getCurrentUsername());
        aboutVersion.setCreateName(SecurityUtils.getCurrentName());
        aboutVersion.setCreateTime(new Date());
        aboutVersion.setDeleted(Boolean.FALSE.booleanValue());
        aboutVersion.setUpdateBy(SecurityUtils.getCurrentUsername());
        aboutVersion.setUpdateName(SecurityUtils.getCurrentName());
        aboutVersion.setUpdateTime(new Date());
        this.save(aboutVersion);
    }


    @Override
    public IPage<FindAboutVersionAppVo> findVersionAppDescription(FindAboutVersionAppForm findAboutVersionAppForm) {
        log.info("分页查询版本{}",JSON.toJSONString(findAboutVersionAppForm));
        IPage<FindAboutVersionAppVo> page = aboutVersionMapper.findVersionAppDescription(findAboutVersionAppForm.toPage(),findAboutVersionAppForm);
        return page;
    }

    @Override
    public void updateDisabledVersion(DisabledForm disabledForm) {
        log.info("启用禁用版本入参{}", JSON.toJSONString(disabledForm));
        AboutVersion version = new AboutVersion();
        //如果是启用
        if(disabledForm.getDisabled()){
            AboutVersion aboutVersion = super.getById(disabledForm.getId());
            aboutVersionMapper.updateDisabledVersion(aboutVersion);
        }
        version.setEnabled(disabledForm.getDisabled());
        version.setUpdateBy(SecurityUtils.getCurrentUsername());
        version.setUpdateName(SecurityUtils.getCurrentName());
        version.setUpdateTime(new Date());
        version.setId(disabledForm.getId());
        this.updateById(version);
    }

    private List<AboutVersion> queryVersionList(AboutVersion aboutVersion){
        LambdaQueryWrapper<AboutVersion> aboutVersionLambdaQueryWrapper = Wrappers.lambdaQuery();
        aboutVersionLambdaQueryWrapper.eq(AboutVersion::getDeleted, Boolean.FALSE.booleanValue());
        aboutVersionLambdaQueryWrapper.eq(AboutVersion::getClient,aboutVersion.getClient());
        aboutVersionLambdaQueryWrapper.eq(AboutVersion::getProductName,aboutVersion.getProductName());
        aboutVersionLambdaQueryWrapper.eq(AboutVersion::getVersion,aboutVersion.getVersion());
        List<AboutVersion> list = super.list(aboutVersionLambdaQueryWrapper);
        return list;
    }
}
