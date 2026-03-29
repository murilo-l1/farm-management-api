package com.dev.farmmanager.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public final class SecurityUtils {

    private SecurityUtils() {}

    public static Integer getCurrentUserId() {
        return getCurrentCustomUserDetails()
                .map(CustomUserDetails::getId)
                .orElse(-1);
    }

    private static Optional<CustomUserDetails> getCurrentCustomUserDetails() {
        return getContext()
                .map(auth -> {
                    final Object principal = auth.getPrincipal();

                    if (principal instanceof CustomUserDetails) {
                        return (CustomUserDetails) principal;
                    }
                    return null;
                });
    }

    private static Optional<Authentication> getContext(){
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }
}
