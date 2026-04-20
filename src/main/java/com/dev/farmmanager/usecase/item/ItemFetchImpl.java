package com.dev.farmmanager.usecase.item;

import com.dev.farmmanager.domain.dto.item.ItemDto;
import com.dev.farmmanager.domain.entity.Item;
import com.dev.farmmanager.exception.handler.ItemNotFoundException;
import com.dev.farmmanager.mapper.ItemMapper;
import com.dev.farmmanager.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemFetchImpl implements ItemFetch {

    private final ItemService service;
    private final ItemMapper mapper;

    @Override
    public ResponseEntity<List<ItemDto>> findAll() {
        List<Item> items = service.findAll();

        return ResponseEntity.ok(items.stream().map(mapper::toDto).toList());
    }

    @Override
    public ResponseEntity<ItemDto> getById(Integer id) {
        Item item = service.getById(id).orElseThrow(ItemNotFoundException::new);

        return ResponseEntity.ok().body(mapper.toDto(item));
    }
}
