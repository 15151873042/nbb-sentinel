package com.nbb.sentinel.servicea.feign.fallback;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.nbb.sentinel.servicea.feign.ServiceBIndexFeignApi;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ServiceBIndexFeignFallbackFactory implements FallbackFactory<ServiceBIndexFeignApi> {
    @Override
    public ServiceBIndexFeignApi create(Throwable throwable) {
        return new ServiceBIndexFeignApi() {
            @Override
            public String getUserInfo() {
                if (throwable instanceof DegradeException) {
                    return "feign调用出错达到阈值，触发熔断降级....";
                } else {
                    return "feign调用出错，降级兜底，异常信息:" + throwable.getMessage();
                }
            }
        };
    }
}
