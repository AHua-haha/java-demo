package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.entity.OrderDAO;
import com.example.demo.dao.entity.ProductCountDAO;
import com.example.demo.dao.mapper.OrderMapper;
import com.example.demo.dao.mapper.ProductCountMapper;
import com.example.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductCountMapper productCountMapper;

    @Transactional
    @Override
    public boolean createOrder(OrderDAO order) {
        long product_id = order.getProduct_id();
        ProductCountDAO productCount = productCountMapper.selectById(product_id);
        long newCount = productCount.getCount() - order.getCount();
        productCount.setCount(newCount);

        productCountMapper.updateById(productCount);
        orderMapper.insert(order);

        return true;
    }
}
