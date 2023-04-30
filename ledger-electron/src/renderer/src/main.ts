import { createApp } from 'vue'
import App from './App.vue'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import { createStore } from 'vuex'

import { AccountTree } from './entity'

interface LedgerStore {
  accountTree: AccountTree | null
}

const store = createStore<LedgerStore>({
  state() {
    return {
      accountTree: null
    }
  },
  mutations: {
    setAccountTree: (state, newAccountTree: AccountTree) => {
      state.accountTree = newAccountTree
    }
  }
})

const app = createApp(App)
app.use(ElementPlus)
app.use(store)
app.mount('#app')
