package com.dev.farmmanager.service.user;

import com.dev.farmmanager.domain.entity.User;
import com.dev.farmmanager.repository.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> getById(@NonNull final Integer id) {
        return repository.findById(id);
    }

}
