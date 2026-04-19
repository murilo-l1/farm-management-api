import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { authService } from '@/services/auth.service'
import type { CreateUserPayload } from '@/types/auth'

export const useAuthStore = defineStore('auth', () => {

  const isAuthenticated = ref(false)
  const isLoggedIn = computed(() => isAuthenticated.value)

  async function login(email: string, password: string) {
    await authService.login({ email, password })
    isAuthenticated.value = true
  }

  async function register(payload: CreateUserPayload) {
    await authService.register(payload)
    isAuthenticated.value = true
  }

  async function logout() {
    await authService.logout()
    isAuthenticated.value = false
  }

  return { isAuthenticated, isLoggedIn, login, register, logout }
})
