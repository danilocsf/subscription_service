package com.br.streaming.subscription.api.controller;

import com.br.streaming.subscription.api.config.openapi.ApiStandardErrors;
import com.br.streaming.subscription.api.dto.CreateSubscriptionRequest;
import com.br.streaming.subscription.api.dto.ErrorResponse;
import com.br.streaming.subscription.api.dto.SubscriptionResponse;
import com.br.streaming.subscription.domain.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Assinaturas", description = "APIs de Gerenciamento de Assinaturas")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria uma nova assiatura para um usuário")
    @ApiStandardErrors
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Assinatura criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubscriptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário ou plano não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public SubscriptionResponse createSubscription(@Valid @RequestBody CreateSubscriptionRequest request) {
        return subscriptionService.createSubscription(request);
    }

    @PostMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Cancela uma assinatura ativa")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Assinatura cancelada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Assinatura não encontrada para o ID informado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @ApiStandardErrors
    public void cancelSubscription(
            @Parameter(
                    name = "id",
                    description = "ID da assinatura a ser cancelada",
                    example = "a3f12345-e89b-12d3-a456-426614174999",
                    required = true
            )
            @PathVariable("id") UUID id) {
        subscriptionService.cancelSubscription(id);
    }
}
