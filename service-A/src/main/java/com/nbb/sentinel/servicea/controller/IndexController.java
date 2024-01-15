package com.nbb.sentinel.servicea.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Value("${server.port}")
    private String port;


    @RequestMapping("/port")
    public String port() {
        return port;
    }

    /**
     * 熔断测试：当前时间戳为偶数则抛出异常
     */
    @RequestMapping("/degrade")
    public String degradeTest() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis % 2 == 0) {
            throw new RuntimeException("后台运行出错了");
        }
        return String.valueOf(currentTimeMillis);
    }

}
