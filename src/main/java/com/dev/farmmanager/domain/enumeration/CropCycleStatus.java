package com.dev.farmmanager.domain.enumeration;

import lombok.Getter;

import java.util.List;

@Getter
public enum CropCycleStatus {
    PLANNING("PLANNING"),
    ACTIVE("ACTIVE"),
    PLANTING("PLANTING"),
    HARVESTING("HARVESTING"),
    FINISHED("FINISHED"),
    CANCELLED("CANCELLED");

    private final String value;

    private final static List<CropCycleStatus> values = List.of(CropCycleStatus.values());

    CropCycleStatus(String value) {
        this.value = value;
    }
}
