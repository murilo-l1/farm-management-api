package com.dev.farmmanager.controller;

import com.dev.farmmanager.controller.base.BaseController;
import com.dev.farmmanager.domain.dto.transaction.TransactionDto;
import com.dev.farmmanager.domain.dto.transaction.TransactionPageDto;
import com.dev.farmmanager.domain.payload.transaction.TransactionPayload;
import com.dev.farmmanager.usecase.transaction.TransactionCommand;
import com.dev.farmmanager.usecase.transaction.TransactionFetch;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/farm/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TransactionController extends BaseController {

    private final TransactionCommand command;
    private final TransactionFetch fetch;

    @GetMapping
    @Operation(summary = "Find all transactions with financial summary")
    public ResponseEntity<TransactionPageDto> findAll() {
        return fetch.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a transaction by ID")
    public ResponseEntity<TransactionDto> getById(@PathVariable Integer id) {
        return fetch.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new transaction")
    public ResponseEntity<TransactionDto> create(@Valid @RequestBody TransactionPayload payload) {
        return command.create(payload);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a transaction by ID")
    public ResponseEntity<Void> update(@PathVariable @NotNull Integer id, @Valid @RequestBody TransactionPayload payload) {
        return command.update(id, payload);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a transaction by ID")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return command.delete(id);
    }
}
