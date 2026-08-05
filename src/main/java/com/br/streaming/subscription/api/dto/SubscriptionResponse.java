package com.br.streaming.subscription.api.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Erros da API")
public record SubscriptionResponse(
        @Schema(description = "Id da assinatura", example = "987fc567-e89b-12d3-a456-426614174111")
        UUID id,
        @Schema(description = "Nome do usuário", example = "José")
        String userName,
        @Schema(description = "Email do usuário", example = "jose@example.com")
        String userEmail,
        @Schema(description = "Nome do plano", example = "Básico")
        String planName,
        @Schema(description = "Data de início do plano",  example = "2026-08-05")
        LocalDate startDate,
        @Schema(description = "Data de expiração do plano", example = "2026-08-05")
        LocalDate expirationDate,
        @Schema(description = "Status da assinatura", example = "ATIVA")
        String status
) {}