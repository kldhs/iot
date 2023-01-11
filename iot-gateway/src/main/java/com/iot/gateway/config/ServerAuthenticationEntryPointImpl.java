package com.iot.gateway.config;


import com.iot.common.data.constant.CommonResult;
import com.iot.common.data.enums.ResultEnum;
import com.iot.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 登录失败自定义
 */
@Component
@RefreshScope
public class ServerAuthenticationEntryPointImpl implements ServerAuthenticationEntryPoint {

    @Value("${auth.client-redirect-uri}")
    private String clientRedirectUri;

    private static final Logger log = LoggerFactory.getLogger(ServerAuthenticationEntryPointImpl.class);


    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        String path = exchange.getRequest().getURI().getPath();
        var host = exchange.getRequest().getHeaders().getHost();
        log.info("not auth path:{}", path);
        var response = exchange.getResponse();
        DataBufferFactory dbf = response.bufferFactory();
        var result = CommonResult.failed(ResultEnum.USER_CUSTOM_LOGIN_ERR);
        result.setData((Objects.nonNull(host) ? host.getHostName() : clientRedirectUri));
        var resJson = JsonUtil.toBytes(result);
        var resData = dbf.wrap(resJson);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.just(resData));
    }
}
