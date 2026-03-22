package com.dev.farmmanager.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Category.TABLE_NAME)
public class Category extends AbstractEntity {

    protected static final String TABLE_NAME = "category";

    @Column(name = "user_id", updatable = false, insertable = false)
    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "name", nullable = false)
    private String name;

}
