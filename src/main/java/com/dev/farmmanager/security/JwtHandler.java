package com.dev.farmmanager.security;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

import java.util.Date;

public interface JwtHandler {

    String generateToken(@NonNull @NotBlank final String email);

    boolean validateToken(@NonNull @NotBlank final String email, @NonNull @NotBlank final String token);

    String extractEmail(@NonNull @NotBlank final String token);

    Date extractExpiration(@NonNull @NotBlank final String token);

}
