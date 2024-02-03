package ru.effectivemobile;

public class Main {
    public static void main(String[] args) {
        RedisMap redisMap = new RedisMap();
        redisMap.put("key1", "value1");
        redisMap.put("key2", "value2");
        redisMap.put("key3", "value3");

        System.out.println(redisMap.get("key1"));
        System.out.println(redisMap.get("key2"));
        System.out.println(redisMap.get("key3"));
    }
}