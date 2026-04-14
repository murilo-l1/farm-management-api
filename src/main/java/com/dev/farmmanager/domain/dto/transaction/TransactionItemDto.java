package com.dev.farmmanager.domain.dto.transaction;

import java.math.BigDecimal;

public record TransactionItemDto(
        Integer id,
        Integer itemId,
        String itemName,
        BigDecimal quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice
) {}
