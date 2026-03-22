package com.dev.farmmanager.controller;

import com.dev.farmmanager.domain.dto.user.FetchUserDto;
import com.dev.farmmanager.domain.payload.user.FetchUserPayload;
import com.dev.farmmanager.usecase.user.UserFetch;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("UserController")
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserFetch fetch;

    @GetMapping("/{userId}")
    public ResponseEntity<FetchUserDto> getById(@NonNull @PathVariable final Integer userId) {
        return fetch.getById(userId);
    }

}
