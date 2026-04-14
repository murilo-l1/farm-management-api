package com.dev.farmmanager.exception.handler;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;

public class TransactionNotFoundException extends NotFoundException {
    public TransactionNotFoundException() {
        super(ErrorMessage.TRANSACTION_NOT_FOUND);
    }
}
