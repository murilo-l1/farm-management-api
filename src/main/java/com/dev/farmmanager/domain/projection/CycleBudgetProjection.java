package com.dev.farmmanager.domain.projection;

import java.math.BigDecimal;

public interface CycleBudgetProjection {
    Integer getCropCycleId();
    String getCropCycleName();
    BigDecimal getPlannedBudget();
    BigDecimal getCurrentInvestment();
    Boolean getInvestmentExpected();
    BigDecimal getTargetYield();
    BigDecimal getCurrentRevenue();
    Boolean getRevenueExpected();
}
