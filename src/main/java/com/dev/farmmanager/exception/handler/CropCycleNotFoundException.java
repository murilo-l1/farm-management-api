package com.dev.farmmanager.exception.handler;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;

public class CropCycleNotFoundException extends NotFoundException {
    public CropCycleNotFoundException() {
        super(ErrorMessage.CROP_CYCLE_NOT_FOUND);
    }
}
