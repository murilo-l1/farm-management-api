package com.dev.farmmanager.domain.dto.cropcycle;

import java.util.List;

public record CropCyclePageDto(
        CropCycleSummaryDto summary,
        List<CropCycleRowDto> cycles
) {}
