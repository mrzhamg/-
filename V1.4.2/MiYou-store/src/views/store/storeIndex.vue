<template>
  <div class="store-box">
    <div class="store-top">
      <span>蜜柚商城-商家</span>
    </div>
    <el-menu
      :default-active="$route.path"
      router
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
    >
      <el-menu-item index="/storehome">我的商品</el-menu-item>
      <el-menu-item index="/shopUpload">商品上传</el-menu-item>
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
export default {
  data () {
    return {
      activeIndex: '1',
      activeIndex2: '1'
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
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
