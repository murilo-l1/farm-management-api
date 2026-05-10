package com.dev.farmmanager.usecase.dashboard;

import com.dev.farmmanager.domain.dto.dashboard.CategoryExpenseDto;
import com.dev.farmmanager.domain.dto.dashboard.CycleBudgetDto;
import com.dev.farmmanager.domain.projection.CategoryExpenseProjection;
import com.dev.farmmanager.domain.projection.CycleBudgetProjection;
import com.dev.farmmanager.repository.DashboardRepository;
import com.dev.farmmanager.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("DashboardFetch")
@RequiredArgsConstructor
public class DashboardFetchImpl implements DashboardFetch {

    private final DashboardRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<CategoryExpenseDto>> getExpensesByCategory() {
        Integer userId = SecurityUtils.getCurrentUserId();
        List<CategoryExpenseDto> result = repository.findExpensesByCategory(userId)
                .stream()
                .map(p -> new CategoryExpenseDto(
                        p.getCategoryName(),
                        p.getColor(),
                        p.getTotal(),
                        p.getPercentage()))
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<CycleBudgetDto>> getCycleBudgets() {
        Integer userId = SecurityUtils.getCurrentUserId();
        List<CycleBudgetDto> result = repository.findCycleBudgets(userId)
                .stream()
                .map(p -> new CycleBudgetDto(
                        p.getCropCycleId(),
                        p.getCropCycleName(),
                        p.getPlannedBudget(),
                        p.getCurrentInvestment(),
                        p.getInvestmentExpected(),
                        p.getTargetYield(),
                        p.getCurrentRevenue(),
                        p.getRevenueExpected()))
                .toList();
        return ResponseEntity.ok(result);
    }
}
