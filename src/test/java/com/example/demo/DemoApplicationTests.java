package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.dao.entity.UserDO;
import com.example.demo.dao.mapper.UserDOMapper;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private UserDOMapper userDOMapper;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	void contextLoads() {
		LambdaQueryWrapper<UserDO> query = new LambdaQueryWrapper<>();
		query.eq(UserDO::getName, "test");
		List<UserDO> res = userDOMapper.selectList(query);
		res.forEach(System.out::println);
	}

	@Test
	void redisTemplateTest() {
		redisTemplate.opsForValue().set("bb", "hello");
	}

}
