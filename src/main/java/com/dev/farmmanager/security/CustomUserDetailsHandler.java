package com.dev.farmmanager.security;

import com.dev.farmmanager.domain.entity.User;
import com.dev.farmmanager.exception.handler.UserNotFoundException;
import com.dev.farmmanager.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomUserDetailsHandler implements UserDetailsService {

    private final UserRepository repository;

    public CustomUserDetailsHandler(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByEmailAndActiveTrue(username).orElseThrow(UserNotFoundException::new);
        return new CustomUserDetails(user);
    }
}
