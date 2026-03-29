package com.dev.farmmanager.exception.handler;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super(ErrorMessage.USER_NOT_FOUND);
    }
}
