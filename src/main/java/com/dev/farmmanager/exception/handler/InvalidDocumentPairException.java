package com.dev.farmmanager.exception.handler;

import static com.dev.farmmanager.exception.handler.message.ErrorMessage.INVALID_DOCUMENT_PAIR;

public class InvalidDocumentPairException extends BadRequestException {
    public InvalidDocumentPairException() {
        super(INVALID_DOCUMENT_PAIR);
    }
}
