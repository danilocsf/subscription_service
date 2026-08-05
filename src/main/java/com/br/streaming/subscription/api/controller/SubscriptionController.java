package com.br.streaming.subscription.api.controller;

import com.br.streaming.subscription.api.dto.CreateSubscriptionRequest;
import com.br.streaming.subscription.api.dto.SubscriptionResponse;
import com.br.streaming.subscription.domain.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Operation(summary = "Create a new subscription for a user")
    public SubscriptionResponse createSubscription(@RequestBody CreateSubscriptionRequest request) {
        return subscriptionService.createSubscription(request);
    }
}
