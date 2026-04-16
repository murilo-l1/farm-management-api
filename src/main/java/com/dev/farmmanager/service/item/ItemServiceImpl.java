package com.dev.farmmanager.service.item;

import com.dev.farmmanager.domain.entity.Category;
import com.dev.farmmanager.domain.entity.Item;
import com.dev.farmmanager.domain.entity.User;
import com.dev.farmmanager.domain.payload.item.ItemPayload;
import com.dev.farmmanager.exception.handler.CategoryNotFoundException;
import com.dev.farmmanager.exception.handler.ItemNotFoundException;
import com.dev.farmmanager.exception.handler.UserNotFoundException;
import com.dev.farmmanager.repository.ItemRepository;
import com.dev.farmmanager.security.SecurityUtils;
import com.dev.farmmanager.service.category.CategoryService;
import com.dev.farmmanager.service.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public List<Item> findAll() {
        return repository.findAllByUserId(SecurityUtils.getCurrentUserId());
    }

    @Override
    public Optional<Item> getById(@NonNull final Integer id) {
        return repository.findByIdAndUserId(id, SecurityUtils.getCurrentUserId());
    }

    @Override
    public Item create(@NonNull final Integer userId, @NonNull final ItemPayload payload) {
        User user = userService.getById(userId).orElseThrow(UserNotFoundException::new);

        Item item = new Item();
        item.setUser(user);
        item.setName(payload.name().trim());
        item.setUnity(payload.unity());
        item.setBrand(payload.brand());

        if (payload.categoryId() != null) {
            Category category = categoryService.getById(payload.categoryId())
                    .orElseThrow(CategoryNotFoundException::new);
            item.setCategory(category);
        }

        return repository.save(item);
    }

    @Override
    public Item update(@NonNull final Integer id, @NonNull final ItemPayload payload) {
        Item item = repository.findByIdAndUserId(id, SecurityUtils.getCurrentUserId())
                .orElseThrow(ItemNotFoundException::new);

        item.setName(payload.name().trim());
        item.setUnity(payload.unity());
        item.setBrand(payload.brand());

        if (payload.categoryId() != null) {
            Category category = categoryService.getById(payload.categoryId())
                    .orElseThrow(CategoryNotFoundException::new);
            item.setCategory(category);
        } else {
            item.setCategory(null);
        }

        return repository.save(item);
    }

    @Override
    public void delete(@NonNull final Integer id) {
        repository.deleteById(id);
    }
}
