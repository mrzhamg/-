<template>
  <div>
    <div class="nav-box">
      <el-menu
        :default-active="$route.path"
        router
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
      >
        <el-menu-item index="/">首页</el-menu-item>
        <el-submenu index>
          <template slot="title">商品分类</template>
          <el-menu-item v-for="item in classifydata" :key="item.index" index>{{item.name}}</el-menu-item>
        </el-submenu>
        <el-menu-item index="/order">我的订单</el-menu-item>

        <el-menu-item index="/login">登录</el-menu-item>
        <el-menu-item index="/register">注册</el-menu-item>
        <el-menu-item index="/about">个人中心</el-menu-item>

        <el-menu-item v-if="userdata.isStore" index="/storehome">我是商家</el-menu-item>
        <el-menu-item v-else-if="!userdata.isStore&userdata!=nill" index="/storehome">商家注册</el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'Nav',
  data () {
    return {
      classify: '',
      user: ''
    }
  },
  created () {
    this.getClassify()
  },
  methods: {
    ...mapActions(['getClassify'])
  },
  computed: {
    classifydata: function () {
      return this.$store.state.classify
    },
    userdata: function () {
      return this.$store.state.user
    }
  }
}
</script>

<style lang="less">
.nav-box {
  width: 1200px;
  margin: 0 auto;
}
</style>
