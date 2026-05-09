export type StakeholderType = 'SUPPLIER' | 'BUYER' | 'BOTH'

export interface StakeholderDto {
  id: number
  name: string
  cpf: string | null
  cnpj: string | null
  type: StakeholderType | null
  phone: string | null
}

export interface StakeholderPayload {
  name: string
  cpf: string | null
  cnpj: string | null
  type: StakeholderType | null
  phone: string | null
}

export interface StakeholderOption {
  id: number
  name: string
}
