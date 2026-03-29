package com.dev.farmmanager.usecase.user;

import com.dev.farmmanager.domain.payload.user.UpdateUserPayload;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

public interface UserUpdate {

    ResponseEntity<Void> update(@NonNull final UpdateUserPayload payload);

}
