package com.example.demo.service.impl;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.entity.UserDO;
import com.example.demo.dao.mapper.UserDOMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserDOMapper, UserDO> implements UserService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RBloomFilter<Long> bloomFilter;

    @Override
    public UserDO getUser(long id) {
        if (!bloomFilter.contains(id)) {
            log.info("not exist");
            return null;
        }

        String key = "user" + ":" + id;
        System.out.println(key);
        UserDO res = null;
        if (redisUtil.hasKey(key)) {
            log.info("redis");
            res = (UserDO) redisUtil.get(key);
            return res;
        }
        log.info("database");
        RLock lock = redissonClient.getLock(key);
        lock.lock();
        try {
            if (redisUtil.hasKey(key)) {
                return (UserDO) redisUtil.get(key);
            }
            res = baseMapper.selectById(id);
            redisUtil.set(key, res);
        } finally {
            lock.unlock();
        }
        return res;
    }

    @Override
    public void updateUser(UserDO user) {
        baseMapper.updateById(user);
        String key = "user" + ":" + user.getId();
        redisUtil.del(key);
    }
}
