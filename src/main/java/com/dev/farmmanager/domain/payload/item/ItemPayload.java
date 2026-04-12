package com.dev.farmmanager.domain.payload.item;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ItemPayload(
        @NotBlank(message = ErrorMessage.REQUIRED_NAME)
        @Size(max = 255, message = ErrorMessage.INVALID_NAME_LENGTH)
        String name,

        @Size(max = 50, message = ErrorMessage.INVALID_UNITY_LENGTH)
        String unity,

        @Size(max = 255, message = ErrorMessage.INVALID_BRAND_LENGTH)
        String brand,

        Integer categoryId
) {}
