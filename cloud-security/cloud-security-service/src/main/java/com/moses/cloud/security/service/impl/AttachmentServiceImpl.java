package com.moses.cloud.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.moses.cloud.commons.exception.BusinessException;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.utils.DictUtils;
import com.moses.cloud.commons.utils.FileUtils;
import com.moses.cloud.commons.utils.JsonUtils;
import com.moses.cloud.commons.utils.SecurityUtils;
import com.moses.cloud.commons.vo.CacheDictVo;
import com.moses.cloud.security.form.UploadForm;
import com.moses.cloud.security.mapper.AttachmentMapper;
import com.moses.cloud.security.po.Attachment;
import com.moses.cloud.security.service.IAttachmentService;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author HanKeQi
 * @Date 2021/2/18 下午2:55
 * @Version 1.0
 **/

@Service
@Slf4j
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {


    @Resource
    private AttachmentMapper attachmentMapper;

    /**
     * 获取受保护的文件服务器根目录
     * @return
     */
    public static String realBasePath(){
        CacheDictVo dict = DictUtils.findByCode("attachment_protect_base_path");
        if(dict != null){
            return dict.getDictValue();
        }
        throw null;
    }

    /**
     * 获取公开文件服务器根目录
     */
    public static String realPublicBasePath(){
        CacheDictVo dict = DictUtils.findByCode("attachment_public_base_path");
        if(dict != null){
            return dict.getDictValue();
        }
        throw null;
    }

    @Override
    public Attachment upload(String objectId, String type,String path ,String originalFilename, byte[] bytes) {
        Attachment attachment = new Attachment();
        attachment.setObjectId(objectId);
        attachment.setType(type);
        attachment.setStorageType(1); // 类型存储：1 本地；2 微软云
        // 文件后缀
        String suffix = FileUtils.getSuffix(originalFilename);
        // 文件系统名称
        String newName = FileUtils.generateFileName(originalFilename);
        // 文件存储基础路径
        String baseFilePath = FileUtils.formatPath(path)+FileUtils.generateDatePath()+"/"+newName;
        // 文件真实存储路径
        String realFilePath = realBasePath()+baseFilePath;

        // 附件安全控制
        //whitelistCheck(type, suffix);
        if(publicFileTypes().contains(type)){
            realFilePath = realPublicBasePath()+baseFilePath;
        }
        attachment.setOldName(originalFilename);
        attachment.setNewName(newName);
        attachment.setFileType(suffix);
        attachment.setPath(baseFilePath);
        //如果是电子合同，没有登陆验证，填充默认值
        attachment.setCreateBy(SecurityUtils.getCurrentUsername());
        attachment.setCreateName(SecurityUtils.getCurrentName());
        attachment.setCreateTime(new Date());
        attachment.setDeleted(false);

        this.save(attachment);

        // 存储文件
        try {
            FileUtils.byteToFile(bytes, realFilePath);
            return attachment;
        } catch (IOException e) {
            log.error("存储文件异常", JsonUtils.toJSONString(e));
            throw new BusinessException(ExceptionMsg.FILE_STORAGE_ERROR);
        }
    }

    @Override
    public byte[] findFileById(String id) {
        Attachment attachment = getById(id);
        if(attachment != null){
            if (attachment.getStorageType() == 1) {
                String realFilePath = realBasePath()+attachment.getPath();
                if(publicFileTypes().contains(attachment.getType())){
                    realFilePath = realPublicBasePath()+attachment.getPath();
                }
                try {
                    byte[] fileByte = FileUtils.readFileToBytes(realFilePath);
                    log.info("fileByte: " +  fileByte + "   realFilePath:" +  realFilePath);
                    return fileByte;
                } catch (IOException e) {
                    throw new BusinessException(ExceptionMsg.FILE_RED_ERROR);
                }
            }

        }
        return null;
    }

    @Override
    public byte[] findFileByObjectId(String objectId) {
        List<Attachment> attachments = findByObjectId(objectId);
        Attachment attachment = attachments.get(0);
        if(attachment != null){
            if (attachment.getStorageType() == 1) {
                String realFilePath = realBasePath() + attachment.getPath();
                if (publicFileTypes().contains(attachment.getType())) {
                    realFilePath = realPublicBasePath() + attachment.getPath();
                }
                try {
                    byte[] fileByte = FileUtils.readFileToBytes(realFilePath);
                    log.info("fileByte: " + fileByte + "   realFilePath:" + realFilePath);
                    return fileByte;
                } catch (IOException e) {
                    throw new BusinessException(ExceptionMsg.FILE_RED_ERROR);
                }
            }
        }
        return null;
    }

    @Override
    public List<Attachment> findByObjectIdAndType(String objectId, String type) {
        LambdaQueryWrapper<Attachment> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Attachment::getObjectId,objectId);
        queryWrapper.eq(Attachment::getType, type);
        queryWrapper.orderByAsc(Attachment::getCreateTime);
        return this.list(queryWrapper);
    }

    @Override
    public List<Attachment> findByObjectIdAndTypeOrdeyDesc(String objectId, String type) {
        LambdaQueryWrapper<Attachment> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Attachment::getObjectId,objectId);
        queryWrapper.eq(Attachment::getType, type);
        queryWrapper.orderByDesc(Attachment::getCreateTime);
        return this.list(queryWrapper);
    }

    @Override
    public Attachment uploadTwoCode(String objectId, String type, String path, String originalFilename, byte[] bytes) {
        List<Attachment> attachmentList = findByObjectIdAndTypeOrdeyDesc(objectId, type);
        if(attachmentList.size()>0){
            for (Attachment att : attachmentList){
                attachmentMapper.deleteById(att.getId());
            }
        }
        Attachment attachment = new Attachment();
        attachment.setObjectId(objectId);
        attachment.setType(type);
        attachment.setStorageType(1); // 类型存储：1 本地；2 腾讯云
        // 文件后缀
        String suffix = FileUtils.getSuffix(originalFilename);
        // 文件系统名称
        String newName = FileUtils.generateFileName(originalFilename);
        // 文件存储基础路径
        String baseFilePath = FileUtils.formatPath(path)+FileUtils.generateDatePath()+"/"+newName;
        // 文件真实存储路径
        String realFilePath = realBasePath()+baseFilePath;

        // 附件安全控制
        whitelistCheck(type, suffix);

        if(publicFileTypes().contains(type)){
            realFilePath = realPublicBasePath()+baseFilePath;
        }
        attachment.setOldName(originalFilename);
        attachment.setNewName(newName);
        attachment.setFileType(suffix);
        attachment.setPath(baseFilePath);
        //如果是电子合同，没有登陆验证，填充默认值
        attachment.setCreateBy(SecurityUtils.getCurrentUsername());
        attachment.setCreateName(SecurityUtils.getCurrentName());
        attachment.setCreateTime(new Date());
        attachment.setDeleted(false);
        this.save(attachment);

        // 存储文件
        try {
            FileUtils.byteToFile(bytes, realFilePath);
            return attachment;
        } catch (IOException e) {
            log.error("存储文件异常", JsonUtils.toJSONString(e));
            throw new BusinessException(ExceptionMsg.FILE_STORAGE_ERROR);
        }
    }

    @Override
    public String uploadFileReturnPath(String objectId, String type, String path, String originalFilename, byte[] bytes) {
        // 文件后缀
        String suffix = FileUtils.getSuffix(originalFilename);
        // 文件系统名称
        String newName = FileUtils.generateFileName(originalFilename);
        // 文件存储基础路径
        String baseFilePath = FileUtils.formatPath(path)+FileUtils.generateDatePath()+"/aaaa"+newName;
        // 文件真实存储路径
        String realFilePath = realBasePath()+baseFilePath;

        // 附件安全控制
        whitelistCheck(type, suffix);

        if(publicFileTypes().contains(type)){
            realFilePath = realPublicBasePath()+baseFilePath;
        }
        // 存储文件
        try {
            FileUtils.byteToFile(bytes, realFilePath);
            return realFilePath;
        } catch (IOException e) {
            log.error("存储文件异常", JsonUtils.toJSONString(e));
            throw new BusinessException(ExceptionMsg.FILE_STORAGE_ERROR);
        }
    }

    @Override
    public List<Attachment> findFileByType(UploadForm form) {
        Assert.notNull(form,"参数不能为空!");
        LambdaQueryWrapper<Attachment> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Attachment::getObjectId,form.getObjectId());
        queryWrapper.eq(Attachment::getType,form.getType());
        queryWrapper.eq(Attachment::getDeleted,0);
        return this.list(queryWrapper);
    }

    @Override
    public List<Attachment> findByObjectId(String objectId){
        Assert.notNull(objectId,"参数不能为空!");
        LambdaQueryWrapper<Attachment> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Attachment::getObjectId,objectId);
        queryWrapper.eq(Attachment::getDeleted,0);
        return this.list(queryWrapper);
    }


    /**
     * 附件白名单
     * @param type	 附件类型
     * @param suffix 文件后缀
     * @return
     */
    private void whitelistCheck(String type, String suffix){
        String dictKey = "atta_"+type;
        List<CacheDictVo> dicts = DictUtils.findByCategoryCode("attachment_type");
        Map<String,String> typeMap = dicts.stream().collect(Collectors.toMap(CacheDictVo::getCode, CacheDictVo::getDesc));
        if(!typeMap.keySet().contains(dictKey)){
            throw new BusinessException(ExceptionMsg.FILE_UNSUPPORTED_CLASSIFICATION_ERROR);
        }

        String supperSuffixStr = typeMap.get(dictKey);
        if(StringUtils.isBlank(supperSuffixStr)){
            throw new BusinessException(ExceptionMsg.FILE_UNSUPPORTED_CLASSIFICATION_WHITE_LIST_ERROR);
        }

        List<String> supperSuffixs = Arrays.asList(supperSuffixStr.split(","));
        Long num = supperSuffixs.stream().filter(string -> string.toUpperCase().equals(suffix.toUpperCase())).count();
        if(num == null ||  num.intValue() == 0){
            throw new BusinessException(ExceptionMsg.FILE_STORAGE_ERROR.getCode(), "不支持的文件类型，仅支持 "+supperSuffixStr);
        }
    }

    /**
     * 公开路径类型
     * @return
     */
    public List<String> publicFileTypes(){
        List<String> list = Lists.newArrayList();
        List<CacheDictVo> publicFileTypes = DictUtils.findByCategoryCode("attachment_types");
        if (!CollectionUtils.isEmpty(publicFileTypes)){
            publicFileTypes.forEach(cacheDictVo ->
                    list.add(cacheDictVo.getDictValue())
            );
        }
        return list;
    }

}
