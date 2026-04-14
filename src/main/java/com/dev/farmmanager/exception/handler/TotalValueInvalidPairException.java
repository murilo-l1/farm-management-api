package com.dev.farmmanager.exception.handler;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;

public class TotalValueInvalidPairException extends BadRequestException {
    public TotalValueInvalidPairException() {
        super(ErrorMessage.TOTAL_VALUE_INVALID_PAIR);
    }
}
