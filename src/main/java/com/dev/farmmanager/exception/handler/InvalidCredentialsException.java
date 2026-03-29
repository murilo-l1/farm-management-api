package com.dev.farmmanager.exception.handler;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;

public class InvalidCredentialsException extends UnauthorizedException {
    public InvalidCredentialsException() {
        super(ErrorMessage.INVALID_CREDENTIALS);
    }
}
