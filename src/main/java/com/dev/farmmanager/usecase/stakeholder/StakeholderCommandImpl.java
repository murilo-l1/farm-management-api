package com.dev.farmmanager.usecase.stakeholder;

import com.dev.farmmanager.domain.dto.stakeholder.StakeholderDto;
import com.dev.farmmanager.domain.entity.Stakeholder;
import com.dev.farmmanager.domain.payload.stakeholder.StakeholderPayload;
import com.dev.farmmanager.mapper.StakeholderMapper;
import com.dev.farmmanager.security.SecurityUtils;
import com.dev.farmmanager.service.stakeholder.StakeholderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("StakeholderCommand")
@RequiredArgsConstructor
public class StakeholderCommandImpl implements StakeholderCommand {

    private final StakeholderService service;
    private final StakeholderMapper mapper;

    @Override
    public ResponseEntity<StakeholderDto> create(@NonNull final StakeholderPayload payload) {
        Integer userId = SecurityUtils.getCurrentUserId();
        Stakeholder stakeholder = service.create(userId, payload);

        return ResponseEntity.ok().body(mapper.toDto(stakeholder));
    }

    @Override
    public ResponseEntity<Void> update(@NonNull final Integer id, @NonNull final StakeholderPayload payload) {
        service.update(id, payload);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(@NonNull final Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
