package com.dev.farmmanager.service.transaction;

import com.dev.farmmanager.domain.entity.Item;
import com.dev.farmmanager.domain.entity.Transaction;
import com.dev.farmmanager.domain.entity.TransactionItem;
import com.dev.farmmanager.domain.payload.transaction.TransactionItemPayload;
import com.dev.farmmanager.exception.handler.ItemNotFoundException;
import com.dev.farmmanager.repository.ItemRepository;
import com.dev.farmmanager.repository.TransactionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionItemServiceImpl implements TransactionItemService {

    private final TransactionItemRepository repository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<TransactionItem> createItems(Transaction transaction, List<TransactionItemPayload> payloads) {
        return repository.saveAll(buildItems(transaction, payloads));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void replaceItems(Transaction transaction, List<TransactionItemPayload> payloads) {
        repository.deleteAllByTransactionId(transaction.getId());
        if (payloads != null && !payloads.isEmpty()) {
            repository.saveAll(buildItems(transaction, payloads));
        }
    }

    @Override
    public List<TransactionItem> findByTransactionId(Integer transactionId) {
        return repository.findAllByTransactionId(transactionId);
    }

    private List<TransactionItem> buildItems(Transaction transaction, List<TransactionItemPayload> payloads) {
        return payloads.stream().map(payload -> {
            Item item = itemRepository.findById(payload.idItem())
                    .orElseThrow(ItemNotFoundException::new);

            BigDecimal quantity = BigDecimal.valueOf(payload.quantity());
            BigDecimal totalPrice = quantity.multiply(payload.unitPrice());

            TransactionItem transactionItem = new TransactionItem();
            transactionItem.setTransaction(transaction);
            transactionItem.setItem(item);
            transactionItem.setQuantity(quantity);
            transactionItem.setUnitPrice(payload.unitPrice());
            transactionItem.setTotalPrice(totalPrice);
            return transactionItem;
        }).toList();
    }
}
