import { createApp } from 'vue'
import App from './App.vue'
import TDesign from 'tdesign-vue-next'
import axios from "axios";
import './assets/main.css'

const app = createApp(App)
app.use(TDesign)
app.config.globalProperties.$http = axios;

app.mount('#app')

