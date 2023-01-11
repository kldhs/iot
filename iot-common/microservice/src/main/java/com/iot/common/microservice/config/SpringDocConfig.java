package com.iot.common.microservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RefreshScope
@ConditionalOnProperty(name = "springdoc.server.enable", havingValue = "true")
public class SpringDocConfig {
    private static final String AUTH_BEARER = "bearer";

    @Value("${springdoc.server.url:}")
    private String serverUrl;

    @Value("${springdoc.server.title:}")
    private String title;

    @Value("${springdoc.server.description:}")
    private String description;

    @Bean
    public OpenAPI openAPI() {
        Server server = new Server();
        server.setUrl(serverUrl);
        return new OpenAPI()
                .info(new Info().title(title)
                        .description(description)
                        .version("v1.0.0"))
                .servers(List.of(server))
                .addSecurityItem(new SecurityRequirement().addList(AUTH_BEARER))
                .components(new Components().addSecuritySchemes(AUTH_BEARER,
                        new SecurityScheme()
                                .name(AUTH_BEARER)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme(AUTH_BEARER)
                                .bearerFormat("jwt")));
    }
}
