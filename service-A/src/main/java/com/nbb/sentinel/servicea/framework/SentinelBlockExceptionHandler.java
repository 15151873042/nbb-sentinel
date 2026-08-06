package com.nbb.sentinel.servicea.framework;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class SentinelBlockExceptionHandler implements BlockExceptionHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        // 设置响应格式与状态码 429 请求过多
        response.setStatus(429);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();

        String msg;

        if (e instanceof DegradeException) {
            msg = "服务降级了";
        } else if (e instanceof FlowException) {
            msg = "服务限流了";
        } else {
            msg = "sentinel 未知的异常:" + e.getMessage();
        }

        writer.write(msg);
        writer.flush();
        writer.close();
    }
}