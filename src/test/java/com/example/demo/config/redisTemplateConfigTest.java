package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.example.demo.dao.entity.UserDAO;

@SpringBootTest
public class redisTemplateConfigTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testSimpleOps() {
        UserDAO userDO = new UserDAO();
        redisTemplate.opsForValue().set("testkey", 22);
        Object res = redisTemplate.opsForValue().get("testkey");;
        System.out.println(res);
    }
    @Test
    void testCluster() {
        UserDAO userDO = new UserDAO();
        redisTemplate.opsForValue().set("testkey", userDO);
        Object res = redisTemplate.opsForValue().get("testkey");;
        System.out.println(res);
    }
}
