package com.dev.farmmanager.service.item;

import com.dev.farmmanager.domain.entity.Item;
import com.dev.farmmanager.domain.payload.item.ItemPayload;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> findAll();

    Optional<Item> getById(@NonNull Integer id);

    Item create(@NonNull Integer userId, @NonNull ItemPayload payload);

    Item update(@NonNull Integer id, @NonNull ItemPayload payload);

    void delete(@NonNull Integer id);
}
