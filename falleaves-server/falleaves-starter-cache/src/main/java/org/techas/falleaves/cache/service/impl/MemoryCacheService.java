package org.techas.falleaves.cache.service.impl;

import org.springframework.stereotype.Service;
import org.techas.falleaves.cache.service.ICacheService;

import java.util.concurrent.ConcurrentHashMap;

@Service(value = "memoryCacheService")
public class MemoryCacheService<K extends String, V> implements ICacheService<K, V> {

    private ConcurrentHashMap<K , V> map = new ConcurrentHashMap<>();

    @Override
    public boolean set(K key, V value, long expireTime) {
        return null != map.put(key, value);
    }

    @Override
    public boolean delete(K key) {
        return null != map.remove(key);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public boolean has(K key) {
        return map.containsKey(key);
    }
}
