package com.dev.farmmanager.exception.handler;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;

public class TotalValueRequiredException extends BadRequestException {
    public TotalValueRequiredException() {
        super(ErrorMessage.TOTAL_VALUE_REQUIRED);
    }
}
