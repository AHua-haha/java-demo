package com.example.demo.service.impl;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.demo.dao.entity.UserDAO;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RBloomFilter<Long> bloomFilter;

    @Test
    void testSave() {
        UserDAO user = UserDAO.builder()
                    .id(4445)
                    .name("hahaha bb")
                    .build();
        userServiceImpl.save(user);
    }
    @Test
    void testUpdateWrapper() {
        LambdaUpdateWrapper<UserDAO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserDAO::getMail, "2242.zju.edu.cn")
                .set(UserDAO::getName, "update name");
        userServiceImpl.update(wrapper);
    }
    @Test
    void testUpdate() {
        UserDAO user = UserDAO.builder()
                    .id(4445)
                    .name("hahaha bb")
                    .mail("2242.zju.edu.cn")
                    .build();
        userServiceImpl.updateById(user);

        LambdaUpdateWrapper<UserDAO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserDAO::getMail, "2242.zju.edu.cn")
                .set(UserDAO::getName, "update name");
        userServiceImpl.update(wrapper);
    }
    @Test
    void insertData() {
        for (int i = 0; i < 1e5; i++) {
            UserDAO user = UserDAO.builder().id(i)
                                        .name("hello_" + i)
                                        .mail("ahua" + i + "@zju.edu.cn")
                                        .build();
            userServiceImpl.save(user);
        }
    }
    @Test
    void addBF() {
        for (long i = 0; i < 1e5; i++) {
            bloomFilter.add(i);
        }
    }
}
