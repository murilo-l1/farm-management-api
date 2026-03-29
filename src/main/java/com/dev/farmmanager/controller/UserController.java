package com.dev.farmmanager.controller;

import com.dev.farmmanager.controller.base.BaseController;
import com.dev.farmmanager.domain.dto.user.FetchUserDto;
import com.dev.farmmanager.domain.payload.user.DeactivateUserPayload;
import com.dev.farmmanager.domain.payload.user.UpdateUserPayload;
import com.dev.farmmanager.usecase.user.UserFetch;
import com.dev.farmmanager.usecase.user.UserUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("UserController")
@RequestMapping(value = "/api/farm/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class UserController extends BaseController {

    private final UserFetch fetch;
    private final UserUpdate update;

//    @GetMapping
//    public ResponseEntity<FetchUserDto> getById(@NonNull @PathVariable final Integer userId) {
//        return fetch.getById(userId);
//    }

    @GetMapping
    public ResponseEntity<FetchUserDto> getCurrentUser() {
        return fetch.getCurrentUser();
    }

    @PutMapping
    public ResponseEntity<Void> updateCurrentUser(@Valid @RequestBody @NotNull final UpdateUserPayload payload){
        return update.update(payload);
    }

}
