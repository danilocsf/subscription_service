package com.br.streaming.subscription.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Payload para criação de uma nova assinatura")
public record CreateSubscriptionRequest(
        @NotNull(message = "O ID do usuário é obrigatório")
        @Schema(
                description = "ID do usuário que está adiquirindo uma assinatura",
                example = "987fc567-e89b-12d3-a456-426614174111"
        )
        UUID userId,
        @NotNull(message = "O ID do plano é obrigatório")
        @Schema(
                description = "ID do plano de assinatura selecionado",
                example = "a3f12345-e89b-12d3-a456-426614174999"
        )
        UUID planId
){}