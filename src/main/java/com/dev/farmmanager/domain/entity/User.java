package com.dev.farmmanager.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = User.TABLE_NAME)
public class User extends AbstractEntity {

   protected static final String TABLE_NAME = "farm_user";

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Size(min = 10, max = 20)
    @Column(name = "phone", nullable = false)
    private String phone;

    @Email
    @NotBlank
    @Size(max = 255)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = true;

}
