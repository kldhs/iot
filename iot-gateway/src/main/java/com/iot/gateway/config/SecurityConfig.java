package com.iot.gateway.config;


import com.iot.gateway.filter.AuthJwtTokenWebFilter;
import com.iot.gateway.manager.MyAuthorizationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.annotation.Resource;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Resource
    private MyAuthorizationManager authorizationManager;

    @Resource
    private IgnorePathConfig ignorePathConfig;

    @Resource
    private AuthJwtTokenWebFilter authJwtTokenWebFilter;

    @Resource
    private ServerAccessDeniedHandlerImpl serverAccessDeniedHandler;

    @Resource
    private ServerAuthenticationEntryPointImpl serverAuthenticationEntryPoint;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity.httpBasic().disable()
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(ignorePathConfig.getMergePathsArray()).permitAll()
                .anyExchange().access(authorizationManager)
                .and().formLogin().disable()
                .logout().disable()
                .exceptionHandling().authenticationEntryPoint(serverAuthenticationEntryPoint).accessDeniedHandler(serverAccessDeniedHandler)
                .and()
                .addFilterBefore(authJwtTokenWebFilter, SecurityWebFiltersOrder.HTTP_BASIC);
        return httpSecurity.build();
    }
}
