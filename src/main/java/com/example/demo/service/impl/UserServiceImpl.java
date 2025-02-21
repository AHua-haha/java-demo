package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.entity.UserDO;
import com.example.demo.dao.mapper.UserDOMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisUtil;

@Service
public class UserServiceImpl extends ServiceImpl<UserDOMapper, UserDO> implements UserService {
    
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserDO getUser(long id) {
        String key = "user" + ":" + id;
        System.out.println(key);
        UserDO res = null;
        if(redisUtil.hasKey(key)) {
            res = (UserDO) redisUtil.get(key);
        } else {
            res = baseMapper.selectById(id);
            redisUtil.set(key, res);
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
