package com.example.demo.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

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
}
