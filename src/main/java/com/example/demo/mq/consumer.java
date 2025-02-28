package com.example.demo.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.dao.entity.OrderDAO;
import com.example.demo.service.OrderService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "counser-g-1", topic = "order")
public class consumer implements RocketMQListener<OrderDAO> {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderService orderService;

    @Override
    public void onMessage(OrderDAO message) {
        Boolean res = redisTemplate.opsForValue().setIfAbsent("order:order_id:" + message.getId(), 1);
        if( res == null || !res ) {
            log.info("message duplicate");
            return;
        }
        orderService.createOrder(message);
        log.info("message consume");
    }
}
