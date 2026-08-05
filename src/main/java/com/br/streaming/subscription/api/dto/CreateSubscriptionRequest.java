package com.br.streaming.subscription.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateSubscriptionRequest(@NotNull UUID userId, @NotNull UUID planId) {}