package com.dev.farmmanager.domain.payload.transaction;

import com.dev.farmmanager.exception.handler.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record TransactionItemPayload(

        @NotNull(message = ErrorMessage.ITEM_ID_REQUIRED)
        Integer idItem,

        @PositiveOrZero(message = ErrorMessage.POSITIVE_OR_ZERO)
        @NotNull(message = ErrorMessage.QUANTITY_REQUIRED)
        Integer quantity,

        @PositiveOrZero(message = ErrorMessage.POSITIVE_OR_ZERO)
        @NotNull(message = ErrorMessage.REQUIRED_TOTAL_VALUE)
        BigDecimal unitPrice
) {}
