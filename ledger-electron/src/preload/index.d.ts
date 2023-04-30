import { ElectronAPI } from '@electron-toolkit/preload'

declare global {
  interface Window {
    APP_URL: string
  }
}
