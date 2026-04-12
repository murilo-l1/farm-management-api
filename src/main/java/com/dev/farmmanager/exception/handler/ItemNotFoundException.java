package com.dev.farmmanager.exception.handler;

import static com.dev.farmmanager.exception.handler.message.ErrorMessage.ITEM_NOT_FOUND;

public class ItemNotFoundException extends NotFoundException {
    public ItemNotFoundException() {
        super(ITEM_NOT_FOUND);
    }
}
