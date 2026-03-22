package com.dev.farmmanager.repository;

import com.dev.farmmanager.domain.entity.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("TransactionItemRepository")
public interface TransactionItemRepository extends JpaRepository<TransactionItem, Integer>, JpaSpecificationExecutor<TransactionItem> {
}