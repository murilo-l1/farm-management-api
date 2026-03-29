package com.dev.farmmanager.service.user;

import com.dev.farmmanager.domain.entity.User;
import com.dev.farmmanager.domain.payload.user.UpdateUserPayload;
import com.dev.farmmanager.exception.handler.UserAlreadyExistsException;
import com.dev.farmmanager.exception.handler.UserNotFoundException;
import com.dev.farmmanager.repository.UserRepository;
import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> getById(@NonNull final Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<User> getByEmail(@NonNull final String email) {
        return repository.findUserByEmail(email);
    }

    @Override
    public User create(@NonNull User user) {
        if (this.getByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    @Override
    public User update(@NonNull Integer userId, @NonNull UpdateUserPayload payload) {
        User user = repository.findById(userId).orElseThrow(UserNotFoundException::new);

        if (!user.getEmail().equals(payload.email()) && this.getByEmail(payload.email()).isPresent()) {
            throw new UserAlreadyExistsException(); // valida que o 'e-mail' já é usado
        }

        user.setName(payload.name());
        user.setPhone(payload.phone());
        user.setEmail(payload.email());
        user.setActive(payload.active());

        return repository.save(user);
    }


}
