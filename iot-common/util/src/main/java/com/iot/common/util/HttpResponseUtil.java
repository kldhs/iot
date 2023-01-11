package com.iot.common.util;

import com.iot.common.data.constant.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpResponseUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpResponseUtil.class);

    private HttpResponseUtil() {
    }

    public static Mono<Void> sendJsonMsg(ServerWebExchange exchange, CommonResult<?> result) {
        var response = exchange.getResponse();
        var dbf = response.bufferFactory();
        var json = JsonUtil.toBytes(result);
        var bf = dbf.wrap(json);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Flux.just(bf));
    }

    public static void sendCaptcha(HttpServletResponse response, BufferedImage image) {
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        try {
            ImageIO.write(image, "jpeg", response.getOutputStream());
        } catch (IOException e) {
            log.error("write captcha code error", e);
        }
    }
}
