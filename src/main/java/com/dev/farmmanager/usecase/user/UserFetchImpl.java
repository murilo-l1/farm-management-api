package com.dev.farmmanager.usecase.user;

import com.dev.farmmanager.domain.dto.user.FetchUserDto;
import com.dev.farmmanager.domain.entity.User;
import com.dev.farmmanager.domain.payload.user.FetchUserPayload;
import com.dev.farmmanager.mapper.UserMapper;
import com.dev.farmmanager.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service("UserFetch")
@Validated
@RequiredArgsConstructor
public class UserFetchImpl implements UserFetch {

    private final UserService service;
    private final UserMapper mapper;

    @Override
    public ResponseEntity<FetchUserDto> getById(final Integer userId) {
        User user = service.getById(userId).orElseThrow( () -> new RuntimeException("foo")); // melhorar sistema de throw + baseController, que automatize isso

        FetchUserDto dto = mapper.toDto(user);

        return ResponseEntity.ok(dto);
    }
}
