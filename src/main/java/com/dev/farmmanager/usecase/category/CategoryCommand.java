package com.dev.farmmanager.usecase.category;

import com.dev.farmmanager.domain.dto.category.CategoryDto;
import com.dev.farmmanager.domain.payload.category.CategoryPayload;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

public interface CategoryCommand {

    ResponseEntity<CategoryDto> create(@NonNull final CategoryPayload payload);

    ResponseEntity<CategoryDto> update(@NonNull final Integer id, @NonNull final CategoryPayload payload);

    ResponseEntity<Void> delete(@NonNull final Integer id);

}
