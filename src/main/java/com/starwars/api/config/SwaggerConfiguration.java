package com.starwars.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.starwars.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(informacoesApi());
    }

    private ApiInfo informacoesApi() {
        return new ApiInfo("API REST STAR WARS", "Criada para cadastrar planetas do Star Wars passando nome, clima e terreno. E para consumir uma API externa sendo " +
                "a API publica do StarWars para obter a quantidade de vezes que o planeta cadastrado apareceu nos filmes da franquia. " + "Para o desenvolvimento foi utilizado Java 8," +
                " Spring Boot, Mongodb e Docker.", "v1", "Api - Livre", new Contact("Tarik Wesley", "https://github.com/tarikwesley", "starwars@.com")
                , null, null, new ArrayList<>());
    }
}
