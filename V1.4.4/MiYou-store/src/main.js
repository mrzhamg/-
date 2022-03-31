import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import { VueJsonp } from 'vue-jsonp'
import store from '../store/store'
// 全局引入axios
import axios from 'axios'

// 引入全局样式
import '../public/css/inddex.css'

import infiniteScroll from 'vue-infinite-scroll'
Vue.use(infiniteScroll)

Vue.config.productionTip = false
// 配置路径（请求后台路径）;
axios.defaults.baseURL = 'http://localhost:8082'
// 在请求头添加token
axios.interceptors.request.use(
  config => {
    // 判断是否存在token，如果存在的话，则每个http header都加上token
    const token = sessionStorage.getItem('token')
    if (token) {
      config.headers.Authorization = token
      // console.log('请求头token：', config.headers.Authorization)
    } else {
      config.headers.Authorization = null
    }
    return config
  }, error => {
    return Promise.reject(error)
  }
)
// 2.0的方法 全局挂载axios
Vue.prototype.$axios = axios

Vue.use(ElementUI)
Vue.use(VueJsonp)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
