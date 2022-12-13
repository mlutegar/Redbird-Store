package br.com.redbird.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
        info = @Info(
                title = "To Do WebService RestFul",
                version = "1.0.0",
                description = "To Do Manager",
                contact = @Contact(name = "Senac Rio", email = "suporte@rj.senac.edu", url = "rj.senac.br")
        ),
        servers = {
                @Server(url = "localhost:8080", description = "Local URL"),
                @Server(url = "localhost:8088/monitoring", description = "Monitoring URL"),
        }
)
public class OpenApiConfig { }
