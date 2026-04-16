package com.dev.farmmanager.domain.payload.transaction;

import com.dev.farmmanager.domain.enumeration.PaymentMethod;
import com.dev.farmmanager.domain.enumeration.TransactionStatus;
import com.dev.farmmanager.domain.enumeration.TransactionType;
import com.dev.farmmanager.exception.handler.message.ErrorMessage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record TransactionPayload(

        Integer cropCycleId,

        Integer stakeholderId,

        Integer categoryId,

        @NotNull(message = ErrorMessage.REQUIRED_TYPE)
        TransactionType type,

        String description,

        @PositiveOrZero(message = ErrorMessage.POSITIVE_OR_ZERO)
        BigDecimal totalValue,

        @NotNull(message = ErrorMessage.REQUIRED_TRANSACTION_DATE)
        LocalDate transactionDate,

        TransactionStatus status,

        PaymentMethod paymentMethod,

        @Valid
        List<TransactionItemPayload> items
) {}
