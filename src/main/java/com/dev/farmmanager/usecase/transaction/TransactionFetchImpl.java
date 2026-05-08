package com.dev.farmmanager.usecase.transaction;

import com.dev.farmmanager.domain.dto.transaction.TransactionDto;
import com.dev.farmmanager.domain.dto.transaction.TransactionPageDto;
import com.dev.farmmanager.domain.entity.Transaction;
import com.dev.farmmanager.domain.enumeration.TransactionStatus;
import com.dev.farmmanager.domain.enumeration.TransactionType;
import com.dev.farmmanager.exception.handler.TransactionNotFoundException;
import com.dev.farmmanager.mapper.TransactionMapper;
import com.dev.farmmanager.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service("TransactionFetch")
@RequiredArgsConstructor
public class TransactionFetchImpl implements TransactionFetch {

    private final TransactionService service;
    private final TransactionMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<TransactionPageDto> findAll(Integer cropCycleId, TransactionType type, LocalDate date, TransactionStatus status) {
        return ResponseEntity.ok(service.findAll(cropCycleId, type, date, status));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<TransactionDto> getById(Integer id) {
        Transaction transaction = service.getById(id).orElseThrow(TransactionNotFoundException::new);
        return ResponseEntity.ok(mapper.toDto(transaction));
    }
}
