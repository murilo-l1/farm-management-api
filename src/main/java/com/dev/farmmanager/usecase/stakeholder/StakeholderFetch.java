package com.dev.farmmanager.usecase.stakeholder;

import com.dev.farmmanager.domain.dto.stakeholder.StakeholderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StakeholderFetch {

    ResponseEntity<List<StakeholderDto>> findAll();

    ResponseEntity<StakeholderDto> getById(Integer id);
}
