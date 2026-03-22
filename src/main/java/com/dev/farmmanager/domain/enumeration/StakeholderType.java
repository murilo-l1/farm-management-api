package com.dev.farmmanager.domain.enumeration;

import lombok.Getter;

import java.util.List;

@Getter
public enum StakeholderType {
    SUPPLIER("SUPPLIER"),
    BUYER("BUYER"),
    BOTH("BOTH");

    private final String value;

    private final static List<StakeholderType> values = List.of(StakeholderType.values());

    StakeholderType(String value) {
        this.value = value;
    }
}
