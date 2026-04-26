import http from '@/api/http'
import type { CategoryDto, CategoryPayload } from '@/types/category'

export const categoryService = {
  findAll: () =>
    http.get<CategoryDto[]>('/farm/category').then((r) => r.data),

  getById: (id: number) =>
    http.get<CategoryDto>(`/farm/category/${id}`).then((r) => r.data),

  create: (payload: CategoryPayload) =>
    http.post<CategoryDto>('/farm/category', payload).then((r) => r.data),

  update: (id: number, payload: CategoryPayload) =>
    http.put<CategoryDto>(`/farm/category/${id}`, payload).then((r) => r.data),

  delete: (id: number) =>
    http.delete(`/farm/category/${id}`),
}
