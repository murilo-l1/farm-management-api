package com.dev.farmmanager.usecase.cropcycle;

import com.dev.farmmanager.domain.dto.cropcycle.CropCycleDto;
import com.dev.farmmanager.domain.entity.CropCycle;
import com.dev.farmmanager.domain.payload.cropcycle.CropCyclePayload;
import com.dev.farmmanager.mapper.CropCycleMapper;
import com.dev.farmmanager.security.SecurityUtils;
import com.dev.farmmanager.service.cropcycle.CropCycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("CropCycleCommand")
@RequiredArgsConstructor
public class CropCycleCommandImpl implements CropCycleCommand {

    private final CropCycleService service;
    private final CropCycleMapper mapper;

    @Override
    public ResponseEntity<CropCycleDto> create(CropCyclePayload payload) {
        CropCycle cropCycle = service.create(SecurityUtils.getCurrentUserId(), payload);
        return ResponseEntity.ok(mapper.toDto(cropCycle));
    }

    @Override
    public ResponseEntity<Void> update(Integer id, CropCyclePayload payload) {
        service.update(id, payload);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
