package br.com.redbird.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
        info = @Info(
                title = "Redbird",
                version = "1.0.0",
                description = "Sistema que gere o cadastro de roupas"
        ),
        servers = {
                @Server(url = "localhost:8080", description = "Local URL"),
                @Server(url = "localhost:8088/monitoring", description = "Monitoring URL"),
        }
)
public class OpenApiConfig { }
