import './assets/main.scss'

import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 因为这里路由的文件是 index.js， 所以直接写@/router就可以了，
// 如果是其他名字，需要在router后面加上文件名，@/router/***.js
import router from '@/router'
// 导入 pinia
import { createPinia } from 'pinia'
// persist插件
import { createPersistedState } from 'pinia-persistedstate-plugin'
// 导入中文语言包
import locale from 'element-plus/dist/locale/zh-cn.js'

const app = createApp(App)
const pinia = createPinia()
const persist = createPersistedState()
pinia.use(persist)
app.use(pinia)
app.use(router)
app.use(ElementPlus, {locale})
app.mount('#app')
