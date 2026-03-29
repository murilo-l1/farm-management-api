package com.dev.farmmanager.service.auth;

import com.dev.farmmanager.domain.payload.auth.CreateUserPayload;
import com.dev.farmmanager.domain.payload.auth.EmailLoginPayload;
import com.dev.farmmanager.exception.handler.InvalidCredentialsException;
import com.dev.farmmanager.security.CustomUserDetails;
import com.dev.farmmanager.security.JwtHandler;
import lombok.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service("AuthService")
public class AuthServiceImpl implements AuthService {

    private final JwtHandler jwtHandler;
    private final AuthenticationManager authManager;

    public AuthServiceImpl(JwtHandler jwtHandler, AuthenticationManager authManager) {
        this.jwtHandler = jwtHandler;
        this.authManager = authManager;
    }

    @Override
    public String register(@NonNull final CreateUserPayload payload) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            payload.email(),
                            payload.password()
                    )
            );

            String username = ((CustomUserDetails) auth.getPrincipal()).getUsername();

            return jwtHandler.generateToken(username);
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public String login(@NonNull final EmailLoginPayload payload) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        payload.email(),
                        payload.password()
                    )
            );

            String username = ((CustomUserDetails) auth.getPrincipal()).getUsername();

            return jwtHandler.generateToken(username);
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException();
        }
    }
}
