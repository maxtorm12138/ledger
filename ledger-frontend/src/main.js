import { createApp } from 'vue'
import App from './App.vue'
import TDesign from 'tdesign-vue-next'
import { createStore } from 'vuex';
import axios from "axios";
import './assets/main.css'

const app = createApp(App)
const store = createStore({})

app.use(TDesign)
app.use(store)
app.mount('#app')

