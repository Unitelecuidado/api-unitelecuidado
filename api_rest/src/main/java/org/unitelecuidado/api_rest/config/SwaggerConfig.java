package org.unitelecuidado.api_rest.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Unitelecuidado API")
                        .description("API Rest dedicada ao cadastro, atualização e leitura de pacientes e usuários do Unitelecuidado.")
                        .version("v1.0.0")
                        .license(new License().name("Documentação SpringDoc").url("http://springdoc.org"))
                        .contact(new Contact().email("victor.alves@ufcspa.edu.br")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório do projeto no GitHub")
                        .url("https://github.com/Unitelecuidado/api-unitelecuidado"));
    }
}