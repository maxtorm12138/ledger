import {createApp} from 'vue'
import App from './App.vue'
import TDesign from 'tdesign-vue-next'
import {createStore} from 'vuex';
import {AccountTree} from "./api/entity";
import './assets/main.css'

interface LedgerState {
    accountTree: AccountTree | null
}

const app = createApp(App)
const store = createStore({
    state: {
        accountTree: null
    } as LedgerState,
    mutations: {
        setAccountTree(state: LedgerState, accountTree: AccountTree) {
            state.accountTree = accountTree;
        }
    }
})

app.use(TDesign)
app.use(store)
app.mount('#app')

