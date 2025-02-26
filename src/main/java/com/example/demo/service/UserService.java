package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.dao.entity.UserDAO;

public interface UserService extends IService<UserDAO> {
    UserDAO getUser(long id);
    void updateUser(UserDAO user);
}
