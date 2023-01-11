package com.iot.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@RestController
public class FallbackController {
    Logger logger = LoggerFactory.getLogger(FallbackController.class);

    /**
     * 进入熔断降级机制时，就会调用该方法
     *
     * @param exchange
     * @return
     */
    @RequestMapping("/fallback")
    public Object fallback(ServerWebExchange exchange, Throwable throwable) {
        Map<String, Object> result = new HashMap<>();
        Exception exception = exchange.getAttribute(ServerWebExchangeUtils.CIRCUITBREAKER_EXECUTION_EXCEPTION_ATTR);
        ServerWebExchange delegate = ((ServerWebExchangeDecorator) exchange).getDelegate();
        logger.error("接口调用失败，URL={}", delegate.getRequest().getURI(), exception);
        result.put("code", 500);
        result.put("msg", "系统异常，请稍后重试");
        return result;
    }

}
