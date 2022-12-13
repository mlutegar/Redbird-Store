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

}
