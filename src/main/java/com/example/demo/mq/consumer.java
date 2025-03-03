package com.example.demo.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import com.example.demo.dao.entity.HongBaoRecordDAO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "counser-g-1", topic = "hbRecord")
public class consumer implements RocketMQListener<HongBaoRecordDAO> {

    @Override
    public void onMessage(HongBaoRecordDAO message) {
        System.err.println(message);

        log.info("record get");
    }
}
