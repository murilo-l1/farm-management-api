package com.dev.farmmanager.usecase.cropcycle;

import com.dev.farmmanager.domain.dto.cropcycle.CropCycleDto;
import com.dev.farmmanager.domain.dto.cropcycle.CropCyclePageDto;
import com.dev.farmmanager.domain.entity.CropCycle;
import com.dev.farmmanager.domain.enumeration.CropCycleStatus;
import com.dev.farmmanager.exception.handler.CropCycleNotFoundException;
import com.dev.farmmanager.mapper.CropCycleMapper;
import com.dev.farmmanager.service.cropcycle.CropCycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CropCycleFetchImpl implements CropCycleFetch {

    private final CropCycleService service;
    private final CropCycleMapper mapper;

    @Override
    public ResponseEntity<CropCyclePageDto> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<CropCycleDto> getById(Integer id) {
        CropCycle cropCycle = service.getById(id).orElseThrow(CropCycleNotFoundException::new);
        return ResponseEntity.ok(mapper.toDto(cropCycle));
    }

    @Override
    public ResponseEntity<List<CropCycleDto>> findByStatus(CropCycleStatus status) {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @Override
    public ResponseEntity<List<CropCycleDto>> findByDateBetween(LocalDate startDate, LocalDate endDate) {
        return ResponseEntity.ok(Collections.emptyList());
    }

}
