package com.br.streaming.subscription.domain.exception;

public class NotFoundException extends BusinessException{
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Object... args) {
        super(message, args);
    }
}
