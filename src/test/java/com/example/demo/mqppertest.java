package com.example.demo;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.entity.HongBaoRecordDAO;
import com.example.demo.dao.entity.OrderDAO;
import com.example.demo.dao.entity.ProductDAO;
import com.example.demo.dao.mapper.HongBaoRecordMapper;
import com.example.demo.dao.mapper.OrderMapper;
import com.example.demo.dao.mapper.ProductMapper;

@SpringBootTest
public class mqppertest {
    @Autowired
    private HongBaoRecordMapper hongBaoRecordMapper;

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
    @Test
    void testHongBaoRecord() {
        HongBaoRecordDAO record = HongBaoRecordDAO.builder()
                    .id(22)
                    .user_id(33)
                    .hongbao_event_id(3)
                    .hongbao_id(2)
                    .price(BigDecimal.valueOf(33.44))
                    .build();;
        hongBaoRecordMapper.insert(record);
    }
    
}
