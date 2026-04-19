import http from '@/api/http'
import type { LoginPayload, JwtResponse, CreateUserPayload } from '@/types/auth'

export const authService = {
  login: (payload: LoginPayload) =>
    http.post<JwtResponse>('/auth/login', payload).then((r) => r.data),

  register: (payload: CreateUserPayload) =>
    http.post('/auth/register', payload),

  logout: () => http.post('/auth/logout'),
}
