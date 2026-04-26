package com.dev.farmmanager.exception.handler;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;

public class CategoryHasItemsException extends BadRequestException {
    public CategoryHasItemsException(long count) {
        super(ErrorMessage.categoryHasItems(count));
    }
}
