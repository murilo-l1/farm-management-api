package com.dev.farmmanager.domain.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NonNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JwtDto(@NonNull Long expiresIn) {
}
