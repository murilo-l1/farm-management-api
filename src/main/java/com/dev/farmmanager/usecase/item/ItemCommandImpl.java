package com.dev.farmmanager.usecase.item;

import com.dev.farmmanager.domain.dto.item.ItemDto;
import com.dev.farmmanager.domain.entity.Item;
import com.dev.farmmanager.domain.payload.item.ItemPayload;
import com.dev.farmmanager.mapper.ItemMapper;
import com.dev.farmmanager.security.SecurityUtils;
import com.dev.farmmanager.service.item.ItemService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("ItemCommand")
@RequiredArgsConstructor
public class ItemCommandImpl implements ItemCommand {

    private final ItemService service;
    private final ItemMapper mapper;

    @Override
    public ResponseEntity<ItemDto> create(@NonNull final ItemPayload payload) {
        Integer userId = SecurityUtils.getCurrentUserId();
        Item item = service.create(userId, payload);

        return ResponseEntity.ok().body(mapper.toDto(item));
    }

    @Override
    public ResponseEntity<Void> update(@NonNull final Integer id, @NonNull final ItemPayload payload) {
        service.update(id, payload);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(@NonNull final Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
