package com.dev.farmmanager.repository;

import com.dev.farmmanager.domain.entity.CropCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("CropCycleRepository")
public interface CropCycleRepository extends JpaRepository<CropCycle, Integer>, JpaSpecificationExecutor<CropCycle> {
}