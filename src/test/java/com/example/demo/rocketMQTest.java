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
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class rocketMQTest {

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

}
