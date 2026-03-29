package com.dev.farmmanager.repository;

import com.dev.farmmanager.domain.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findUserByEmail(@NotBlank final String email);

    Optional<User> findUserByEmailAndActiveTrue(@NotBlank final String email);

}