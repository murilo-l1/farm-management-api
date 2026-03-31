package com.dev.farmmanager.exception.handler;

import static com.dev.farmmanager.exception.handler.message.ErrorMessage.CATEGORY_NOT_FOUND;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException() {
        super(CATEGORY_NOT_FOUND);
    }
}
