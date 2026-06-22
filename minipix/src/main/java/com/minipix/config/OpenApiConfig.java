package com.minipix.config;

import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.Components;

import io.swagger.v3.oas.models.security.*;

import org.springframework.context.annotation.*;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        SecurityScheme securityScheme =
                new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT");

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("BearerAuth",securityScheme))
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("BearerAuth")
                );
    }

}
