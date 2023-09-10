package com.little.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI tinyOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Crud-System API")
                        .description("SpringDoc API 演示")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://github.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("CrudSystem Api 文档")
                        .url("https://github.com"));
    }

}
