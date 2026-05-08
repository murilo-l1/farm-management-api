import http from '@/api/http'
import type { StakeholderOption } from '@/types/stakeholder'

export const stakeholderService = {
  findOptions: () =>
    http.get<StakeholderOption[]>('/farm/stakeholder/options').then((r) => r.data),
}
