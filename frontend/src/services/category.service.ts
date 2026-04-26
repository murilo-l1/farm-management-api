import http from '@/api/http'
import type { CategoryDto } from '@/types/category'

export const categoryService = {
  findAll: () =>
    http.get<CategoryDto[]>('/farm/category').then((r) => r.data),
}
