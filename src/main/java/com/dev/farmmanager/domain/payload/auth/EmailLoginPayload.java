package com.dev.farmmanager.domain.payload.auth;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmailLoginPayload(
        @NotBlank(message = ErrorMessage.REQUIRED_EMAIL) @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = ErrorMessage.INVALID_EMAIL) String email,
        @NotBlank(message = ErrorMessage.REQUIRED_PASSWORD) @Size(min = 8, max = 100, message = ErrorMessage.INVALID_PASSWORD_LENGTH) String password
) {}
