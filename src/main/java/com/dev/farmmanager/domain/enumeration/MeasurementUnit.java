package com.dev.farmmanager.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MeasurementUnit {
    PES("pés"),
    HECTARE("ha"),
    METRO_QUADRADO("m2"),
    ALQUEIRE("alqueires");

    private final String label;

    MeasurementUnit(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static MeasurementUnit fromLabel(String label) {
        for (MeasurementUnit unit : values()) {
            if (unit.label.equalsIgnoreCase(label)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Unidade de medida inválida: " + label);
    }
}
