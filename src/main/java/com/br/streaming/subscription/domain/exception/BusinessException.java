package com.br.streaming.subscription.domain.exception;

import java.text.MessageFormat;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
    public BusinessException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }
}