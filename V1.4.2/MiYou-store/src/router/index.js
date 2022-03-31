import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/index/index.vue'
import Home from '../views/index/Home.vue'
import About from '../views/index/About.vue'
import Login from '../views/index/Login.vue'
import Register from '../views/index/Register.vue'
import Shopping from '../views/index/Shopping.vue'
import Order from '../views/index/Order.vue'

import Goodes from '../views/index/Goodes.vue'
import Search from '../views/index/Search.vue'

// 商家页面
import StoreLogin from '../views/store/storeLogin.vue'
import StoreIndex from '../views/store/storeIndex.vue'
import StoreHome from '../views/store/Home.vue'
import ShopUpload from '../views/store/ShopUpload.vue'
import storeMsg from '../views/store/storeMsg.vue'
import storeDingDan from '../views/store/storeDingDan.vue'
import storeRegitster from '../views/store/storeRegitster.vue'
import GoodUpdata from '../views/store/goodUpdata.vue'

// import { mapActions } from 'vuex'
import { Message } from 'element-ui'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'index',
    component: Index,
    children: [{
      path: '/',
      name: 'Home',
      component: Home
    }, {
      path: '/about',
      name: 'About',
      component: About
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }, {
      path: '/register',
      name: 'Register',
      component: Register
    }, {
      path: '/shopping',
      name: 'shopping',
      component: Shopping
    }, {
      path: '/order',
      name: 'Order',
      component: Order
    }, {
      path: '/goodes',
      name: 'Goodes',
      component: Goodes
    }, {
      path: '/getSearch',
      name: 'Search',
      component: Search
    }, {
      path: '/sj',
      name: 'sj',
      component: ShopUpload
    }]
  }, {
    path: '/store',
    name: 'StoreIndex',
    component: StoreIndex,
    children: [{
      path: '/storeRegitster',
      name: 'storeRegitster',
      component: storeRegitster
    }, {
      path: '/storeLogin',
      name: 'StoreLogin',
      component: StoreLogin
    }, {
      path: '/storehome',
      name: 'StoreHome',
      component: StoreHome
    }, {
      path: '/shopUpload',
      name: 'ShopUpload',
      component: ShopUpload
    }, {
      path: '/storeMsg',
      name: 'storeMsg',
      component: storeMsg
    }, {
      path: '/storeDingDan',
      name: 'storeDingDan',
      component: storeDingDan
    }, {
      path: '/sjgoodes',
      name: 'goodUpdata',
      component: GoodUpdata
    }]
  }
]

const router = new VueRouter({
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  const token = sessionStorage.getItem('token')
  const sjtoken = sessionStorage.getItem('sjtoken')
  // console.log(token)
  if (token ||
     to.path === '/login' ||
     to.path === '/register' || to.path === '/' ||
     sjtoken || to.path === '/storehome' || to.path === '/goodes' ||
     to.path === '/storeRegitster' ||
     to.path === '/storeLogin') {
    next()
  } else if (to.path === '/about' || to.path === '/about' || to.path === '/shopping' || to.path === '/order') {
    next('/login')
    Message.error('请先登录')
  } else if (sjtoken || to.path === '/storehome' || to.path === '/storeRegitster' || to.path === '/storeLogin') {
    next()
  } else {
    next('/storeLogin')
    Message.error('请先登录')
  }
  // if (sjtoken || to.path === '/storehome' || to.path === '/storeRegitster' || to.path === '/storeLogin') {
  //   // next()
  // } else {
  //   // next('/storeLogin')
  //   Message.error('请先登录')
  // }
})
export default router
