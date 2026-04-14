package com.dev.farmmanager.usecase.transaction;

import com.dev.farmmanager.domain.dto.transaction.TransactionDto;
import com.dev.farmmanager.domain.payload.transaction.TransactionPayload;
import org.springframework.http.ResponseEntity;

public interface TransactionCommand {

    ResponseEntity<TransactionDto> create(TransactionPayload payload);

    ResponseEntity<TransactionDto> update(Integer id, TransactionPayload payload);

    ResponseEntity<Void> delete(Integer id);
}
