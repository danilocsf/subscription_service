package com.br.streaming.subscription.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Detalhe do erro associado a um campo específico da requisição")
public record ErrorDetails(
    @Schema(description = "Nome do campo que falhou na validação", example = "userId")
    String field,
    @Schema(description = "Mensagem informando o motivo da falha", example = "O ID do usuário é obrigatório")
    String message
){}
