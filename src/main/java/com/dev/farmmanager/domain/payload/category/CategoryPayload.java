package com.dev.farmmanager.domain.payload.category;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CategoryPayload(
        @NotBlank(message = ErrorMessage.REQUIRED_NAME)
        @Size(min = 1, max = 255, message = ErrorMessage.INVALID_NAME_LENGTH)
        String name,

        @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = ErrorMessage.INVALID_COLOR)
        String color
) {
}
