import { contextBridge } from 'electron'

// Custom APIs for renderer
const api = {
  APP_URL: 'http://localhost:52774'
}
contextBridge.exposeInMainWorld('api', api)
