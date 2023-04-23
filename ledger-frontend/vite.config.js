import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

const httpHost = 'http://localhost:52748';
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    proxy: {
      '^/api': {
        target: httpHost,
        changeOrigin: true,
        secure: false
      },
      '^/static': {
        target: httpHost,
        changeOrigin: true,
        secure: false
      }
    }
  }
})
