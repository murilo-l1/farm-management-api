package com.dev.farmmanager.domain.payload.user;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUserPayload(
        @NotBlank(message = ErrorMessage.REQUIRED_NAME) @Size(min = 5, max = 100, message = ErrorMessage.INVALID_NAME_LENGTH)
        String name,
        @NotBlank(message = ErrorMessage.REQUIRED_EMAIL) @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = ErrorMessage.INVALID_EMAIL)
        String email,
        @NotBlank(message = ErrorMessage.REQUIRED_PHONE) @Size(min = 10, max = 11, message = ErrorMessage.INVALID_PHONE_LENGTH)
        String phone,
        @NotNull
        Boolean active
) { }
