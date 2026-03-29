package com.dev.farmmanager.exception.handler;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;

public class BlankCredentialsException extends BadRequestException {
    public BlankCredentialsException() {
        super(ErrorMessage.BLANK_CREDENTIALS);
    }
}
