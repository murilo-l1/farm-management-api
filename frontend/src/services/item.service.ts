import http from '@/api/http'
import type { ItemDto, ItemPayload } from '@/types/item'

export const itemService = {
  findAll: () =>
    http.get<ItemDto[]>('/farm/item').then((r) => r.data),

  getById: (id: number) =>
    http.get<ItemDto>(`/farm/item/${id}`).then((r) => r.data),

  create: (payload: ItemPayload) =>
    http.post<ItemDto>('/farm/item', payload).then((r) => r.data),

  update: (id: number, payload: ItemPayload) =>
    http.put<ItemDto>(`/farm/item/${id}`, payload).then((r) => r.data),

  delete: (id: number) =>
    http.delete(`/farm/item/${id}`),
}
