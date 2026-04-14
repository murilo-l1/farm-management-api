package com.dev.farmmanager.service.transaction;

import com.dev.farmmanager.domain.entity.Transaction;
import com.dev.farmmanager.domain.entity.TransactionItem;
import com.dev.farmmanager.domain.payload.transaction.TransactionItemPayload;

import java.util.List;

public interface TransactionItemService {

    List<TransactionItem> createItems(Transaction transaction, List<TransactionItemPayload> payloads);

    void replaceItems(Transaction transaction, List<TransactionItemPayload> payloads);

    List<TransactionItem> findByTransactionId(Integer transactionId);
}
