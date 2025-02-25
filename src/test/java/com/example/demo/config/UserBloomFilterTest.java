package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserBloomFilterTest {

    @Autowired
    private RBloomFilter<Long> bloomFilter;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void deleteBloomFilter() {
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("t_user");;
        bloomFilter.delete();
    }
    @Test
    void testUserBF() {
        bloomFilter.add(22l);
        boolean res = bloomFilter.contains(22l);
        System.out.println(res);
    }
    @Test
    void testLock() {
        RLock lock = redissonClient.getLock("test_lock");;
        lock.lock();
        try {
            Thread.sleep(15000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        lock.unlock();
    }
}
