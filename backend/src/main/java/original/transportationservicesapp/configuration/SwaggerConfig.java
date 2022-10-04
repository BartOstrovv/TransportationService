package original.transportationservicesapp.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApiConfig() {
        return new OpenAPI().info(apiInfo());
    }

    protected Info apiInfo() {
        return new Info().title("Transportation Services Application").description("For transport some products").version("0.1")
                .contact(new Contact().name("Taras").url("https://github.com/BartOstrovv")
                        .email("tapasz1488@gmail.com"));
    }
}
