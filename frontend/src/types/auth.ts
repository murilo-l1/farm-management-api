export interface LoginPayload {
  email: string
  password: string
}

export interface CreateUserPayload {
  name: string
  email: string
  password: string
  phone: string
}

export interface AuthUser {
  id: number
  name: string
  email: string
  phone: string
  active: boolean
}
