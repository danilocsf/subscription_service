package com.br.streaming.subscription.api.controller;

import com.br.streaming.subscription.api.config.openapi.ApiStandardErrors;
import com.br.streaming.subscription.api.dto.CreateSubscriptionRequest;
import com.br.streaming.subscription.api.dto.ErrorResponse;
import com.br.streaming.subscription.api.dto.SubscriptionResponse;
import com.br.streaming.subscription.domain.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Subscriptions", description = "Subscription Management APIs")
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
}
