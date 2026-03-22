package com.dev.farmmanager.service.user;

import com.dev.farmmanager.domain.entity.User;
import lombok.NonNull;

import java.util.Optional;

public interface UserService {

    Optional<User> getById(@NonNull final Integer id);

}
