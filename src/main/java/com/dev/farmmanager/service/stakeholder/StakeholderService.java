package com.dev.farmmanager.service.stakeholder;

import com.dev.farmmanager.domain.dto.stakeholder.StakeholderOptionDto;
import com.dev.farmmanager.domain.entity.Stakeholder;
import com.dev.farmmanager.domain.payload.stakeholder.StakeholderPayload;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface StakeholderService {

    List<Stakeholder> findAll();

    List<StakeholderOptionDto> findOptions();

    Optional<Stakeholder> getById(@NonNull Integer id);

    Stakeholder create(@NonNull Integer userId, @NonNull StakeholderPayload payload);

    Stakeholder update(@NonNull Integer id, @NonNull StakeholderPayload payload);

    void delete(@NonNull Integer id);
}
