import http from '@/api/http'
import type { CropCycleDto, CropCyclePage, CropCyclePayload, CropCycleStatus } from '@/types/crop-cycle'

export interface CropCycleFilters {
  status?: CropCycleStatus | null
  date?: string | null
}

export const cropCycleService = {
  findAll: (filters?: CropCycleFilters) => {
    const params: Record<string, string> = {}
    if (filters?.status) params.status = filters.status
    if (filters?.date)   params.date   = filters.date
    return http.get<CropCyclePage>('/farm/crop-cycle', { params }).then((r) => r.data)
  },

  create: (payload: CropCyclePayload) =>
    http.post<CropCycleDto>('/farm/crop-cycle', payload).then((r) => r.data),

  getById: (id: number) =>
    http.get<CropCycleDto>(`/farm/crop-cycle/${id}`).then((r) => r.data),

  update: (id: number, payload: CropCyclePayload) =>
    http.put<CropCycleDto>(`/farm/crop-cycle/${id}`, payload).then((r) => r.data),

  delete: (id: number) =>
    http.delete(`/farm/crop-cycle/${id}`),
}
