package com.dev.farmmanager.usecase.user;

import com.dev.farmmanager.domain.dto.user.FetchUserDto;
import com.dev.farmmanager.domain.payload.user.FetchUserPayload;
import org.springframework.http.ResponseEntity;

public interface UserFetch {

    ResponseEntity<FetchUserDto> getById(final Integer userId);

}
