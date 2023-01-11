package com.iot.gateway.filter;


import com.iot.common.data.constant.RequestHeaderConstant;
import com.iot.common.data.model.bo.gateway.UserInfo;
import com.iot.common.util.jwt.JwtToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 添加全局过滤器
 * 获取用户信息，放在请求头传递到服务中
 */
@Component
public class UserInfoGlobalFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(UserInfoGlobalFilter.class);

    private static final String AUTH_HEADER = "Authorization";
    private static final String AUTH_BEARER = "Bearer";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String auth = exchange.getRequest().getHeaders().getFirst(AUTH_HEADER);
        if (StringUtils.hasText(auth) && auth.contains(AUTH_BEARER)) {
            String token = auth.replace(AUTH_BEARER, "").trim();
            log.debug("token={}", token);
            return getUsername(token).map(userInfo -> {
                var req = exchange.getRequest()
                        .mutate()
                        .header(RequestHeaderConstant.HEADER_USERID, userInfo.userId().toString())
                        .header(RequestHeaderConstant.HEADER_COMPANY_ID,userInfo.companyId().toString())
                        .header(RequestHeaderConstant.HEADER_ROLE_ADMIN, userInfo.admin().toString())
                        .build();
                return exchange.mutate().request(req).build();
            }).flatMap(chain::filter);
        } else {
            return chain.filter(exchange);
        }
    }

    private Mono<UserInfo> getUsername(String token) {
        UserInfo userInfo = JwtToken.loadUserInfo(token);
        log.debug("userId={},companyId={}", userInfo.userId(),userInfo.companyId());
        return Mono.just(userInfo);

    }

    @Override
    public int getOrder() {
        return 1;
    }
}
