package com.dev.farmmanager.mapper;

import com.dev.farmmanager.domain.dto.category.CategoryDto;
import com.dev.farmmanager.domain.entity.Category;
import com.dev.farmmanager.domain.payload.category.CategoryPayload;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

}
