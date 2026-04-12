package com.dev.farmmanager.usecase.stakeholder;

import com.dev.farmmanager.domain.dto.stakeholder.StakeholderDto;
import com.dev.farmmanager.domain.entity.Stakeholder;
import com.dev.farmmanager.exception.handler.StakeholderNotFoundException;
import com.dev.farmmanager.mapper.StakeholderMapper;
import com.dev.farmmanager.service.stakeholder.StakeholderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StakeholderFetch")
@RequiredArgsConstructor
public class StakeholderFetchImpl implements StakeholderFetch {

    private final StakeholderService service;
    private final StakeholderMapper mapper;

    @Override
    public ResponseEntity<List<StakeholderDto>> findAll() {
        List<Stakeholder> stakeholders = service.findAll();

        return ResponseEntity.ok(stakeholders.stream().map(mapper::toDto).toList());
    }

    @Override
    public ResponseEntity<StakeholderDto> getById(Integer id) {
        Stakeholder stakeholder = service.getById(id).orElseThrow(StakeholderNotFoundException::new);

        return ResponseEntity.ok().body(mapper.toDto(stakeholder));
    }
}
