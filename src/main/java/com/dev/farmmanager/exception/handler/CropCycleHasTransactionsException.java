package com.dev.farmmanager.exception.handler;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;

public class CropCycleHasTransactionsException extends BadRequestException {
    public CropCycleHasTransactionsException(long count) {
        super(ErrorMessage.cropCycleHasTransactions(count));
    }
}
