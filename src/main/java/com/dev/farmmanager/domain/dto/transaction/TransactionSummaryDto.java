package com.dev.farmmanager.domain.dto.transaction;

import java.math.BigDecimal;

public record TransactionSummaryDto(
        BigDecimal totalCashFlow,
        BigDecimal totalIncome,
        BigDecimal totalExpense
) {}
