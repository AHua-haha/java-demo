package com.example.demo.service;

import com.example.demo.dao.entity.OrderDAO;

public interface OrderService {
    boolean createOrder(OrderDAO order);
}
