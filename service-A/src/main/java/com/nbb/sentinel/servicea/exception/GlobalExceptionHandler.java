package com.nbb.sentinel.servicea.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 接口熔断不能添加全局异常处理，因为全局异常处理会优先处理异常，这样就不能继续抛出异常了，sentinel就检测不到异常了
 */
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object asyncRequestTimeoutException(Exception e) {
        return "hupeng";
    }

}
