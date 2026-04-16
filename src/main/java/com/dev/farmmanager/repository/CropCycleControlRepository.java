package com.dev.farmmanager.repository;

import com.dev.farmmanager.domain.entity.CropCycleControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CropCycleControlRepository extends JpaRepository<CropCycleControl, Integer>, JpaSpecificationExecutor<CropCycleControl> {
}