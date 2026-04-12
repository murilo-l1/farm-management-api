package com.dev.farmmanager.usecase.stakeholder;

import com.dev.farmmanager.domain.dto.stakeholder.StakeholderDto;
import com.dev.farmmanager.domain.payload.stakeholder.StakeholderPayload;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

public interface StakeholderCommand {

    ResponseEntity<StakeholderDto> create(@NonNull StakeholderPayload payload);

    ResponseEntity<Void> update(@NonNull Integer id, @NonNull StakeholderPayload payload);

    ResponseEntity<Void> delete(@NonNull Integer id);
}
