package com.example.demo.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import com.example.demo.dao.entity.UserDAO;

@SpringBootTest
public class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testRedisUtil() {
        redisUtil.set("util", "aaabbb");
        Object res = redisUtil.get("util");
        System.out.println(res);
    }

    @Test
    void testExpire() {
        redisUtil.set("exp", "aaabbb", 30);
        Object res = redisUtil.get("exp");
        System.out.println(res);
    }

    @Test
    void testDelete() {
        System.out.println(redisUtil.get("exp"));
        System.out.println(redisUtil.get("util"));

        redisUtil.del("exp", "util");
    }

    @Test
    void testRedisTemplateExecute() {
        String luaScript = "local current_value = tonumber(redis.call('GET', KEYS[1])) " +
                "if current_value and current_value > 0 then " +
                "    redis.call('DECR', KEYS[1]) " +
                "    return current_value - 1 " +
                "else " +
                "    return current_value " +
                "end";
        RedisScript<Long> redisScript = RedisScript.of(luaScript, Long.class);
        Long res = (Long) redisTemplate.execute(redisScript, Collections.singletonList("my_key"));
        System.out.println(res);
    }
    @Test
    void testScritp() {
        // List<Integer> value = Arrays.asList(0, 2, 33, 44, 454, 567);
        // redisTemplate.opsForList().rightPushAll("aaa", value);
        
        Float value = 22.90f;
        redisTemplate.opsForValue().set("test", value);
        Float res = (Float) redisTemplate.opsForValue().get("test");
    }
    void atomicPop(String key) {
        String luaScript = 
        "local len = redis.call('llen', KEYS[1])" + 
        "if len and len > 0 then " + 
        "   local value = redis.call('lpop', KEYS[1]) " +
        "   return {len, value} " +
        "else " +
        "   return { 0, nil } " +
        "end ";
        RedisScript<Object> redisScript = RedisScript.of(luaScript, Object.class);
        List<String> keys = Arrays.asList(key);
        List<Long> res = (List<Long>) redisTemplate.execute(redisScript, keys);
        Long first = res.get(0);
        Long second = res.get(1);
    }
}
