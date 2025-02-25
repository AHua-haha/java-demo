package com.example.demo.service.impl;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.demo.dao.entity.UserDO;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RBloomFilter<Long> bloomFilter;

    @Test
    void testSave() {
        UserDO user = UserDO.builder()
                    .id(4445)
                    .name("hahaha bb")
                    .build();
        userServiceImpl.save(user);
    }
    @Test
    void testUpdateWrapper() {
        LambdaUpdateWrapper<UserDO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserDO::getMail, "2242.zju.edu.cn")
                .set(UserDO::getName, "update name");
        userServiceImpl.update(wrapper);
    }
    @Test
    void testUpdate() {
        UserDO user = UserDO.builder()
                    .id(4445)
                    .name("hahaha bb")
                    .mail("2242.zju.edu.cn")
                    .build();
        userServiceImpl.updateById(user);

        LambdaUpdateWrapper<UserDO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserDO::getMail, "2242.zju.edu.cn")
                .set(UserDO::getName, "update name");
        userServiceImpl.update(wrapper);
    }
    @Test
    void insertData() {
        for (int i = 0; i < 1e5; i++) {
            UserDO user = UserDO.builder().id(i)
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
