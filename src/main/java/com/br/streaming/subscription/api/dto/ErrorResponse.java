package com.br.streaming.subscription.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Erros da API")
public record ErrorResponse(
    @Schema(description = "Data e hora em que o erro ocorreu", example = "2026-08-05T09:30:00")
    LocalDateTime timestamp,
    @Schema(description = "Código de status HTTP do erro", example = "400")
    int status,
    @Schema(description = "Descrição curta do status do erro", example = "Bad Request")
    String error,
    @Schema(description = "Motivo do erro", example = "Um ou mais campos estão inválidos")
    String message,
    @Schema(description = "Lista detalhada dos erros ocorridos por campo")
    List<ErrorDetails> errors
) {

    public ErrorResponse(int status, String error, String message) {
        this(LocalDateTime.now(), status, error, message, null);
    }
    public ErrorResponse(int status, String error, String message, List<ErrorDetails> errors) {
        this(LocalDateTime.now(), status, error, message, errors);
    }
}