package com.dev.farmmanager.usecase.transaction;

import com.dev.farmmanager.domain.dto.transaction.TransactionDto;
import com.dev.farmmanager.domain.dto.transaction.TransactionPageDto;
import com.dev.farmmanager.domain.enumeration.TransactionStatus;
import com.dev.farmmanager.domain.enumeration.TransactionType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface TransactionFetch {

    ResponseEntity<TransactionPageDto> findAll(Integer cropCycleId, TransactionType type, LocalDate date, TransactionStatus status);

    ResponseEntity<TransactionDto> getById(Integer id);
}
