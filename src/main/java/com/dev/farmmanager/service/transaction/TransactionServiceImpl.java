package com.dev.farmmanager.service.transaction;

import com.dev.farmmanager.domain.dto.transaction.TransactionPageDto;
import com.dev.farmmanager.domain.dto.transaction.TransactionRowDto;
import com.dev.farmmanager.domain.dto.transaction.TransactionSummaryDto;
import com.dev.farmmanager.domain.entity.Category;
import com.dev.farmmanager.domain.entity.CropCycle;
import com.dev.farmmanager.domain.entity.Stakeholder;
import com.dev.farmmanager.domain.entity.Transaction;
import com.dev.farmmanager.domain.entity.User;
import com.dev.farmmanager.domain.enumeration.TransactionStatus;
import com.dev.farmmanager.domain.enumeration.TransactionType;
import com.dev.farmmanager.domain.payload.transaction.TransactionPayload;
import com.dev.farmmanager.exception.handler.*;
import com.dev.farmmanager.repository.TransactionRepository;
import com.dev.farmmanager.security.SecurityUtils;
import com.dev.farmmanager.service.category.CategoryService;
import com.dev.farmmanager.service.cropcycle.CropCycleService;
import com.dev.farmmanager.service.stakeholder.StakeholderService;
import com.dev.farmmanager.service.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final TransactionItemService transactionItemService;
    private final UserService userService;
    private final CropCycleService cropCycleService;
    private final CategoryService categoryService;
    private final StakeholderService stakeholderService;

    @Override
    public TransactionPageDto findAll() {
        Integer userId = SecurityUtils.getCurrentUserId();
        List<TransactionRowDto> rows = repository.findAllRowsByUserId(userId);

        BigDecimal totalIncome = repository.sumTotalValueByUserIdAndType(userId, TransactionType.INCOME);
        BigDecimal totalExpense = repository.sumTotalValueByUserIdAndType(userId, TransactionType.EXPENSE);
        BigDecimal totalCashFlow = totalIncome.subtract(totalExpense);

        TransactionSummaryDto summary = new TransactionSummaryDto(totalCashFlow, totalIncome, totalExpense);
        return new TransactionPageDto(summary, rows);
    }

    @Override
    public Optional<Transaction> getById(@NonNull final Integer id) {
        return repository.findByIdAndUserId(id, SecurityUtils.getCurrentUserId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Transaction create(@NonNull final Integer userId, @NonNull final TransactionPayload payload) {
        validateTotalValuePayload(payload);

        User user = userService.getById(userId).orElseThrow(UserNotFoundException::new);

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setType(payload.type());
        transaction.setDescription(payload.description());
        transaction.setTransactionDate(payload.transactionDate());
        transaction.setStatus(payload.status() != null ? payload.status() : TransactionStatus.PENDING);
        transaction.setPaymentMethod(payload.paymentMethod());
        transaction.setTotalValue(resolveTotalValue(payload));

        applyRelations(transaction, payload);

        Transaction saved = repository.save(transaction);

        if (hasItems(payload)) {
            transactionItemService.createItems(saved, payload.items());
        }

        return saved;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Transaction update(@NonNull final Integer id, @NonNull final TransactionPayload payload) {
        validateTotalValuePayload(payload);

        Transaction transaction = repository.findByIdAndUserId(id, SecurityUtils.getCurrentUserId())
                .orElseThrow(TransactionNotFoundException::new);

        transaction.setType(payload.type());
        transaction.setDescription(payload.description());
        transaction.setTransactionDate(payload.transactionDate());
        transaction.setStatus(payload.status() != null ? payload.status() : TransactionStatus.PENDING);
        transaction.setPaymentMethod(payload.paymentMethod());
        transaction.setTotalValue(resolveTotalValue(payload));

        applyRelations(transaction, payload);

        Transaction saved = repository.save(transaction);
        transactionItemService.replaceItems(saved, payload.items());
        return saved;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NonNull final Integer id) {
        Transaction transaction = repository.findByIdAndUserId(id, SecurityUtils.getCurrentUserId())
                .orElseThrow(TransactionNotFoundException::new);
        repository.delete(transaction);
    }

    private boolean hasItems(TransactionPayload payload) {
        return payload.items() != null && !payload.items().isEmpty();
    }

    private void validateTotalValuePayload(TransactionPayload payload) {
        if (hasItems(payload) && payload.totalValue() != null) {
            throw new TotalValueInvalidPairException();
        }
        if (!hasItems(payload) && payload.totalValue() == null) {
            throw new TotalValueRequiredException();
        }
    }

    private BigDecimal resolveTotalValue(TransactionPayload payload) {
        if (hasItems(payload)) {
            return payload.items().stream()
                    .map(i -> BigDecimal.valueOf(i.quantity()).multiply(i.unitPrice()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return payload.totalValue();
    }

    private void applyRelations(Transaction transaction, TransactionPayload payload) {
        if (payload.cropCycleId() != null) {
            CropCycle cropCycle = cropCycleService.getById(payload.cropCycleId())
                    .orElseThrow(CropCycleNotFoundException::new);
            transaction.setCropCycle(cropCycle);
        } else {
            transaction.setCropCycle(null);
        }

        if (payload.categoryId() != null) {
            Category category = categoryService.getById(payload.categoryId())
                    .orElseThrow(CategoryNotFoundException::new);
            transaction.setCategory(category);
        } else {
            transaction.setCategory(null);
        }

        if (payload.stakeholderId() != null) {
            Stakeholder stakeholder = stakeholderService.getById(payload.stakeholderId())
                    .orElseThrow(StakeholderNotFoundException::new);
            transaction.setStakeholder(stakeholder);
        } else {
            transaction.setStakeholder(null);
        }
    }
}
