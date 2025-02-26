package com.example.demo.dao.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_product")
public class ProductDAO {
    private long id;
    private String name;
    private String type;
    private String unit;
    private BigDecimal price;
}
