<template>
  <Button v-bind="$attrs" class="app-btn" />
</template>

<script setup lang="ts">
import Button from 'primevue/button'
defineOptions({ inheritAttrs: false })
</script>

<style>
.app-btn {
  --p-button-primary-background:        #0d631b;
  --p-button-primary-hover-background:  #2e7d32;
  --p-button-primary-active-background: #2e7d32;
  --p-button-primary-border-color:        #0d631b;
  --p-button-primary-hover-border-color:  #2e7d32;
  --p-button-primary-active-border-color: #2e7d32;
  --p-button-primary-color: #ffffff;
}
</style>
