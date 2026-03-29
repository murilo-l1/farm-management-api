package com.dev.farmmanager.usecase.auth;

import com.dev.farmmanager.domain.dto.auth.JwtDto;
import com.dev.farmmanager.domain.payload.auth.CreateUserPayload;
import com.dev.farmmanager.domain.payload.auth.EmailLoginPayload;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

public interface UserAuth {

    ResponseEntity<JwtDto> login(@NonNull @NotNull final EmailLoginPayload payload);

    ResponseEntity<Void> register(@NonNull @NotNull final CreateUserPayload payload);
}
