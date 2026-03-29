package com.dev.farmmanager.service.auth;

import com.dev.farmmanager.domain.payload.auth.CreateUserPayload;
import com.dev.farmmanager.domain.payload.auth.EmailLoginPayload;
import lombok.NonNull;

public interface AuthService {

    String register(@NonNull final CreateUserPayload payload);

    String login(@NonNull final EmailLoginPayload payload);

}
