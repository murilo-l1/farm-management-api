package com.dev.farmmanager.domain.projection;

import java.math.BigDecimal;

public interface CategoryExpenseProjection {
    String getCategoryName();
    String getColor();
    BigDecimal getTotal();
    BigDecimal getPercentage();
}
