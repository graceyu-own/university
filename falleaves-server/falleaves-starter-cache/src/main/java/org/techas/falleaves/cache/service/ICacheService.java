package org.techas.falleaves.cache.service;

public interface ICacheService<K extends String, V> {

    /**
     *
     * @param key   键
     * @param value 值
     * @param expireTime 毫秒
     * @return 是否成功
     */
    boolean set(K key, V value, long expireTime);

    boolean delete(K key);

    V get(K key);

    boolean has(K key);

}
