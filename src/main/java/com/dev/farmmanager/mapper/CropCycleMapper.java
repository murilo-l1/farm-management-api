package com.dev.farmmanager.mapper;

import com.dev.farmmanager.domain.dto.cropcycle.CropCycleDto;
import com.dev.farmmanager.domain.entity.CropCycle;
import com.dev.farmmanager.domain.payload.cropcycle.CropCyclePayload;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CropCycleMapper {

    CropCycleDto toDto(CropCycle cropCycle);

    CropCycle toEntity(CropCyclePayload payload);
}
