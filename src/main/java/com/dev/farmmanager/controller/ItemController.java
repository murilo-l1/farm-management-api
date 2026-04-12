package com.dev.farmmanager.controller;

import com.dev.farmmanager.controller.base.BaseController;
import com.dev.farmmanager.domain.dto.item.ItemDto;
import com.dev.farmmanager.domain.payload.item.ItemPayload;
import com.dev.farmmanager.usecase.item.ItemCommand;
import com.dev.farmmanager.usecase.item.ItemFetch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/farm/item", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ItemController extends BaseController {

    private final ItemFetch fetch;
    private final ItemCommand command;

    @PostMapping
    public ResponseEntity<ItemDto> create(@Valid @RequestBody ItemPayload payload) {
        return command.create(payload);
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> findAll() {
        return fetch.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getById(@PathVariable Integer id) {
        return fetch.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                       @Valid @RequestBody ItemPayload payload) {
        return command.update(id, payload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return command.delete(id);
    }
}
