<template>
  <span class="app-input-host">
    <InputText v-bind="$attrs" />
  </span>
</template>

<script setup lang="ts">
import InputText from 'primevue/inputtext'
defineOptions({ inheritAttrs: false })
</script>

<style>
.app-input-host {
  display: contents;
  --p-inputtext-background:             #ffffff;
  --p-inputtext-color:                  #1a1c1c;
  --p-inputtext-placeholder-color:      #40493d;
  --p-inputtext-border-color:           #bfcaba;
  --p-inputtext-hover-border-color:     #707a6c;
  --p-inputtext-focus-border-color:     #0d631b;
}

.app-input-host input:-webkit-autofill,
.app-input-host input:-webkit-autofill:hover,
.app-input-host input:-webkit-autofill:focus {
  -webkit-box-shadow: 0 0 0 1000px #ffffff inset !important;
  -webkit-text-fill-color: #1a1c1c !important;
}
</style>
