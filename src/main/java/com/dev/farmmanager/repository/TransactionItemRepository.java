package com.dev.farmmanager.repository;

import com.dev.farmmanager.domain.entity.TransactionItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TransactionItemRepository")
public interface TransactionItemRepository extends JpaRepository<TransactionItem, Integer>, JpaSpecificationExecutor<TransactionItem> {

    @EntityGraph(attributePaths = {"item"})
    List<TransactionItem> findAllByTransactionId(Integer transactionId);

    @Modifying
    @Query("DELETE FROM TransactionItem ti WHERE ti.transactionId = :transactionId")
    void deleteAllByTransactionId(@Param("transactionId") Integer transactionId);

}