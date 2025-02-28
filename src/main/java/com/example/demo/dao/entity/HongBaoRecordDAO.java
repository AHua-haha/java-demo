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
@TableName("t_hongbao_record")
public class HongBaoRecordDAO {
    private long id;
    private long user_id;
    private long hongbao_event_id;
    private long hongbao_id;
    private BigDecimal price;
}
