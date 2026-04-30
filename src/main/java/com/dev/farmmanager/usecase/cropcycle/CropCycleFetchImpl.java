package com.dev.farmmanager.usecase.cropcycle;

import com.dev.farmmanager.domain.dto.cropcycle.CropCycleDto;
import com.dev.farmmanager.domain.dto.cropcycle.CropCycleOptionDto;
import com.dev.farmmanager.domain.dto.cropcycle.CropCyclePageDto;
import com.dev.farmmanager.domain.entity.CropCycle;
import com.dev.farmmanager.domain.enumeration.CropCycleStatus;
import com.dev.farmmanager.exception.handler.CropCycleNotFoundException;
import com.dev.farmmanager.mapper.CropCycleMapper;
import com.dev.farmmanager.service.cropcycle.CropCycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CropCycleFetchImpl implements CropCycleFetch {

    private final CropCycleService service;
    private final CropCycleMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CropCyclePageDto> findAll(CropCycleStatus status, LocalDate date) {
        return ResponseEntity.ok(service.findAll(status, date));
    }

    @Override
    public ResponseEntity<CropCycleDto> getById(Integer id) {
        CropCycle cropCycle = service.getById(id).orElseThrow(CropCycleNotFoundException::new);
        return ResponseEntity.ok(mapper.toDto(cropCycle));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<CropCycleOptionDto>> findOptions() {
        return ResponseEntity.ok(service.findOptions());
    }

}
