package com.moses.cloud.commons.utils;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 上午9:43
 * @Version 1.0
 **/
public interface DictKeyInterface {

    /** 字典版本 */
    String DICT_VERSION_KEY = "dictVersion";


    String CATEGORY_CACHE_SYSTEM = "dictHelper_hash_category_cache:system";

    String CATEGORY_CACHE_PUBLIC = "dictHelper_hash_category_cache:public";

    String CACHE_SYSTEM = "dictHelper_hash_cache:system";

    String CACHE_PUBLIC = "dictHelper_hash_cache:public";


    /**
     * 中台Redis key
     */
    String CONNECT_ACCESS_TOKEN = "connect:access_token";

}
