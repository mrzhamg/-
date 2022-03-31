<template>
  <div class="store-box">
    <div class="store-top">
      <span v-if="!getSj_user">蜜柚商城</span>
      <div v-else>
        <span>商家：{{getSj_user.sj_user}} </span>
        <span>店铺名：{{getSj_user.sj_name}}</span>
      </div>
    </div>
    <el-menu :default-active="$route.path"
             router
             class="el-menu-demo"
             mode="horizontal"
             @select="handleSelect"
             background-color="#545c64"
             text-color="#fff"
             active-text-color="#ffd04b">
      <el-menu-item index="/storehome">我的商品</el-menu-item>
      <el-menu-item index="/shopUpload">商品上传</el-menu-item>
      <el-menu-item index="/storeClassify">分类管理</el-menu-item>
      <el-menu-item index="/storeMsg">消息中心</el-menu-item>
      <el-menu-item index="/storeDingDan">订单管理</el-menu-item>
      <el-menu-item index="/storeLogin">登录</el-menu-item>
      <el-menu-item index="/storeRegitster">注册</el-menu-item>
      <el-menu-item index="/">返回用户端</el-menu-item>
    </el-menu>
    <router-view />
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  data () {
    return {
    }
  },
  created () {
    const sjuser = JSON.parse(window.sessionStorage.getItem('sj_user'))
    this.setSj_user(sjuser)
  },
  methods: {
    ...mapActions(['setSj_user']),
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    }
  },
  computed: {
    getSj_user () {
      return this.$store.state.sj_user
    }
  }
}
</script>

<style lang="less">
.store-box {
  .store-top {
    width: 100%;
    height: 100px;
    line-height: 100px;
    background-color: gray;
    color: white;
    span {
      margin-left: 20px;
      font-size: 30px;
      font-weight: bold;
    }
  }
}
</style>
