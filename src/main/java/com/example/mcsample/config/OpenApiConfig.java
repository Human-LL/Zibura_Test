package com.example.mcsample.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String VERSION = "dev";
    private static final String TITLE = "Шаблонный проект микросервиса";
    private static final String DESCRIPTION = "Шаблонный проект микросервиса";
    private static final Contact CONTACT = new Contact()
            .name("Zibura Z.Z.")
            .email("zibura.z.ge@zibura.ru");

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .version(VERSION)
                        .title(TITLE)
                        .contact(CONTACT)
                        .description(DESCRIPTION));
    }


}

