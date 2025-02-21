package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.dao.entity.UserDO;

public interface UserService extends IService<UserDO> {
    UserDO getUser(long id);
    void updateUser(UserDO user);
}
