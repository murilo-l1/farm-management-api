package com.dev.farmmanager.domain.dto.stakeholder;

import com.dev.farmmanager.domain.enumeration.StakeholderType;

public record StakeholderDto(
        Integer id,
        String name,
        String cpf,
        String cnpj,
        StakeholderType type,
        String phone
) {}
