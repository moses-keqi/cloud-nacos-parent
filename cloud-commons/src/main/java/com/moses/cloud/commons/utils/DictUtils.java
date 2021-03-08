package com.moses.cloud.commons.utils;

import com.moses.cloud.commons.vo.CacheDictVo;

import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 上午9:36
 * @Version 1.0
 **/
public class DictUtils {


    /**
     * 目前只使用Redis 其他不使用
     * @param code
     * @return
     */
    public static CacheDictVo findByCode(String code){
        Object obj = RedisUtils.opsForHashGet(DictKeyInterface.CACHE_PUBLIC, code);
        if(obj == null){
            obj = RedisUtils.opsForHashGet(DictKeyInterface.CACHE_SYSTEM, code);
        }
        if(obj != null){
            return (CacheDictVo) obj;
        }
        return null;
    }


    /**
     * 目前只使用Redis 其他不使用
     * @param code
     * @return
     */
    public static String findByValue(String code){
        Object obj = RedisUtils.opsForHashGet(DictKeyInterface.CACHE_PUBLIC, code);
        if(obj == null){
            obj = RedisUtils.opsForHashGet(DictKeyInterface.CACHE_SYSTEM, code);
        }
        if(obj != null){
            return ((CacheDictVo) obj).getDictValue();
        }
        return null;
    }


    /**
     * 目前只使用Redis 其他不使用 获取自己中的子集
     * @param code
     * @return
     */
    public static CacheDictVo findByCategoryCodeLists(String code){
        Object obj = RedisUtils.opsForHashGet(DictKeyInterface.CACHE_PUBLIC, code);
        if(obj == null){
            obj = RedisUtils.opsForHashGet(DictKeyInterface.CACHE_SYSTEM, code);
        }
        if(obj != null){
            return (CacheDictVo) obj;
        }
        return null;
    }

    /**
     * 根据分类编码查询所有字典项
     * @param categoryCode
     * @return
     */
    public static List<CacheDictVo> findByCategoryCode(String categoryCode){
        Object obj = RedisUtils.opsForHashGet(DictKeyInterface.CATEGORY_CACHE_SYSTEM, categoryCode);
        if(obj == null){
            obj = RedisUtils.opsForHashGet(DictKeyInterface.CATEGORY_CACHE_PUBLIC, categoryCode);
        }
        if(obj != null){
            return (List<CacheDictVo>) obj;
        }
        return null;
    }

}
