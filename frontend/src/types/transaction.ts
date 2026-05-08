export type TransactionType = 'INCOME' | 'EXPENSE'
export type TransactionStatus = 'PENDING' | 'FINISHED' | 'CANCELLED'
export type PaymentMethod = 'CARD' | 'CASH' | 'PIX' | 'CHECK' | 'BOLETO'

export interface TransactionItem {
  id: number
  item_id: number
  item_name: string
  quantity: number
  unit_price: number
  total_price: number
}

export interface TransactionRow {
  id: number
  transaction_date: string
  description: string | null
  crop_cycle_name: string | null
  category_id: number | null
  category_name: string | null
  stakeholder_name: string | null
  type: TransactionType
  status: TransactionStatus
  payment_method: PaymentMethod | null
  total_value: number
  items: TransactionItem[]
}

export interface TransactionSummary {
  total_cash_flow: number
  total_income: number
  total_expense: number
}

export interface TransactionPage {
  summary: TransactionSummary
  transactions: TransactionRow[]
}

export interface TransactionDto {
  id: number
  crop_cycle_id: number | null
  crop_cycle_name: string | null
  stakeholder_id: number | null
  stakeholder_name: string | null
  category_id: number | null
  category_name: string | null
  type: TransactionType
  description: string | null
  total_value: number
  transaction_date: string
  status: TransactionStatus
  payment_method: PaymentMethod | null
  items: TransactionItem[]
}

export interface TransactionPayload {
  type: TransactionType
  description: string | null
  transaction_date: string
  status: TransactionStatus | null
  payment_method: PaymentMethod | null
  total_value: number | null
  crop_cycle_id: number | null
  category_id: number | null
  stakeholder_id: number | null
  items: TransactionItemPayload[] | null
}

export interface TransactionItemPayload {
  id_item: number
  quantity: number
  unit_price: number
}
