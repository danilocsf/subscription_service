package com.br.streaming.subscription.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Dados do usuário assinante")
public record UserResponse(

        @Schema(description = "ID único do usuário", example = "9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d")
        UUID id,
        @Schema(description = "Nome do usuário", example = "João da Silva")
        String name,
        @Schema(description = "E-mail do usuário", example = "joao.silva@email.com")
        String email,
        @Schema(description = "Data e hora de criação do usuário")
        LocalDateTime createdAt
) {}