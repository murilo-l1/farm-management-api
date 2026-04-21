package com.dev.farmmanager.controller;

import com.dev.farmmanager.controller.base.BaseController;
import com.dev.farmmanager.domain.dto.auth.JwtDto;
import com.dev.farmmanager.domain.payload.auth.CreateUserPayload;
import com.dev.farmmanager.domain.payload.auth.EmailLoginPayload;
import com.dev.farmmanager.usecase.auth.UserAuth;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("AuthController")
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AuthController extends BaseController {

    private final UserAuth auth;

    public AuthController(UserAuth auth) {
        this.auth = auth;
    }

    @Operation(summary = "Login user by email and password")
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody @NotNull final EmailLoginPayload payload) {
        return auth.login(payload);
    }

    @Operation(summary = "Register user using required credentials")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody @NotNull final CreateUserPayload payload) {
        return auth.register(payload);
    }

    @Operation(summary = "Resets the cookie with jwt")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return auth.logout();
    }

}
