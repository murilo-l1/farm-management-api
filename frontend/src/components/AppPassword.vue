<template>
  <span class="app-password-host">
    <Password v-bind="$attrs" />
  </span>
</template>

<script setup lang="ts">
import Password from 'primevue/password'
defineOptions({ inheritAttrs: false })
</script>

<style>
.app-password-host {
  display: contents;
  --p-inputtext-background:             #ffffff;
  --p-inputtext-color:                  #1a1c1c;
  --p-inputtext-placeholder-color:      #40493d;
  --p-inputtext-border-color:           #bfcaba;
  --p-inputtext-hover-border-color:     #707a6c;
  --p-inputtext-focus-border-color:     #0d631b;
}

.app-password-host input:-webkit-autofill,
.app-password-host input:-webkit-autofill:hover,
.app-password-host input:-webkit-autofill:focus {
  -webkit-box-shadow: 0 0 0 1000px #ffffff inset !important;
  -webkit-text-fill-color: #1a1c1c !important;
}
</style>
