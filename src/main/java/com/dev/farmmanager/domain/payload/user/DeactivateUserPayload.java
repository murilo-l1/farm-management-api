package com.dev.farmmanager.domain.payload.user;

import jakarta.validation.constraints.NotNull;

public record DeactivateUserPayload(@NotNull Boolean active) {
}
