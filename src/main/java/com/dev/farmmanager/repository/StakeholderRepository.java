package com.dev.farmmanager.repository;

import com.dev.farmmanager.domain.entity.Stakeholder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("StakeholderRepository")
public interface StakeholderRepository extends JpaRepository<Stakeholder, Integer>, JpaSpecificationExecutor<Stakeholder> {
}