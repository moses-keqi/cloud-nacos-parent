package com.moses.cloud.commons.utils;

import com.moses.cloud.commons.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author HanKeQi
 * @Date 2020/12/26 下午12:40
 * @Version 1.0
 **/
@Slf4j
public class RedisUtils {

    public static RedisTemplate getRedisTemplate(){
        //此处必须要这么写, 在目前版本如果使用RedisTemplate.class 将有获取到StringRedisTemplate 和 RedisTemplate bean 导致异常
        //如果直接使用StringRedisTemplate不会出现此问题，但无法使用hash问题
        return (RedisTemplate) ApplicationContextHolder.getBean("redisTemplate");
    }

    /**
     * 根据环境添加Redis, 原因只有一个环境Redis 而且db是0，不存在db1 所以要在同一个Redis 区分
     * @return
     */
    public static String getEnv(){
//        Environment environment = ApplicationContextHolder.getBean(Environment.class);
//        if (environment != null && environment.getActiveProfiles().length != 0){
//            String activeProfile = environment.getActiveProfiles()[0];
//            log.debug("spring profiles active {}", activeProfile);
//            if ("prod".equals(activeProfile)){
//                return "";
//            }
//            return String.format("%s:", activeProfile);
//
//        }
//        throw new BusinessException(ExceptionMsg.RESOURCE_ERROR_001_CODE);
        return "";
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public static void del(String... key) {
        if (key == null || key.length <= 0 ){
            log.debug("del key is null or length  lt 0 ");
            return;
        }
        String[] keys = new String[key.length];
        String keyEnv = getEnv();
        for (int i = 0; i < key.length ; i++) {
            keys[i] = String.format("%s%s", keyEnv, key[i]);
        }
        if (keys.length == 1) {
            getRedisTemplate().delete(keys[0]);
        } else {
            List<String> list = CollectionUtils.arrayToList(keys);
            try {
                getRedisTemplate().delete(list);
            }catch (Exception e){
                log.warn("redis Multiple deletion failures, However, the key can be deleted as a single instance");
                list.forEach(str-> getRedisTemplate().delete(str));
            }
        }
    }

    /**
     * 获取缓存
     *
     * @param key
     * @param clazz
     * @return
     */
    public static <T> T get(String key, Class<T> clazz) {
        key = String.format("%s%s", getEnv(), key);
        return (T) getRedisTemplate().opsForValue().get(key);
    }

    /**
     * 将value对象写入缓存
     *
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        key = String.format("%s%s", getEnv(), key);
        getRedisTemplate().opsForValue().set(key, value);
    }

    /**
     * 将value对象写入缓存
     *
     * @param key
     * @param value
     * @param time 秒
     */
    public static void set(String key, Object value,long time) {
        key = String.format("%s%s", getEnv(), key);
        getRedisTemplate().opsForValue().set(key, value,time, TimeUnit.SECONDS);
    }

    /**
     * 指定缓存的失效时间
     *
     * @param key 缓存KEY
     * @param time 失效时间(秒)
     */
    public static void expire(String key, long time) {
        if (time > 0) {
            key = String.format("%s%s", getEnv(), key);
            getRedisTemplate().expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 根据Redis key 查看 Redis是否存在
     * @param key
     * @return
     */
    public static boolean hasKey(String key){
        key = String.format("%s%s", getEnv(), key);
        return getRedisTemplate().hasKey(key);
    }

    /**
     * 根据Redis key  原子自增或自减
     * @param key
     * @param time
     * @param <T>
     * @return
     */
    public static <T> Long incr(String key,long time){
        key = String.format("%s%s", getEnv(), key);
        AtomicReference<byte[]> atomicReference = new AtomicReference<>(key.getBytes());

        Long data = null;
        //(Long) getRedisTemplate().execute((RedisCallback<Long>) con -> con.incrBy(key.getBytes(), 0L));
        data = (Long) getRedisTemplate().execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                Long res = connection.incrBy(atomicReference.get(), 1L);
                connection.expire(atomicReference.get(), time);
                return res;
            }
        });
        return data;
    }

    /**
     * 根据redis  key 获取在Redis存活时长
     * @param key
     * @return
     */
    public static long getExpire(String key){
        key = String.format("%s%s", getEnv(), key);
        return getRedisTemplate().getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 根据redis key 获取Map
     * @param key
     * @param <T>
     * @param <H>
     * @return
     */
    public static <T,H> Map<T,H> opsForHashGetAll(String key){
        key = String.format("%s%s", getEnv(), key);
        return getRedisTemplate().opsForHash().entries(key);
    }

    /**
     * 根据Redis 和map key 获取map value
     * @param key
     * @param hashKey
     * @return
     */
    public static Object opsForHashGet(String key,String hashKey){
        key = String.format("%s%s", getEnv(), key);
        if(getRedisTemplate().opsForHash().hasKey(key, hashKey)){
            return getRedisTemplate().opsForHash().get(key, hashKey);
        }else {
            return null;
        }
    }

    /**
     * redis hashMap putAll 存储
     * @param key
     * @param map
     */
    public static void opsForHashPutAll(String key,Map map){
        key = String.format("%s%s", getEnv(), key);
        getRedisTemplate().opsForHash().putAll(key, map);
    }

}
