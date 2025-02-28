package com.example.demo.dao.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_hongbao_event")
public class HongBaoEventDAO {
    private Long id;
    private Long user_id;
    private Long number;
    private BigDecimal total_price;
    private LocalDateTime begin_time;
}
