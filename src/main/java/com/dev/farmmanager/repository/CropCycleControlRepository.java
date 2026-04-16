package com.dev.farmmanager.repository;

import com.dev.farmmanager.domain.entity.CropCycleControl;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CropCycleControlRepository extends JpaRepository<CropCycleControl, Integer>, JpaSpecificationExecutor<CropCycleControl> {

    @EntityGraph(attributePaths = {"cropCycle"})
    Optional<CropCycleControl> findByCropCycleId(@NonNull final Integer cropCycleId);
}