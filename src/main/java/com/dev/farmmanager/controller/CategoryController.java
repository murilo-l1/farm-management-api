package com.dev.farmmanager.controller;

import com.dev.farmmanager.controller.base.BaseController;
import com.dev.farmmanager.domain.dto.category.CategoryDto;
import com.dev.farmmanager.domain.payload.category.CategoryPayload;
import com.dev.farmmanager.usecase.category.CategoryCommand;
import com.dev.farmmanager.usecase.category.CategoryFetch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/farm/category", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class CategoryController extends BaseController {

    private final CategoryFetch fetch;
    private final CategoryCommand command;

    @PostMapping
    @Operation(summary = "Create a new category")
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryPayload payload) {
        return command.create(payload);
    }

    @GetMapping
    @Operation(summary = "Get all categories for authenticated user")
    public ResponseEntity<List<CategoryDto>> findAll() {
        return fetch.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a category by ID")
    public ResponseEntity<CategoryDto> getById(@PathVariable Integer id) {
        return fetch.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category by ID")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CategoryPayload payload) {
        return command.update(id, payload);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category by ID")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
       return command.delete(id);
    }
}
