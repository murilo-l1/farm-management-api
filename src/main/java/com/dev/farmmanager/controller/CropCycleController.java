package com.dev.farmmanager.controller;

import com.dev.farmmanager.controller.base.BaseController;
import com.dev.farmmanager.domain.dto.cropcycle.CropCycleDto;
import com.dev.farmmanager.domain.dto.cropcycle.CropCyclePageDto;
import com.dev.farmmanager.domain.payload.cropcycle.CropCyclePayload;
import com.dev.farmmanager.usecase.cropcycle.CropCycleCommand;
import com.dev.farmmanager.usecase.cropcycle.CropCycleFetch;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/farm/crop-cycle", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CropCycleController extends BaseController {

    private final CropCycleCommand command;
    private final CropCycleFetch fetch;

    @GetMapping
    @Operation(summary = "Find all crop cycles with summary stats")
    public ResponseEntity<CropCyclePageDto> findAll() {
        return fetch.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a crop cycle by ID")
    public ResponseEntity<CropCycleDto> getById(@PathVariable Integer id) {
        return fetch.getById(id);
    }

    @PostMapping
    @Operation(summary = "Creates a new crop cycle")
    public ResponseEntity<CropCycleDto> create(@Valid @RequestBody CropCyclePayload payload) {
        return command.create(payload);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a crop cycle by ID")
    public ResponseEntity<Void> update(@PathVariable @NotNull Integer id, @Valid @RequestBody CropCyclePayload payload) {
        return command.update(id, payload);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a crop cycle by ID")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return command.delete(id);
    }

    // TODO
    // Isso aqui vai dar um pouco mais de trabalho e vai ser melhor ser tudo do findAll e dai usa Specification.
    // Pensar se será usado no FE depois...
//    @GetMapping
//    @Operation(summary = "Find crop cycles by status")
//    public ResponseEntity<List<CropCycleDto>> findByStatus(@Valid @RequestParam CropCycleStatus status) {
//        return fetch.findByStatus(status);
//    }
//
//    @GetMapping
//    @Operation(summary = "Get crop cycles by date range for authenticated user")
//    public ResponseEntity<List<CropCycleDto>> findByDateBetween(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//        return fetch.findByDateBetween(startDate, endDate);
//    }


}
