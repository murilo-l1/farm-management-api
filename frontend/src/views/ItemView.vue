<template>
  <div class="item-view">
    <!-- Header -->
    <div class="page-header">
      <div class="page-header__title">
        <h1>Inventário</h1>
        <p class="page-header__sub">Gerencie os itens de insumos da sua propriedade</p>
      </div>
      <div class="page-header__actions">
        <AppButton
          icon="pi pi-external-link"
          label="Exportar CSV"
          severity="secondary"
          outlined
          @click="exportCSV"
        />
        <AppButton
          icon="pi pi-plus"
          label="Novo Item"
          @click="handleAdd"
        />
      </div>
    </div>

    <!-- Edit Drawer -->
    <Drawer
      v-model:visible="drawerOpen"
      position="right"
      :header="drawerHeader"
      style="width: 36rem"
    >
      <div v-if="drawerLoading && !editInitialData" class="drawer-loading">
        <Skeleton height="1.5rem" width="60%" class="mb-3" />
        <Skeleton height="1.5rem" width="60%" class="mb-3" />
        <Skeleton height="1.5rem" width="60%" class="mb-3" />
        <Skeleton height="1.5rem" width="40%" />
      </div>
      <ItemForm
        v-else
        :initial-data="editInitialData"
        :loading="drawerLoading"
        :mode="drawerMode"
        @submit="handleSave"
        @cancel="drawerOpen = false"
      />
    </Drawer>

    <!-- Table card -->
    <div class="table-card">
      <DataTable
        ref="dt"
        :value="loading ? skeletonRows : items"
        paginator
        :rows="10"
        :rows-per-page-options="[5, 10, 25, 50]"
        v-model:filters="filters"
        :global-filter-fields="['name', 'brand', 'category_name']"
        v-model:selection="selectedRow"
        selection-mode="single"
        :meta-key-selection="false"
        data-key="id"
        :row-class="rowClass"
        class="item-table"
      >
        <template #header>
          <div class="table-header">
            <IconField>
              <InputIcon>
                <i class="pi pi-search" />
              </InputIcon>
              <InputText
                v-model="filters['global'].value"
                placeholder="Buscar por nome, marca, ..."
              />
            </IconField>
          </div>
        </template>

        <!-- Nome -->
        <Column field="name" header="Nome" style="min-width: 14rem">
          <template #body="{ data }">
            <Skeleton v-if="loading" height="1rem" width="10rem" />
            <span v-else class="cell-name">{{ data.name }}</span>
          </template>
        </Column>

        <!-- Unidade -->
        <Column field="unity" header="Unidade" style="min-width: 7rem">
          <template #body="{ data }">
            <Skeleton v-if="loading" height="1rem" width="4rem" />
            <span v-else>{{ data.unity ?? '—' }}</span>
          </template>
        </Column>

        <!-- Marca -->
        <Column field="brand" header="Marca" style="min-width: 10rem">
          <template #body="{ data }">
            <Skeleton v-if="loading" height="1rem" width="7rem" />
            <span v-else>{{ data.brand ?? '—' }}</span>
          </template>
        </Column>

        <!-- Categoria -->
        <Column field="category_name" header="Categoria" style="min-width: 10rem">
          <template #body="{ data }">
            <Skeleton v-if="loading" height="1.5rem" width="7rem" border-radius="2rem" />
            <span v-else-if="data.category_name" class="category-badge">
              {{ data.category_name }}
            </span>
            <span v-else class="cell-empty">—</span>
          </template>
        </Column>

        <!-- Ações -->
        <Column header="" style="width: 6rem; text-align: right">
          <template #body="{ data }">
            <div v-if="!loading" class="action-cell">
              <button class="action-btn action-btn--edit" title="Editar" @click.stop="handleEdit(data)">
                <span class="material-symbols-outlined">edit</span>
              </button>
              <button class="action-btn action-btn--delete" title="Excluir" @click.stop="handleDelete(data.id)">
                <span class="material-symbols-outlined">delete</span>
              </button>
            </div>
          </template>
        </Column>

        <template #empty>
          <div class="empty-state">
            <span class="material-symbols-outlined empty-icon">inventory_2</span>
            <p>Nenhum item encontrado.</p>
          </div>
        </template>
      </DataTable>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import DataTable from 'primevue/datatable'
import Column from 'primevue/column'
import Skeleton from 'primevue/skeleton'
import IconField from 'primevue/iconfield'
import InputIcon from 'primevue/inputicon'
import InputText from 'primevue/inputtext'
import Drawer from 'primevue/drawer'
import { FilterMatchMode } from '@primevue/core/api'
import AppButton from '@/components/AppButton.vue'
import ItemForm from '@/form/ItemForm.vue'
import { itemService } from '@/services/item.service'
import { toast } from '@/services/toast'
import type { ItemDto, ItemPayload } from '@/types/item'

const dt = ref()
const loading = ref(false)
const items = ref<ItemDto[]>([])
const selectedRow = ref<ItemDto | null>(null)
const skeletonRows = Array(7).fill({})
const filters = ref({ global: { value: null as string | null, matchMode: FilterMatchMode.CONTAINS } })

const drawerOpen = ref(false)
const drawerLoading = ref(false)
const drawerMode = ref<'create' | 'edit'>('edit')
const editingId = ref<number | null>(null)
const editInitialData = ref<ItemDto | null>(null)

const drawerHeader = computed(() =>
  drawerMode.value === 'create'
    ? 'Novo Item'
    : (editInitialData.value?.name ?? 'Editando Item')
)

function rowClass(row: ItemDto) {
  return selectedRow.value?.id === row.id ? 'row--selected' : ''
}

async function loadData() {
  loading.value = true
  items.value = await itemService.findAll().finally(() => (loading.value = false))
}

onMounted(loadData)

function exportCSV() {
  dt.value.exportCSV()
}

function handleAdd() {
  drawerMode.value = 'create'
  editingId.value = null
  editInitialData.value = null
  drawerOpen.value = true
}

async function handleEdit(row: ItemDto) {
  drawerMode.value = 'edit'
  editingId.value = row.id
  editInitialData.value = null
  drawerOpen.value = true
  drawerLoading.value = true
  editInitialData.value = await itemService.getById(row.id).finally(() => (drawerLoading.value = false))
}

async function handleSave(payload: ItemPayload) {
  drawerLoading.value = true
  if (drawerMode.value === 'create') {
    await itemService.create(payload).finally(() => (drawerLoading.value = false))
    toast.success('Item criado.')
  } else {
    await itemService.update(editingId.value!, payload).finally(() => (drawerLoading.value = false))
    toast.success('Item atualizado.')
  }
  drawerOpen.value = false
  await loadData()
}

async function handleDelete(id: number) {
  await itemService.delete(id)
  items.value = items.value.filter((i) => i.id !== id)
  if (selectedRow.value?.id === id) selectedRow.value = null
  toast.success('Item excluído.')
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Manrope:wght@400;600;700;800&family=Inter:wght@400;500;600&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&display=swap');

.item-table.p-datatable .p-datatable-thead > tr > th {
  background: #f5f7f5;
  color: #40493d;
  font-family: 'Inter', sans-serif;
  font-size: 0.75rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.06em;
  border-bottom: 1px solid #dde5d8;
  padding: 0.75rem 1rem;
}

.item-table.p-datatable .p-datatable-tbody > tr > td {
  font-family: 'Inter', sans-serif;
  font-size: 0.875rem;
  color: #1a1c1c;
  border-bottom: 1px solid #eef1eb;
  padding: 0.875rem 1rem;
  vertical-align: middle;
}

.item-table.p-datatable .p-datatable-tbody > tr:hover > td {
  background: #f5f7f5;
}

.item-table.p-datatable .p-datatable-tbody > tr.row--selected > td {
  background: #e8f5e9;
}

.item-table.p-datatable .p-datatable-tbody > tr.p-highlight > td {
  background: #e8f5e9 !important;
  color: #1a1c1c !important;
}
</style>

<style scoped>
.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
  font-family: 'Material Symbols Outlined';
}

.item-view {
  --primary:            #0d631b;
  --primary-container:  #2e7d32;
  --surface:            #f9f9f9;
  --on-surface:         #1a1c1c;
  --on-surface-variant: #40493d;
  --outline-variant:    #bfcaba;
}

.item-view {
  display: flex;
  flex-direction: column;
  height: 100%;
  padding: 2rem;
  gap: 1.5rem;
  background: var(--surface);
  font-family: 'Inter', sans-serif;
  overflow: hidden;
}

.page-header {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 1rem;
  flex-shrink: 0;
}

.page-header__title h1 {
  font-family: 'Manrope', sans-serif;
  font-size: 1.5rem;
  font-weight: 800;
  color: var(--on-surface);
  margin: 0;
  line-height: 1.2;
}

.page-header__sub {
  font-size: 0.8125rem;
  color: var(--on-surface-variant);
  margin: 0.25rem 0 0;
}

.page-header__actions {
  display: flex;
  gap: 0.625rem;
  align-items: center;
  flex-shrink: 0;
}

.table-card {
  flex: 1;
  background: #fff;
  border: 1px solid var(--outline-variant);
  border-radius: 1rem;
  overflow: auto;
  display: flex;
  flex-direction: column;
  min-height: 0;
}

:deep(.item-table) {
  height: 100%;
}

:deep(.item-table .p-datatable-wrapper) {
  flex: 1;
}

.table-header {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 0.625rem;
  padding: 0.75rem 1rem;
}

.category-badge {
  display: inline-block;
  padding: 0.25rem 0.75rem;
  border-radius: 2rem;
  font-size: 0.75rem;
  font-weight: 600;
  background: #e8f5e9;
  color: #2e7d32;
  white-space: nowrap;
}

.cell-name {
  font-weight: 600;
  color: var(--on-surface);
}

.cell-empty {
  color: var(--on-surface-variant);
}

.action-cell {
  display: flex;
  gap: 0.375rem;
  justify-content: flex-end;
}

.action-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 2rem;
  height: 2rem;
  border-radius: 0.5rem;
  border: none;
  background: transparent;
  cursor: pointer;
  transition: background 0.15s, color 0.15s;
  color: var(--on-surface-variant);
}

.action-btn .material-symbols-outlined {
  font-size: 1.1rem;
}

.action-btn--edit:hover {
  background: #e3f2fd;
  color: #1565c0;
}

.action-btn--delete:hover {
  background: #ffebee;
  color: #c62828;
}

.drawer-loading {
  padding: 1.5rem;
  display: flex;
  flex-direction: column;
}

.mb-3 {
  margin-bottom: 1rem;
}

.empty-state {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 3rem 1rem;
  gap: 0.75rem;
  color: var(--on-surface-variant);
}

.empty-icon {
  font-size: 2.5rem;
  font-variation-settings: 'FILL' 0, 'wght' 300, 'GRAD' 0, 'opsz' 48;
  opacity: 0.5;
}
</style>
