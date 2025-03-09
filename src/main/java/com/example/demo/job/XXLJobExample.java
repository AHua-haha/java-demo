package com.example.demo.job;

import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;

@Component
public class XXLJobExample {

    @XxlJob("echo")
    public ReturnT<String> execute(String... param) throws Exception {
        System.out.println("hello xxl job");
        return ReturnT.SUCCESS;
    }

}
