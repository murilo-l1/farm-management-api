package com.dev.farmmanager.exception.handler;

import static com.dev.farmmanager.exception.handler.message.ErrorMessage.STAKEHOLDER_NOT_FOUND;

public class StakeholderNotFoundException extends NotFoundException {
    public StakeholderNotFoundException() {
        super(STAKEHOLDER_NOT_FOUND);
    }
}
