import http from '@/api/http'
import type { StakeholderDto, StakeholderOption, StakeholderPayload } from '@/types/stakeholder'

export const stakeholderService = {
  findAll: () =>
    http.get<StakeholderDto[]>('/farm/stakeholder').then((r) => r.data),

  getById: (id: number) =>
    http.get<StakeholderDto>(`/farm/stakeholder/${id}`).then((r) => r.data),

  create: (payload: StakeholderPayload) =>
    http.post<StakeholderDto>('/farm/stakeholder', payload).then((r) => r.data),

  update: (id: number, payload: StakeholderPayload) =>
    http.put<StakeholderDto>(`/farm/stakeholder/${id}`, payload).then((r) => r.data),

  delete: (id: number) =>
    http.delete(`/farm/stakeholder/${id}`),

  findOptions: () =>
    http.get<StakeholderOption[]>('/farm/stakeholder/options').then((r) => r.data),
}
