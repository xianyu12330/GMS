import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import './assets/main.css'

// element-plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'


const app = createApp(App)

app.use(ElementPlus, {
  locale: zhCn
})
app.use(createPinia())
app.use(router)
router.isReady().then(() => app.mount('#app'))

