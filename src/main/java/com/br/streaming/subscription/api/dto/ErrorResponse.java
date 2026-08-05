package com.br.streaming.subscription.api.dto;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, String message) {
}
