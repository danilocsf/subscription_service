package com.br.streaming.subscription.api.config.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API para assinaturas de streaming",
                version = "1.0.0",
                description = "API para gerenciamento de assinaturas e planos de plataforma de streaming"
        )
)
public class OpenAPIConfig {
}
