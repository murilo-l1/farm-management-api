package com.dev.farmmanager.repository;

import com.dev.farmmanager.domain.dto.cropcycle.CropCycleRowDto;
import com.dev.farmmanager.domain.entity.CropCycle;
import com.dev.farmmanager.domain.enumeration.CropCycleStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CropCycleRepository extends JpaRepository<CropCycle, Integer> {

    @Query("""
            SELECT new com.dev.farmmanager.domain.dto.cropcycle.CropCycleRowDto(
                c.id, c.name, c.crop, c.plantedArea, c.plantCount, c.measurementUnit,
                c.status, c.plannedBudget, c.targetYield,
                ctrl.progressPercentage, ctrl.currentRoi
            )
            FROM CropCycle c LEFT JOIN c.control ctrl
            WHERE c.userId = :userId
            ORDER BY c.createdAt DESC
            """)
    List<CropCycleRowDto> findAllRowsByUserId(@Param("userId") Integer userId);

    Optional<CropCycle> findByIdAndUserId(Integer id, Integer userId);

    @EntityGraph(attributePaths = "user")
    List<CropCycle> findAllByUserIdAndStatus(Integer userId, CropCycleStatus status);

    @EntityGraph(attributePaths = "user")
    List<CropCycle> findAllByUserIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(Integer userId, LocalDate startDate, LocalDate endDate);
}
