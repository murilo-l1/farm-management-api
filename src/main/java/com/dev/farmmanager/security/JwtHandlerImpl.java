package com.dev.farmmanager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service("JwtHandler")
public class JwtHandlerImpl implements JwtHandler {

    @Value("${farm.jwt.token.secret}")
    private String secret;

    @Value("${farm.jwt.token.expiration}")
    private Long expirationTime;

    private Algorithm algorithm;
    private JWTVerifier verifier;

    @PostConstruct
    private void init() {
        byte[] decodedKey = Base64.getDecoder().decode(this.secret);
        this.algorithm = Algorithm.HMAC256(decodedKey);
        this.verifier = JWT.require(this.algorithm).build();
    }

    @Override
    public String generateToken(@NonNull final String email) {
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(expirationTime))
                .sign(this.algorithm);
    }

    @Override
    public boolean validateToken(@NonNull final String email, @NonNull final String token) {
        try {
            DecodedJWT jwt = this.verifier.verify(token);
            return email.equals(jwt.getSubject());
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    @Override
    public String extractEmail(@NonNull final String token) {
        return JWT.decode(token).getSubject();
    }

    @Override
    public Date extractExpiration(@NonNull final String token) {
        return JWT.decode(token).getExpiresAt();
    }
}
