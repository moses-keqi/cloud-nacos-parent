package com.moses.cloud.security.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Maps;
import com.moses.cloud.commons.utils.BeanUtils;
import com.moses.cloud.commons.utils.DictKeyInterface;
import com.moses.cloud.commons.utils.RedisUtils;
import com.moses.cloud.commons.vo.CacheDictVo;
import com.moses.cloud.security.po.Dict;
import com.moses.cloud.security.service.IDictService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 上午9:35
 * @Version 1.0
 **/
@Component
@Slf4j
public class DictUtilsConfig {

    /** 字典版本 */
    public static String DICT_VERSION_VALUE = "";

    @Autowired
    private IDictService sysDictService;


    /**
     * 更新版本
     */
    public void updateVersion(){
        String version = generateVersion();
        RedisUtils.set(DictKeyInterface.DICT_VERSION_KEY, version);
    }

    /**
     * 自动刷新字典缓存
     */
    @Scheduled(cron="0/10 * * * * ?")
    public void initDictJob(){
        String dictVersion = RedisUtils.get(DictKeyInterface.DICT_VERSION_KEY, String.class);
        if(StringUtils.isEmpty(dictVersion)){
            log.info("初始化字典缓存版本");
            DICT_VERSION_VALUE = generateVersion();
            RedisUtils.set(DictKeyInterface.DICT_VERSION_KEY, DICT_VERSION_VALUE);
            dictVersion = RedisUtils.get(DictKeyInterface.DICT_VERSION_KEY, String.class);
        }

        if(StringUtils.isEmpty(DICT_VERSION_VALUE) || !dictVersion.equals(DICT_VERSION_VALUE)){
            DICT_VERSION_VALUE = dictVersion;
            log.info("初始化字典缓存开始");
            initDict();
            log.info("初始化字典缓存完成");
        }
    }

    /**
     * 获取版本
     * @return
     */
    public String generateVersion(){
        return LocalDateTime.now().toString();
    }

    /**
     * 初始化加载
     */
    public void initDict(){
        // 加载
        publicCacheDict();
        systemCacheDict();
    }

    private void publicCacheDict(){
        Map<String, CacheDictVo> publicCacheDict = Maps.newHashMap();
        Map<String,List<CacheDictVo>> publicCacheDictCategory = Maps.newHashMap();
        List<Dict> parentDict = sysDictService.findByParentIdAndSystemFlag("0", "1");
        if(CollectionUtils.isEmpty(parentDict)){
            return;
        }
        for (Dict item : parentDict) {
            String code = item.getCode();
            Dict dict = sysDictService.findByCode(code);
            publicCacheDict.put(code, BeanUtils.copy(dict, CacheDictVo.class));
            List<Dict> childs = sysDictService.findByParentCode(code);
            if(childs != null && childs.size() > 0){

                publicCacheDictCategory.put(code,BeanUtils.copy(childs, new TypeReference<List<CacheDictVo>>(){}));
                for (Dict child : childs) {
                    publicCacheDict.put(child.getCode(), BeanUtils.copy(child, CacheDictVo.class));
                }
            }
        }
        RedisUtils.del(DictKeyInterface.CATEGORY_CACHE_PUBLIC, DictKeyInterface.CACHE_PUBLIC);
        RedisUtils.opsForHashPutAll(DictKeyInterface.CATEGORY_CACHE_PUBLIC, publicCacheDictCategory);
        RedisUtils.opsForHashPutAll(DictKeyInterface.CACHE_PUBLIC, publicCacheDict);

    }

    private void systemCacheDict(){
        List<Dict> parentDict = sysDictService.findByParentIdAndSystemFlag("0", "0");
        if(CollectionUtils.isEmpty(parentDict)){
            return;
        }

        Map<String, CacheDictVo> systemCacheDict = Maps.newHashMap();
        Map<String, List<CacheDictVo>> systemCacheDictCategory = Maps.newHashMap();

        for (Dict item : parentDict) {
            String code = item.getCode();
            Dict dict = sysDictService.findByCode(code);
            systemCacheDict.put(code, BeanUtils.copy(dict, CacheDictVo.class));
            List<Dict> childs = sysDictService.findByParentCode(code);
            if(childs != null && childs.size() > 0){
                systemCacheDictCategory.put(code,BeanUtils.copy(childs, new TypeReference<List<CacheDictVo>>(){}));
                for (Dict child : childs) {
                    systemCacheDict.put(child.getCode(), BeanUtils.copy(child, CacheDictVo.class));
                    List<Dict> dictList = sysDictService.findByParentCode(child.getCode());
                    for(Dict dict1 : dictList){
                        systemCacheDict.put(dict1.getCode(), BeanUtils.copy(dict1, CacheDictVo.class));
                    }
                }
            }
        }
        RedisUtils.del(DictKeyInterface.CACHE_SYSTEM, DictKeyInterface.CATEGORY_CACHE_SYSTEM);
        RedisUtils.opsForHashPutAll(DictKeyInterface.CATEGORY_CACHE_SYSTEM, systemCacheDictCategory);
        RedisUtils.opsForHashPutAll(DictKeyInterface.CACHE_SYSTEM, systemCacheDict);

    }
}
