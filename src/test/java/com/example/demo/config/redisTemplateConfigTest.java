package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.example.demo.dao.entity.UserDO;

@SpringBootTest
public class redisTemplateConfigTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testSimpleOps() {
        UserDO userDO = new UserDO();
        redisTemplate.opsForValue().set("testkey", 22);
        Object res = redisTemplate.opsForValue().get("testkey");;
        System.out.println(res);
    }
}
