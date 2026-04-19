<template>
  <RouterView />
  <Toast />
</template>

<script setup lang="ts">
import Toast from 'primevue/toast'
import { useToast } from 'primevue/usetoast'
import { registerToast } from '@/services/toast'

registerToast(useToast())
</script>
