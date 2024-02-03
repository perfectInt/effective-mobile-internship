package ru.effectivemobile;

import redis.clients.jedis.Jedis;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class RedisMap implements Map<String, String> {

    private final Jedis jedis;

    public RedisMap() {
        this("localhost", 6379);
    }

    public RedisMap(String host, Integer port) {
        this.jedis = new Jedis(host, port);
    }

    @Override
    public int size() {
        return (int) jedis.dbSize();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return jedis.exists((String) key);
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get(Object key) {
        return jedis.get((String) key);
    }

    @Override
    public String put(String key, String value) {
        jedis.set(key, value);
        return value;
    }

    @Override
    public String remove(Object key) {
        jedis.del((String) key);
        return (String) key;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        jedis.keys("*").forEach(jedis::del);
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        throw new UnsupportedOperationException();
    }
}
