package com.dev.farmmanager.usecase.user;

import com.dev.farmmanager.domain.dto.user.FetchUserDto;
import com.dev.farmmanager.domain.payload.user.UpdateUserPayload;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

public interface UserFetch {

    ResponseEntity<FetchUserDto> getCurrentUser();

    ResponseEntity<FetchUserDto> getById(@NonNull final Integer userId);

}
