package com.dev.farmmanager.domain.dto.transaction;

import com.dev.farmmanager.domain.enumeration.PaymentMethod;
import com.dev.farmmanager.domain.enumeration.TransactionStatus;
import com.dev.farmmanager.domain.enumeration.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record TransactionRowDto(
        Integer id,
        LocalDate transactionDate,
        String description,
        String cropCycleName,
        Integer categoryId,
        String categoryName,
        String stakeholderName,
        TransactionType type,
        TransactionStatus status,
        PaymentMethod paymentMethod,
        BigDecimal totalValue,
        List<TransactionItemDto> items
) {}
