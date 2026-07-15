package com.nbb.sentinel.servicea.controller;

import com.nbb.sentinel.servicea.feign.ServiceBIndexFeignApi;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 熔断测试 Controller
 * <p>
 * 用于验证 Sentinel 的熔断降级功能，提供两种测试场景：
 * <ul>
 *     <li>本地接口直接抛异常</li>
 *     <li>通过 Feign 发起远程调用</li>
 * </ul>
 */
@RequestMapping("/test/degrade")
@RestController
public class DegradeTestController {

    /**
     * ServiceB 的 Feign 客户端，配置了 fallbackFactory，调用失败时会进入兜底逻辑
     */
    @Resource
    private ServiceBIndexFeignApi serviceBIndexFeignApi;

    /**
     * 本地接口异常测试。
     * <p>
     * flag 为 true 时抛出 RuntimeException，模拟业务异常，用于触发 Sentinel 熔断统计；
     * flag 为 false 时返回当前时间戳，表示正常调用。
     *
     * @param flag 是否抛出异常
     * @return 正常调用时返回当前时间戳字符串
     */
    @RequestMapping("/api/throwException/{flag}")
    public String testApiException(@PathVariable("flag") Boolean flag) {
        if (flag) {
            throw new RuntimeException("我抛出了一个异常");
        }
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * Feign 远程调用测试。
     * <p>
     * flag 为 true 时调用 service-B-app 的 getUserInfo 接口，
     * 若远程异常或被熔断，会进入 fallbackFactory 兜底逻辑；
     * flag 为 false 时不发起远程调用，直接返回提示信息。
     *
     * @param flag 是否发起远程调用
     * @return 远程调用结果或提示字符串
     */
    @RequestMapping("/feign/remoteCall/{flag}")
    public String testFeignException(@PathVariable("flag") boolean flag) {
        if (flag) {
            return serviceBIndexFeignApi.getUserInfo();
        }
        return "我没有触发feign远程调用";
    }
}