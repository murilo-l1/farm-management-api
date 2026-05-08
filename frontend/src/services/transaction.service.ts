import http from '@/api/http'
import type { TransactionDto, TransactionPage, TransactionPayload, TransactionStatus, TransactionType } from '@/types/transaction'

export interface TransactionFilters {
  crop_cycle_id?: number | null
  type?: TransactionType | null
  date?: string | null
  status?: TransactionStatus | null
}

export const transactionService = {
  findAll: (filters?: TransactionFilters) => {
    const params: Record<string, string> = {}
    if (filters?.crop_cycle_id != null) params.crop_cycle_id = String(filters.crop_cycle_id)
    if (filters?.type)   params.type   = filters.type
    if (filters?.date)   params.date   = filters.date
    if (filters?.status) params.status = filters.status
    return http.get<TransactionPage>('/farm/transaction', { params }).then((r) => r.data)
  },

  create: (payload: TransactionPayload) =>
    http.post('/farm/transaction', payload).then((r) => r.data),

  getById: (id: number) =>
    http.get<TransactionDto>(`/farm/transaction/${id}`).then((r) => r.data),

  update: (id: number, payload: TransactionPayload) =>
    http.put(`/farm/transaction/${id}`, payload).then((r) => r.data),

  delete: (id: number) =>
    http.delete(`/farm/transaction/${id}`),
}
