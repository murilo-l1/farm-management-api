package com.dev.farmmanager.domain.dto.item;

public record ItemDto(
        Integer id,
        String name,
        String unity,
        String brand,
        Integer categoryId,
        String categoryName
) {}
