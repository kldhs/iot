package com.iot.common.microservice.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 设置tomcat容器松弛的QueryChars属性，处理url中存在特殊字符串的情况
 */
@Configuration
public class WebServerConfig {

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers( connector -> connector.setProperty("relaxedQueryChars", "|{}[]\\"));
        return factory;
    }
}
