export interface ItemDto {
  id: number
  name: string
  unity: string | null
  brand: string | null
  category_id: number | null
  category_name: string | null
}

export interface ItemPayload {
  name: string
  unity: string | null
  brand: string | null
  category_id: number | null
}
