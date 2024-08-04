package com.nbb.sentinel.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class IndexController {

    @RequestMapping("/test")
    public Mono<String> test() {
        return Mono.just("test");
    }
}
