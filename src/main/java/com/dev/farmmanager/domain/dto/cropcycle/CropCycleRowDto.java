package com.dev.farmmanager.domain.dto.cropcycle;

import com.dev.farmmanager.domain.enumeration.CropCycleStatus;
import com.dev.farmmanager.domain.enumeration.MeasurementUnit;

import java.math.BigDecimal;

public record CropCycleRowDto(
        Integer id,
        String name,
        String crop,
        BigDecimal plantedArea,
        Integer plantCount,
        MeasurementUnit measurementUnit,
        CropCycleStatus status,
        BigDecimal plannedBudget,
        BigDecimal targetYield,
        BigDecimal progressPercentage,
        BigDecimal currentRoi
) {}
