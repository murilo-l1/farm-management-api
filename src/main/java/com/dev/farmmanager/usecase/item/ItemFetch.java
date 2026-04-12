package com.dev.farmmanager.usecase.item;

import com.dev.farmmanager.domain.dto.item.ItemDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ItemFetch {

    ResponseEntity<List<ItemDto>> findAll();

    ResponseEntity<ItemDto> getById(Integer id);
}
