package com.dev.farmmanager.usecase.dashboard;

import com.dev.farmmanager.domain.dto.dashboard.CategoryExpenseDto;
import com.dev.farmmanager.domain.dto.dashboard.CycleBudgetDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DashboardFetch {
    ResponseEntity<List<CategoryExpenseDto>> getExpensesByCategory();
    ResponseEntity<List<CycleBudgetDto>> getCycleBudgets();
}
