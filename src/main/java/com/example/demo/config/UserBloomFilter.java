package com.example.demo.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBloomFilter {

    @Autowired
    private RedissonClient redissonClient;

    @Bean
    public RBloomFilter<Long> userBF() {
        RBloomFilter<Long> bloomFilter = redissonClient.getBloomFilter("t_user");

        bloomFilter.tryInit(1000, 0.01);

        return bloomFilter;
    }
}
