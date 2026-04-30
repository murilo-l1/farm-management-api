package com.dev.farmmanager.repository;

import com.dev.farmmanager.domain.dto.stakeholder.StakeholderOptionDto;
import com.dev.farmmanager.domain.entity.Stakeholder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StakeholderRepository extends JpaRepository<Stakeholder, Integer>, JpaSpecificationExecutor<Stakeholder> {

    List<Stakeholder> findAllByUserId(Integer userId);

    Optional<Stakeholder> findByIdAndUserId(Integer id, Integer userId);

    @Query("SELECT new com.dev.farmmanager.domain.dto.stakeholder.StakeholderOptionDto(s.id, s.name) FROM Stakeholder s WHERE s.userId = :userId ORDER BY s.name ASC")
    List<StakeholderOptionDto> findOptionsByUserId(@Param("userId") Integer userId);
}