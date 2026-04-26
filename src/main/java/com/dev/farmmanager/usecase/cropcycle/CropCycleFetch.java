package com.dev.farmmanager.usecase.cropcycle;

import com.dev.farmmanager.domain.dto.cropcycle.CropCycleDto;
import com.dev.farmmanager.domain.dto.cropcycle.CropCyclePageDto;
import com.dev.farmmanager.domain.enumeration.CropCycleStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface CropCycleFetch {

    ResponseEntity<CropCyclePageDto> findAll(CropCycleStatus status, LocalDate date);

    ResponseEntity<CropCycleDto> getById(Integer id);
}
