package com.dev.farmmanager.usecase.transaction;

import com.dev.farmmanager.domain.dto.transaction.TransactionDto;
import com.dev.farmmanager.domain.dto.transaction.TransactionPageDto;
import org.springframework.http.ResponseEntity;

public interface TransactionFetch {

    ResponseEntity<TransactionPageDto> findAll();

    ResponseEntity<TransactionDto> getById(Integer id);
}
