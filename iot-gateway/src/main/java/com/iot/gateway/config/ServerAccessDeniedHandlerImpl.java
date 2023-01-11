package com.iot.gateway.config;

import com.iot.common.data.constant.CommonResult;
import com.iot.common.data.enums.ResultEnum;
import com.iot.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 没有权限处理
 */
@Component
public class ServerAccessDeniedHandlerImpl implements ServerAccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(ServerAccessDeniedHandlerImpl.class);

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        String path = exchange.getRequest().getURI().getPath();
        log.warn("path={}, access deny", path);
        var result = CommonResult.failed(ResultEnum.ACCESS_DENY);
        var response = exchange.getResponse();
        var dbf = response.bufferFactory();
        var data = dbf.wrap(JsonUtil.toBytes(result));
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.just(data));
    }
}
