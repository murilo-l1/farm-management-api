package com.dev.farmmanager.exception.handler;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;

public class UserAlreadyExistsException extends BadRequestException {
    public UserAlreadyExistsException() {
        super(ErrorMessage.ALREADY_EXISTS);
    }
}
