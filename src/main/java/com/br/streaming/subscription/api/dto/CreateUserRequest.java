package com.br.streaming.subscription.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Payload para criação de um novo usuário assinante")
public record CreateUserRequest(

        @Schema(description = "Nome completo do usuário", example = "João da Silva")
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @Schema(description = "Endereço de e-mail único do usuário", example = "joao.silva@email.com")
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail deve ser válido")
        String email,

        @Schema(description = "Token do cartão - utilizado para testes no momento", example = "CARD_SUCCESS")
        @NotBlank(message = "O token do cartão é obrigatório")
        String cardToken
) {}