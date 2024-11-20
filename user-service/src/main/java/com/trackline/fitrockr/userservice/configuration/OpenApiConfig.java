package com.trackline.fitrockr.userservice.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig
{
    @Bean
    public OpenAPI api()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("Fitrockr User Service")
                        .description("Handling of users")
                        .version("0.1.0")
                        .license(new License().name("proprietary license")));
    }

}