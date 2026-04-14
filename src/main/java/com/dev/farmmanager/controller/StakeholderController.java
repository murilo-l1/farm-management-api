package com.dev.farmmanager.controller;

import com.dev.farmmanager.controller.base.BaseController;
import com.dev.farmmanager.domain.dto.stakeholder.StakeholderDto;
import com.dev.farmmanager.domain.payload.stakeholder.StakeholderPayload;
import com.dev.farmmanager.usecase.stakeholder.StakeholderCommand;
import com.dev.farmmanager.usecase.stakeholder.StakeholderFetch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/farm/stakeholder", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class StakeholderController extends BaseController {

    private final StakeholderFetch fetch;
    private final StakeholderCommand command;

    @PostMapping
    public ResponseEntity<StakeholderDto> create(@Valid @RequestBody StakeholderPayload payload) {
        return command.create(payload);
    }

    @GetMapping
    public ResponseEntity<List<StakeholderDto>> findAll() {
        return fetch.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StakeholderDto> getById(@PathVariable Integer id) {
        return fetch.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StakeholderDto> update(@PathVariable Integer id,
                                                 @Valid @RequestBody StakeholderPayload payload) {
        return command.update(id, payload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return command.delete(id);
    }
}
