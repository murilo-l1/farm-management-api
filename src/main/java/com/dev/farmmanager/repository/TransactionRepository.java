package com.dev.farmmanager.repository;

import com.dev.farmmanager.domain.dto.transaction.TransactionRowDto;
import com.dev.farmmanager.domain.entity.Transaction;
import com.dev.farmmanager.domain.enumeration.TransactionType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>, JpaSpecificationExecutor<Transaction> {

    @Query("""
            SELECT new com.dev.farmmanager.domain.dto.transaction.TransactionRowDto(
                t.id, t.transactionDate, t.description,
                cc.name, cat.name, s.name,
                t.type, t.status, t.paymentMethod, t.totalValue
            )
            FROM Transaction t
            LEFT JOIN t.cropCycle cc
            LEFT JOIN t.category cat
            LEFT JOIN t.stakeholder s
            WHERE t.userId = :userId
            ORDER BY t.transactionDate DESC, t.createdAt DESC
            """)
    List<TransactionRowDto> findAllRowsByUserId(@Param("userId") Integer userId);

    @EntityGraph(attributePaths = {"cropCycle", "category", "stakeholder", "items", "items.item"})
    Optional<Transaction> findByIdAndUserId(Integer id, Integer userId);

    @Query("SELECT COALESCE(SUM(t.totalValue), 0) FROM Transaction t WHERE t.userId = :userId AND t.type = :type")
    BigDecimal sumTotalValueByUserIdAndType(@Param("userId") Integer userId, @Param("type") TransactionType type);

    List<Transaction> findByCropCycleIdAndType(Integer cropCycleId, TransactionType type);

    @Query("SELECT COALESCE(SUM(t.totalValue), 0) FROM Transaction t WHERE t.cropCycleId = :cropCycleId AND t.type = :type")
    BigDecimal sumTotalValueByCropCycleIdAndType(@Param("cropCycleId") Integer cropCycleId, @Param("type") TransactionType type);
}
