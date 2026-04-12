package com.dev.farmmanager.exception.handler;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;

public class InvalidMeasurementUnitPairException extends BadRequestException {
    public InvalidMeasurementUnitPairException() {
        super(ErrorMessage.INVALID_MEASUREMENT_UNIT_PAIR);
    }
}
