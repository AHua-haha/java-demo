package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.entity.UserDO;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public UserDO getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/update/{id}")
    public void updateUser(UserDO user) {
        userService.updateUser(user);
    }
}
