package com.dev.farmmanager.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Item.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
public class Item extends AbstractEntity {

    protected static final String TABLE_NAME = "item";

    @Column(name = "user_id", updatable = false, insertable = false)
    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "category_id", updatable = false, insertable = false)
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 50)
    @Column(name = "unity", length = 50)
    private String unity;

    @Size(max = 255)
    @Column(name = "brand")
    private String brand;

}
