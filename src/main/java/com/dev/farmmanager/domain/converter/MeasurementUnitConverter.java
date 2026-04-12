package com.dev.farmmanager.domain.converter;

import com.dev.farmmanager.domain.enumeration.MeasurementUnit;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MeasurementUnitConverter implements AttributeConverter<MeasurementUnit, String> {

    @Override
    public String convertToDatabaseColumn(MeasurementUnit attribute) {
        return attribute == null ? null : attribute.getLabel();
    }

    @Override
    public MeasurementUnit convertToEntityAttribute(String dbData) {
        return dbData == null ? null : MeasurementUnit.fromLabel(dbData);
    }
}
