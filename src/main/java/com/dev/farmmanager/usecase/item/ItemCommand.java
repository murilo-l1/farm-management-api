package com.dev.farmmanager.usecase.item;

import com.dev.farmmanager.domain.dto.item.ItemDto;
import com.dev.farmmanager.domain.payload.item.ItemPayload;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

public interface ItemCommand {

    ResponseEntity<ItemDto> create(@NonNull ItemPayload payload);

    ResponseEntity<ItemDto> update(@NonNull Integer id, @NonNull ItemPayload payload);

    ResponseEntity<Void> delete(@NonNull Integer id);
}
