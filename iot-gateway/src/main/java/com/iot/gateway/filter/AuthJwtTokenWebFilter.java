package com.iot.gateway.filter;

import com.iot.gateway.config.IgnorePathConfig;
import com.iot.common.data.constant.CommonResult;
import com.iot.common.data.constant.RedisKeyConstant;
import com.iot.common.data.enums.ResultEnum;
import com.iot.common.data.model.bo.gateway.UserInfo;
import com.iot.common.util.HttpResponseUtil;
import com.iot.common.util.jwt.JwtToken;
import com.iot.common.util.redis.RedisUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import java.util.List;
import java.util.Objects;

@Component
public class AuthJwtTokenWebFilter implements WebFilter {

    private final RedisUtil<?> redisUtil;

    private final IgnorePathConfig ignorePathConfig;

    public AuthJwtTokenWebFilter(RedisUtil<?> redisUtil, IgnorePathConfig ignorePathConfig) {
        this.redisUtil = redisUtil;
        this.ignorePathConfig = ignorePathConfig;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.hasText(token) && !ignorePathConfig.exist(exchange.getRequest().getPath().toString())){
            UserInfo userInfo = requestTokenCheck(token);
            if (Objects.isNull(userInfo)){
                var res = CommonResult.failed(ResultEnum.TOKEN_INVALID_ERR);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return HttpResponseUtil.sendJsonMsg(exchange,res);
            }
            //检查缓存数据
            boolean exists = redisUtil.exists(RedisKeyConstant.SYS_USER_LOGIN_DATA_PREFIX + userInfo.userId());
            if (exists) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userInfo, null, List.of(new SimpleGrantedAuthority(userInfo.roleId().toString())));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                return chain.filter(exchange).subscribeOn(Schedulers.boundedElastic()).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authenticationToken));
            }
        }
        return chain.filter(exchange);
    }

    private UserInfo requestTokenCheck(String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer")) {
            var jwtToken = token.replace("Bearer", "").trim();
            if (JwtToken.verify(jwtToken)) {
                return JwtToken.loadUserInfo(jwtToken);
            }
        }
        return null;
    }
}
