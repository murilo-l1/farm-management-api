package com.dev.farmmanager.domain.payload.cropcycle;

import com.dev.farmmanager.domain.enumeration.CropCycleStatus;
import com.dev.farmmanager.domain.enumeration.MeasurementUnit;
import com.dev.farmmanager.exception.handler.message.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CropCyclePayload(
        @NotBlank(message = ErrorMessage.REQUIRED_NAME)
        @Size(max = 255, message = ErrorMessage.INVALID_NAME_LENGTH)
        String name,

        @NotBlank(message = ErrorMessage.REQUIRED_CROP)
        @Size(max = 50, message = ErrorMessage.INVALID_CROP_LENGTH)
        String crop,

        @PositiveOrZero(message = ErrorMessage.POSITIVE_OR_ZERO)
        BigDecimal plantedArea,

        MeasurementUnit measurementUnit,

        @PositiveOrZero(message = ErrorMessage.POSITIVE_OR_ZERO)
        Integer plantCount,

        BigDecimal plannedBudget,

        BigDecimal targetYield,

        @NotNull(message = ErrorMessage.REQUIRED_STATUS)
        CropCycleStatus status,

        @NotNull(message = ErrorMessage.REQUIRED_START_DATE)
        LocalDate startDate,

        LocalDate endDate
) {}
