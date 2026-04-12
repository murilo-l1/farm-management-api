package com.dev.farmmanager.usecase.cropcycle;

import com.dev.farmmanager.domain.dto.cropcycle.CropCycleDto;
import com.dev.farmmanager.domain.dto.cropcycle.CropCyclePageDto;
import com.dev.farmmanager.domain.enumeration.CropCycleStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface CropCycleFetch {

    ResponseEntity<CropCyclePageDto> findAll();

    ResponseEntity<List<CropCycleDto>> findByStatus(CropCycleStatus status);

    ResponseEntity<List<CropCycleDto>> findByDateBetween(LocalDate startDate, LocalDate endDate);

    ResponseEntity<CropCycleDto> getById(Integer id);
}
