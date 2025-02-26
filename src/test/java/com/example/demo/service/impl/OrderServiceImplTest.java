package com.example.demo.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.entity.OrderDAO;
import com.example.demo.service.OrderService;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;


    @Test
    void testCreateOrder() {
        OrderDAO order = OrderDAO.builder()
                    .id(334334)
                    .user_id(22)
                    .product_id(22)
                    .count(33)
                    .build();
        orderService.createOrder(order);
    }
}
