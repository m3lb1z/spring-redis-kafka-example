package dev.emrx.users.configs;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Users API")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .version("1.0")
                        .contact(new Contact()
                                .email("contacto@dev3z.com")
//                                .url("http://dev3z.com")
                                .name("@dev3z.com"))
                );
    }
}
