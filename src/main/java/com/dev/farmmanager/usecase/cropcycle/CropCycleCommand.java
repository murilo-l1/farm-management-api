package com.dev.farmmanager.usecase.cropcycle;

import com.dev.farmmanager.domain.dto.cropcycle.CropCycleDto;
import com.dev.farmmanager.domain.payload.cropcycle.CropCyclePayload;
import org.springframework.http.ResponseEntity;

public interface CropCycleCommand {

    ResponseEntity<CropCycleDto> create(CropCyclePayload payload);

    ResponseEntity<CropCycleDto> update(Integer id, CropCyclePayload payload);

    ResponseEntity<Void> delete(Integer id);
}
