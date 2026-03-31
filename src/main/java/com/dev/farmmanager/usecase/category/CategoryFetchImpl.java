package com.dev.farmmanager.usecase.category;

import com.dev.farmmanager.domain.dto.category.CategoryDto;
import com.dev.farmmanager.domain.entity.Category;
import com.dev.farmmanager.exception.handler.CategoryNotFoundException;
import com.dev.farmmanager.mapper.CategoryMapper;
import com.dev.farmmanager.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CategoryFetch")
@RequiredArgsConstructor
public class CategoryFetchImpl implements CategoryFetch {

    private final CategoryService service;
    private final CategoryMapper mapper;


    @Override
    public ResponseEntity<List<CategoryDto>> findAll() {
        List<Category> categories = service.findAll();

        return ResponseEntity.ok(categories.stream().map(mapper::toDto).toList());
    }

    @Override
    public ResponseEntity<CategoryDto> getById(Integer id) {
        Category category = service.getById(id).orElseThrow(CategoryNotFoundException::new);

        return ResponseEntity.ok().body(mapper.toDto(category));
    }
}
