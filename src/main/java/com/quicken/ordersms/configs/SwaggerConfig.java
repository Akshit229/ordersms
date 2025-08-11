package com.quicken.ordersms.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OrdersMS APIs")
                        .version("1.0")
                        .description("[Akshit Goyal] - Quicken's Assignment - API documentation for managing Orders and Products."));
    }
}
