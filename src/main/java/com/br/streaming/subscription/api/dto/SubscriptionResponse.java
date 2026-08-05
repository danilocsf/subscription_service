package com.br.streaming.subscription.api.dto;
import java.time.LocalDate;
import java.util.UUID;

public record SubscriptionResponse(
        UUID id,
        UUID userId,
        String planName,
        LocalDate startDate,
        LocalDate expirationDate,
        String status
) {}