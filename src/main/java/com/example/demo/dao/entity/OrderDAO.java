package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order")
public class OrderDAO {
    private long id;
    private long user_id;
    private long product_id;
    private long count;
}
