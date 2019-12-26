package org.techas.falleaves.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.techas.falleaves.cache.service.ICacheService;

import java.util.concurrent.TimeUnit;

@Service(value = "simpleRedisCacheService")
public class SimpleRedisCacheService<K extends String, V> implements ICacheService<K, V> {

    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    @Override
    public boolean set(K key, V value, long expireTime) {
        try {
            if(!"".equals(key) && null != value) {
                redisTemplate.boundValueOps(key).set(value, expireTime, TimeUnit.MILLISECONDS);
                return true;
            }
            return false;
        } catch (Exception ignore) {
            return false;
        }

    }

    @Override
    public boolean delete(K key) {
        if(!"".equals(key)) {
            return has(key);
        }
        return false;
    }

    @Override
    public V get(K key) {
        if(!"".equals(key)) {
            return redisTemplate.boundValueOps(key).get();
        }
        return null;
    }

    @Override
    public boolean has(K key) {
        if(!"".equals(key)) {
            Boolean found = redisTemplate.hasKey(key);
            if(null != found) {
                return found;
            }
            return false;
        }
        return false;
    }
}
