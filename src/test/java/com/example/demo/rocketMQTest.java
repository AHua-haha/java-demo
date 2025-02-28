package com.example.demo;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.entity.OrderDAO;
import com.example.demo.dao.entity.UserDAO;


@SpringBootTest
public class rocketMQTest {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void producerTest() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        Message rsg = new Message("TestTopic", "tag", "aaaaaaaaaaaaaaaaaaaaaaaaaaa".getBytes());
        SendResult res = producer.send(rsg);
        System.out.println(res);
        producer.shutdown();
    }

    DefaultMQPushConsumer buildConsumer() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("TestTopic", "*");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        return consumer;
    }

    @Test
    void consumerTest() throws Exception {
        DefaultMQPushConsumer consumer = buildConsumer();
        consumer.start();
    }

    @Test
    void testMQTemplate() {
        UserDAO userDO = UserDAO.builder().id(220000).name("fuck you").build();
        rocketMQTemplate.convertAndSend("order", userDO);
    }
    @Test
    void testOrderConsumer() {
        OrderDAO order = OrderDAO.builder().id(34567)
                    .user_id(344)
                    .product_id(22)
                    .count(40)
                    .build();
        rocketMQTemplate.convertAndSend("order", order);
    }

}
