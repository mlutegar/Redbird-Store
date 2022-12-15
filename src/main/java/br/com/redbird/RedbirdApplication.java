package br.com.redbird;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedbirdApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedbirdApplication.class, args);
    }

    @Bean
    public OpenAPI openApiConfig() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Redbird")
                .description("Sistema que gere o cadastro de roupas")
                .version("1.0.0");
    }
}
