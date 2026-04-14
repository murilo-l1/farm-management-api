package com.dev.farmmanager.domain.dto.transaction;

import com.dev.farmmanager.domain.enumeration.PaymentMethod;
import com.dev.farmmanager.domain.enumeration.TransactionStatus;
import com.dev.farmmanager.domain.enumeration.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public record TransactionDto(
        Integer id,
        Integer cropCycleId,
        String cropCycleName,
        Integer stakeholderId,
        String stakeholderName,
        Integer categoryId,
        String categoryName,
        TransactionType type,
        String description,
        BigDecimal totalValue,
        LocalDate transactionDate,
        TransactionStatus status,
        PaymentMethod paymentMethod,
        List<TransactionItemDto> items
) {}
