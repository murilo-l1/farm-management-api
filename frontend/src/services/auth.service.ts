import http from '@/api/http'
import type { LoginPayload, CreateUserPayload, AuthUser } from '@/types/auth'

export const authService = {
  login: (payload: LoginPayload) =>
    http.post('/auth/login', payload),

  register: (payload: CreateUserPayload) =>
    http.post('/auth/register', payload),

  logout: () =>
    http.post('/auth/logout'),

  me: () =>
    http.get<AuthUser>('/farm/user/me').then((r) => r.data),
}
