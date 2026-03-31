package com.dev.farmmanager.usecase.category;

import com.dev.farmmanager.domain.dto.category.CategoryDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryFetch {

    ResponseEntity<List<CategoryDto>> findAll();

    ResponseEntity<CategoryDto> getById(Integer id);
}
