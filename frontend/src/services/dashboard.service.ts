import http from '@/api/http'
import type { CategoryExpenseDto, CycleBudgetDto } from '@/types/dashboard'

export const dashboardService = {
  getExpensesByCategory: () =>
    http.get<CategoryExpenseDto[]>('/farm/dashboard/expenses').then((r) => r.data),

  getCycleBudgets: () =>
    http.get<CycleBudgetDto[]>('/farm/dashboard/cycles').then((r) => r.data),
}
