package com.dev.farmmanager.domain.dto.cropcycle;

import java.math.BigDecimal;

public record CropCycleSummaryDto(
        Long activeCyclesCount,
        BigDecimal totalPlantedArea,
        BigDecimal averageProgress,
        BigDecimal averageRoi
) {}
