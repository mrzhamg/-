import Vue from 'vue'
import Vuex from 'vuex'
import { getRequst } from '../uilts/api'
Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    user: {},
    userimg: {},
    address: {},
    classify: {}, // 商品分类
    sj_user: null
  },
  // 唯一能修改state状态
  mutations: {
    updataUser (state, data) {
      state.user = data
    },
    updataAddress (state, data) {
      state.address = data
    },
    updataClassify (state, data) {
      state.classify = data
    },
    updataUserimg (state, data) {
      state.userimg = data
    },
    updataSj_user (state, data) {
      state.sj_user = data
    }
  },
  // 异步处理
  actions: {
    async setSj_user ({ commit }, data) {
      await commit('updataSj_user', data)
    },
    // 获取用户信息
    async getUser ({ commit }) {
      const result = await getRequst('/selectusername')
      commit('updataUser', result.data.user)
    },
    // 获取用户地址
    async getAddress ({ commit }) {
      const resAddress = await getRequst('/getCarAddress')
      console.log('11111111111111', resAddress)
      commit('updataAddress', resAddress.data)
    },
    // 获取分类
    async getClassify ({ commit }) {
      await getRequst('/getClassify').then((res) => {
        commit('updataClassify', res)
      })
    },
    // 获取头像信息
    async getpic ({ commit }) {
      const userimg = await getRequst('/getpic')
      commit('updataUserimg', userimg.bid)
      console.log(userimg)
    }
  },
  modules: {}
})
