package com.dev.farmmanager.domain.dto.dashboard;

import java.math.BigDecimal;

public record CycleBudgetDto(
        Integer cropCycleId,
        String cropCycleName,
        BigDecimal plannedBudget,
        BigDecimal currentInvestment,
        Boolean investmentExpected,
        BigDecimal targetYield,
        BigDecimal currentRevenue,
        Boolean revenueExpected
) {}
