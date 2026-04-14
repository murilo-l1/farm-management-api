package com.dev.farmmanager.usecase.transaction;

import com.dev.farmmanager.domain.dto.transaction.TransactionDto;
import com.dev.farmmanager.domain.entity.Transaction;
import com.dev.farmmanager.domain.entity.TransactionItem;
import com.dev.farmmanager.domain.payload.transaction.TransactionPayload;
import com.dev.farmmanager.mapper.TransactionMapper;
import com.dev.farmmanager.security.SecurityUtils;
import com.dev.farmmanager.service.transaction.TransactionItemService;
import com.dev.farmmanager.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TransactionCommand")
@RequiredArgsConstructor
public class TransactionCommandImpl implements TransactionCommand {

    private final TransactionService service;
    private final TransactionItemService transactionItemService;
    private final TransactionMapper mapper;

    @Override
    public ResponseEntity<TransactionDto> create(TransactionPayload payload) {
        Transaction transaction = service.create(SecurityUtils.getCurrentUserId(), payload);
        if (payload.items() != null && transaction.getItems().isEmpty()) {
            transaction.setItems(transactionItemService.findByTransactionId(transaction.getId()));
        }
        return ResponseEntity.ok(mapper.toDto(transaction));
    }

    @Override
    public ResponseEntity<TransactionDto> update(Integer id, TransactionPayload payload) {
        Transaction updated = service.update(id, payload);
        if (payload.items() != null && updated.getItems().isEmpty()) {
            updated.setItems(transactionItemService.findByTransactionId(updated.getId()));
        }
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
