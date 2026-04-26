package com.dev.farmmanager.exception.handler;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;

public class StartDateGreaterThanEndDateException extends BadRequestException {
    public StartDateGreaterThanEndDateException() {
        super(ErrorMessage.START_DATE_GREATER_THAN_END_DATE);
    }
}
