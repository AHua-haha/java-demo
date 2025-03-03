package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dao.entity.HongBaoEventDAO;
import com.example.demo.dao.entity.HongBaoRecordDAO;
import com.example.demo.dao.mapper.HongBaoEventMapper;
import com.example.demo.service.HongBaoService;
import com.example.demo.utils.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HongBaoServiceImpl implements HongBaoService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private HongBaoEventMapper hongBaoEventMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean createHongbaoEvent(HongBaoEventDAO hbEvent) {
        hongBaoEventMapper.insert(hbEvent);
        return true;
    }
    @Override
    public boolean beginHongbaoEvent(long hongbaoEventId) {
        HongBaoEventDAO event = hongBaoEventMapper.selectById(hongbaoEventId);
        System.out.println(event);
        long hongbaoNumber = event.getNumber();
        long total_price = event.getTotal_price().multiply(BigDecimal.valueOf(100)).longValue();
        List<HongBao> hongbaolist = generateHongbao(total_price, hongbaoNumber);
        log.info("generate hongbao: " + hongbaolist);
        redisTemplate.opsForList().leftPushAll("hbEvent:" + event.getId(), hongbaolist);
        return true;
    }
    @Override
    public List<HongBao> generateHongbao(long total_price, long number) {
        List<HongBao> arrayList = new ArrayList<HongBao>((int) number);

        Random random = new Random();

        for (int i = 1; i < number; i++) {
            int bound =  (int) (2 * ((double) total_price / number - i ) - 1) ;
            long price = random.nextInt(bound) + 1;
            HongBao hongBao = new HongBao();
            hongBao.id = Long.valueOf(i);
            hongBao.price = price;
            arrayList.add(hongBao);
            total_price = total_price - price;
        }
        HongBao hongBao = new HongBao();
        hongBao.id = number;
        hongBao.price = total_price;
        arrayList.add(hongBao);
        return arrayList;
    }
    @Override
    public boolean getHongbao(long eventId) {
        String key = "hbEvent:" + eventId;
        HongBao hongBao = (HongBao) redisTemplate.opsForList().rightPop(key);
        if(hongBao == null) {
            log.info("hong bao is empty");
            return false;
        }

        HongBaoRecordDAO record = HongBaoRecordDAO.builder()
                    .id(456)
                    .user_id(33)
                    .hongbao_event_id(eventId)
                    .hongbao_id(hongBao.id)
                    .price(BigDecimal.valueOf(hongBao.price / 100.0))
                    .build();
        redisTemplate.opsForList().leftPush(key + ":record", record);
        rocketMQTemplate.convertAndSend("hbRecord", record);;
        log.info("get hong bao, id: "+ hongBao.id + " price: " + record.getPrice());
        return true;
    }

}
