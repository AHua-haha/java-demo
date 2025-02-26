package com.example.demo;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.entity.OrderDAO;
import com.example.demo.dao.entity.ProductDAO;
import com.example.demo.dao.mapper.OrderMapper;
import com.example.demo.dao.mapper.ProductMapper;

@SpringBootTest
public class mqppertest {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;
    @Test
    void testOrder() {
        OrderDAO order = OrderDAO.builder()
            .id(24)
            .user_id(2)
            .product_id(33)
            .count(1)
            .build();
        orderMapper.insert(order);
    }
    @Test
    void testProduct() {
        ProductDAO product = ProductDAO.builder()
                    .id(22)
                    .name("wa ha ha")
                    .unit("bottle")
                    .price(BigDecimal.valueOf(12.25))
                    .build();
        productMapper.insert(product);
    }
    
}
