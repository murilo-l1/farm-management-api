package com.dev.farmmanager.domain.dto.dashboard;

import java.math.BigDecimal;

public record CategoryExpenseDto(
        String categoryName,
        String color,
        BigDecimal total,
        BigDecimal percentage
) {}
