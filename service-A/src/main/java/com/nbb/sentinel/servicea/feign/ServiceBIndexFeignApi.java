package com.nbb.sentinel.servicea.feign;


import com.nbb.sentinel.servicea.feign.fallback.ServiceBIndexFeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        value = "service-B-app",
        contextId = "ServiceBIndexApi",
        path = "/index",
        fallbackFactory = ServiceBIndexFeignFallbackFactory.class)
public interface ServiceBIndexFeignApi {

    @GetMapping("/getUserInfo")
    String getUserInfo();

}
