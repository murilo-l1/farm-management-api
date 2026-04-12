package com.dev.farmmanager.mapper;

import com.dev.farmmanager.domain.dto.item.ItemDto;
import com.dev.farmmanager.domain.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ItemDto toDto(Item item);
}
