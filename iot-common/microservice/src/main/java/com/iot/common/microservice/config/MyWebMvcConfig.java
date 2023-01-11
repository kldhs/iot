package com.iot.common.microservice.config;


import com.iot.common.microservice.interceptor.MyHandlerInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class MyWebMvcConfig implements WebMvcConfigurer {

    private final MyHandlerInterceptor myHandlerInterceptor;

    public MyWebMvcConfig(MyHandlerInterceptor myHandlerInterceptor) {
        this.myHandlerInterceptor = myHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myHandlerInterceptor)
                .addPathPatterns("/**");
    }
}
