package com.dev.farmmanager.security;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseCookie;

public interface CookieHandler {

    ResponseCookie jwtCookie (@NotBlank final String jwt);

}
