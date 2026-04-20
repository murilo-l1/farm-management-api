<template>
  <div class="app-layout">
    <AppSidebar />
    <main class="app-content">
      <RouterView />
    </main>
  </div>
</template>

<script setup lang="ts">
import AppSidebar from '@/components/AppSidebar.vue'
</script>

<style scoped>
.app-layout {
  display: flex;
  height: 100%;
  background-color: #f9f9f9;
}

.app-content {
  margin-left: 16rem;
  flex: 1;
  height: 100vh;
  overflow-y: auto;
}
</style>
