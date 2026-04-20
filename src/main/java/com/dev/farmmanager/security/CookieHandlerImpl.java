package com.dev.farmmanager.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CookieHandlerImpl implements CookieHandler {

    @Value("${farm.jwt.token.expiration}")
    private Long expiration;

    @Override
    public ResponseCookie jwtCookie(String jwt) {
        return generate("jwt", jwt, expiration);
    }

    private ResponseCookie generate(String name, String value, Long expiration){
        return ResponseCookie.from(name, value)
                .path("/")
                .maxAge(expiration)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .build();
    }

}
