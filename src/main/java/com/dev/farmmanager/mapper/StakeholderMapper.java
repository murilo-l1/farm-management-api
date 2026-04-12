package com.dev.farmmanager.mapper;

import com.dev.farmmanager.domain.dto.stakeholder.StakeholderDto;
import com.dev.farmmanager.domain.entity.Stakeholder;
import com.dev.farmmanager.domain.payload.stakeholder.StakeholderPayload;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StakeholderMapper {

    StakeholderDto toDto(Stakeholder stakeholder);

    Stakeholder toEntity(StakeholderPayload payload);
}
