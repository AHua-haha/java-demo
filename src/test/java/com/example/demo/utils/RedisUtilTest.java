package com.example.demo.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;


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
}
