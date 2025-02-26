package com.example.demo.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import com.example.demo.dao.entity.UserDAO;

@Component
@RocketMQMessageListener(consumerGroup = "counser-g-1", topic = "topic")
public class consumer implements RocketMQListener<UserDAO> {

    @Override
    public void onMessage(UserDAO message) {
        System.out.println(message);
    }
}
