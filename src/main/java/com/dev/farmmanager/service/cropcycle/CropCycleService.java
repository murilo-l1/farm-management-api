package com.dev.farmmanager.service.cropcycle;

import com.dev.farmmanager.domain.dto.cropcycle.CropCyclePageDto;
import com.dev.farmmanager.domain.entity.CropCycle;
import com.dev.farmmanager.domain.enumeration.CropCycleStatus;
import com.dev.farmmanager.domain.payload.cropcycle.CropCyclePayload;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CropCycleService {

    CropCyclePageDto findAll(CropCycleStatus status, LocalDate date);

    Optional<CropCycle> getById(Integer id);

    CropCycle create(Integer userId, CropCyclePayload payload);

    CropCycle update(Integer id, CropCyclePayload payload);

    void delete(Integer id);
}
