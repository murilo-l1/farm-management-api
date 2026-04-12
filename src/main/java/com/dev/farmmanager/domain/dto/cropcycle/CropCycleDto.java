package com.dev.farmmanager.domain.dto.cropcycle;

import com.dev.farmmanager.domain.enumeration.CropCycleStatus;
import com.dev.farmmanager.domain.enumeration.MeasurementUnit;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CropCycleDto(
        Long id,
        String name,
        String crop,
        BigDecimal plantedArea,
        MeasurementUnit measurementUnit,
        Integer plantCount,
        BigDecimal plannedBudget,
        BigDecimal targetYield,
        CropCycleStatus status,
        LocalDate startDate,
        LocalDate endDate
) {}
