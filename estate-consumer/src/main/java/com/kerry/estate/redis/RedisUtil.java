package com.kerry.estate.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangshen on 2017/7/4.
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }
    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }
    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入hash缓存
     * @param cacheKey
     * @param hashKey
     * @param value
     * @return
     */
    public boolean setHash(final String cacheKey, final String hashKey, Object value){
        boolean result = false;
        try {
            HashOperations<Serializable,Serializable,Object> operations = redisTemplate.opsForHash();
            operations.put(cacheKey,hashKey,value);
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取hash
     * @param cacheKey
     * @param hashKey
     * @return
     */
    public Object getHash(final String cacheKey, final String hashKey){
        Object object = null;
        HashOperations<Serializable,Serializable,Object> operations = redisTemplate.opsForHash();
        object = operations.get(cacheKey,hashKey);
        return object;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * List写入
     * @param key
     * @param value
     * @return
     */
    public boolean setList(final String key,Long index, Object value){
        boolean result = false;
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            operations.set(key, index, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取List缓存的大小
     * @param key
     * @return
     */
    public long getListLenght(final String key){
        long result = 0;
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            result = operations.size(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取list缓存
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Object> getList(final String key, long start , long end){
        List<Object> result = null;
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            result = operations.range(key,start,end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
