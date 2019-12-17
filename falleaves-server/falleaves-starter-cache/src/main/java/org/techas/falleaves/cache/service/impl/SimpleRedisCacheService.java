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
        if(null != key && !key.equals("") && null != value) {
            redisTemplate.boundValueOps(key).set(value, expireTime, TimeUnit.MILLISECONDS);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(K key) {
        if(null != key && !key.equals("")) {
            return has(key);
        }
        return false;
    }

    @Override
    public V get(K key) {
        if(null != key && !key.equals("")) {
            return redisTemplate.boundValueOps(key).get();
        }
        return null;
    }

    @Override
    public boolean has(K key) {
        if(null != key && !key.equals("")) {
            Boolean found = redisTemplate.hasKey(key);
            if(null != found) {
                return found;
            }
            return false;
        }
        return false;
    }
}
