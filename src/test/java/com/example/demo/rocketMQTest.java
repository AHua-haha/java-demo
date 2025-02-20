package com.example.demo;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class rocketMQTest {

    @Test
    void producerTest() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        Message rsg = new Message("TestTopic", "tag", "hello world".getBytes());
        SendResult res = producer.send(rsg);
        System.out.println(res);
        producer.shutdown();
    }

}
