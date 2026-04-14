package com.dev.farmmanager.usecase.transaction;

import com.dev.farmmanager.domain.dto.transaction.TransactionDto;
import com.dev.farmmanager.domain.dto.transaction.TransactionPageDto;
import com.dev.farmmanager.domain.entity.Transaction;
import com.dev.farmmanager.exception.handler.TransactionNotFoundException;
import com.dev.farmmanager.mapper.TransactionMapper;
import com.dev.farmmanager.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("TransactionFetch")
@RequiredArgsConstructor
public class TransactionFetchImpl implements TransactionFetch {

    private final TransactionService service;
    private final TransactionMapper mapper;

    @Override
    public ResponseEntity<TransactionPageDto> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<TransactionDto> getById(Integer id) {
        Transaction transaction = service.getById(id).orElseThrow(TransactionNotFoundException::new);
        return ResponseEntity.ok(mapper.toDto(transaction));
    }
}
