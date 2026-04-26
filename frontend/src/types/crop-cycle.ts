export type CropCycleStatus =
  | 'PLANNING'
  | 'ACTIVE'
  | 'PLANTING'
  | 'HARVESTING'
  | 'FINISHED'
  | 'CANCELLED'

export type MeasurementUnit =
  | 'PES'
  | 'HECTARE'
  | 'METRO_QUADRADO'
  | 'ALQUEIRE'

export interface CropCycleRow {
  id: number
  name: string
  crop: string
  planted_area: number | null
  plant_count: number | null
  measurement_unit: MeasurementUnit | null
  status: CropCycleStatus
  planned_budget: number | null
  target_yield: number | null
  progress_percentage: number | null
  current_roi: number | null
}

export interface CropCycleDto {
  id: number
  name: string
  crop: string
  planted_area: number | null
  measurement_unit: MeasurementUnit | null
  plant_count: number | null
  planned_budget: number | null
  target_yield: number | null
  status: CropCycleStatus
  start_date: string
  end_date: string | null
}

export interface CropCyclePayload {
  name: string
  crop: string
  planted_area: number | null
  measurement_unit: MeasurementUnit | null
  plant_count: number | null
  planned_budget: number | null
  target_yield: number | null
  status: CropCycleStatus
  start_date: string
  end_date: string | null
}

export interface CropCycleSummary {
  active_cycles_count: number
  total_planted_area: number
  average_progress: number
  average_roi: number
}

export interface CropCyclePage {
  summary: CropCycleSummary
  cycles: CropCycleRow[]
}
