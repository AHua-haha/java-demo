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
        redisUtil.set("exp", "aaabbb", 15);
        Object res = redisUtil.get("util");
        System.out.println(res);
    }
}
