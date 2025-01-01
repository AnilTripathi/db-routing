package com.db.routing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * OpenApiConfiguration is responsible for configuring the OpenAPI documentation
 * for the application. This includes setting up the API information, security
 * schemes,
 * and any additional customizations required for the OpenAPI specification.
 */
@Configuration
public class DockConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {

        return new OpenAPI()
                .info(new Info().title("Database Routing API").version("v1.0")
                        .description("DataBase Routing API Documetation")
                        .contact(new Contact().email("admin@dbrouting.co")
                                .name("Wellness360").url("https://dbrouting.co"))
                        .termsOfService("https://www.dbrouting.co/tou"));
    }

}
