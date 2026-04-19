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

export interface JwtResponse {
  expires_in: number
}
