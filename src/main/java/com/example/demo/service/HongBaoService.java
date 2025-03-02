package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.entity.HongBaoEventDAO;
import com.example.demo.service.impl.HongBao;

public interface HongBaoService {

    boolean createHongbaoEvent(HongBaoEventDAO hbEvent);
    boolean beginHongbaoEvent(long hongbaoEventId);
    List<HongBao> generateHongbao(long total_price, long number);

    boolean getHongbao(long eventId);

}
