package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.entity.HongBaoEventDAO;
import com.example.demo.dao.mapper.HongBaoEventMapper;
import com.example.demo.service.HongBaoService;

@SpringBootTest
public class HongBaoServiceImplTest {
    @Autowired
    private HongBaoEventMapper hongBaoEventMapper;
    @Autowired
    private HongBaoService hongBaoService;


    @Test
    void testHongBaoGen() {
        List<HongBao> res = hongBaoService.generateHongbao(187, 3);
        System.out.println(res);
        int total = 0;
        for (HongBao hb : res) {
            total += hb.price;
        }
        System.out.println(total);

    }
    @Test
    void testBegin() {
        HongBaoEventDAO event = HongBaoEventDAO.builder()
                    .id(35l)
                    .user_id(22l)
                    .number(6l)
                    .total_price(BigDecimal.valueOf(22.89))
                    .build();
        hongBaoService.createHongbaoEvent(event);
        hongBaoService.beginHongbaoEvent(event.getId());
    }
    @Test
    void testMapper() {
        // hongBaoService.createHongbaoEvent(event);
        HongBaoEventDAO res = hongBaoEventMapper.selectById(22l);
        System.out.println(res.getUser_id());
        System.out.println(res);
    }
    @Test
    void testGethongbao() {
        hongBaoService.getHongbao(35);
    }
}
