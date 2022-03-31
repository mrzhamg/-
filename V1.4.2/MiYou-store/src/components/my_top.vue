<template>
  <div class="top-box">
    <div class="my-top">
      <div class="my-logo">
        <div>
          <strong>蜜柚商城</strong>
        </div>
      </div>
      <!-- 搜索框，购物车，登录状态 -->
      <div class="top-right">
        <div class="ss-shopping">
          <input type="text" class="ss" v-model="searchs" placeholder="请输入搜索内容" />
          <div @click="search">
            <router-link class="toSearch" :to="'/getSearch?searchs='+this.searchs">
              <i style="color:#ffffff">&#xe63d;</i>
            </router-link>
          </div>
        </div>
        <div class="my-cat" @click="toCart">
          <i>&#xe74b;</i>购物车
        </div>
        <div class="login">
          <div v-show="!userdata.name">
            <div class="user-logo">
              <img src="../assets/logo.png" alt />
            </div>
            <p>未登录</p>
          </div>
          <div v-show="userdata.name">
            <div class="user-logo">
              <img :src="'http://localhost:8082'+userimgdata" alt />
            </div>
            <p style="text-align:center">{{userdata.name}}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'MYtop',
  data () {
    return {
      user: '',
      userimg: '',
      searchs: ''
    }
  },
  created () {
    this.getpic()
    this.getUser()
    // console.log(this.user)
  },
  methods: {
    ...mapActions(['getpic', 'getUser']),
    toCart () {
      console.log('购物车')
      this.$router.push('/shopping')
    },
    // 搜索
    search () {
      console.log('搜索', this.searchs)
      setTimeout(function () {
        window.location.reload()
      }, 200)
    }
  },
  computed: {
    userdata: function () {
      return this.$store.state.user
    },
    userimgdata: function () {
      console.log(this.$store.state.userimg)
      return this.$store.state.userimg
    }
  }
}
</script>

<style lang="less">
.my-top {
  width: 1200px;
  height: 130px;
  margin: 0 auto;
  display: flex;
  // background-color: #42b983;
  .my-logo {
    width: 200px;
    height: 100%;
    background-color: aliceblue;
    div {
      width: 200px;
      height: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }
  .top-right {
    flex: 1;
    display: flex;
    align-items: center;
    padding: 0 10px;
    background-color: aliceblue;
    .ss-shopping {
      flex: 1;
      display: flex;
      height: 50px;
      border: 1px solid #fa0000;
      input {
        flex: 1;
        border-radius: 0;
        border: 0;
        padding: 0 15px;
        &:focus {
          outline: none;
        }
      }
      div {
        width: 60px;

        background-color: #fa0000;
        .toSearch {
          display: flex;
          justify-content: center;
          align-items: center;
          width: 100%;
          height: 100%;
        }
      }
    }
    .my-cat {
      display: flex;
      justify-content: center;
      align-items: center;
      width: 150px;
      height: 50px;
      margin: 0 30px;
      border: 1px solid gray;
      cursor: pointer;
    }
    .login {
      width: 100px;
      height: 100%;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      // background-color: #fa0000;
      .user-logo {
        width: 50px;
        height: 50px;
        border-radius: 50%;
        background-color: white;
        overflow: hidden;
        display: flex;
        justify-content: center;
        align-items: center;
        img {
          width: 100%;
          height: 100%;
        }
      }
      div {
        p {
          font-size: 12px;
        }
      }
    }
  }
  .el-menu-demo {
    width: 90%;
    background-color: #42b983;
  }
}
</style>
