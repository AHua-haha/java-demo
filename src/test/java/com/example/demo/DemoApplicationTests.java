package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.dao.entity.UserDAO;
import com.example.demo.dao.mapper.UserDOMapper;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private UserDOMapper userDOMapper;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	void contextLoads() {
		LambdaQueryWrapper<UserDAO> query = new LambdaQueryWrapper<>();

		query.eq(UserDAO::getName, "test");
		List<UserDAO> res = userDOMapper.selectList(query);
		res.forEach(System.out::println);
	}

	@Test
	void redisTemplateTest() {
		redisTemplate.opsForValue().set("bb", "hello");
	}

}
