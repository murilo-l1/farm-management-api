package com.dev.farmmanager.service.transaction;

import com.dev.farmmanager.domain.dto.transaction.TransactionPageDto;
import com.dev.farmmanager.domain.entity.Transaction;
import com.dev.farmmanager.domain.enumeration.TransactionStatus;
import com.dev.farmmanager.domain.enumeration.TransactionType;
import com.dev.farmmanager.domain.payload.transaction.TransactionPayload;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionService {

    TransactionPageDto findAll(Integer cropCycleId, TransactionType type, LocalDate date, TransactionStatus status);

    Optional<Transaction> getById(Integer id);

    List<Transaction> findExpensesByCropCycleId(Integer cropCycleId);

    List<Transaction> findIncomesByCropCycleId(Integer cropCycleId);

    Transaction create(Integer userId, TransactionPayload payload);

    Transaction update(Integer id, TransactionPayload payload);

    void delete(Integer id);
}
