package com.nbb.sentinel.servicea.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流控测试控制器
 * <p>
 * 用于演示 Sentinel 的流量控制（限流）功能。
 * 通过模拟接口处理耗时，便于触发限流规则并观察效果。
 */
@RequestMapping("/test/flow")
@RestController
public class FlowTestController {

    /**
     * 模拟耗时接口
     * <p>
     * 根据传入的 time 参数，循环休眠以模拟接口处理耗时，
     * 每秒输出一次当前线程名称，并最终返回处理该请求的线程名称。
     * 常用于触发 Sentinel 的流控规则（如 QPS 限流、线程数限流）。
     *
     * @param time 休眠的秒数（循环次数）
     * @return 处理请求的线程名称
     * @throws InterruptedException 如果线程在休眠期间被中断
     */
    @GetMapping("/api/sleep/{time}")
    public String sleep(@PathVariable("time") Long time) throws InterruptedException {
        for(int i=0; i<time; i++) {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000L);
        }

        return Thread.currentThread().getName();
    }

}