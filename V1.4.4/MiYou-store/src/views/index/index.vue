<template>
  <div class="app-box">
    <div class="app-top">
      <MYtop></MYtop>
    </div>
    <div class="app-nav">
      <Nav></Nav>
    </div>
    <div class="view">
      <div>
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import MYtop from '../../components/my_top.vue'
import Nav from '../../components/nav.vue'
export default {
  name: 'Index',
  data () {
    return {
      activeIndex: '1',
      activeIndex2: '1'
    }
  },
  components: {
    MYtop,
    Nav
  },
  methods: {
    handleSelect (key, keyPath) {
      // console.log(key, keyPath)
    }
  },
  mounted () {
  }
}
</script>

<style lang="less">
.app-box {
  // background-color: #42b983;
  .app-top {
    width: 100%;
    background-color: #f0f8ff;
  }
  .app-nav {
    width: 100%;
    background-color: white;
  }
  .view {
    width: 100%;
    margin: 0 auto;
    & > div {
      width: 1200px;
      margin: 0 auto;
    }
  }
  #nav {
    // padding: 30px;

    a {
      font-weight: bold;
      color: #2c3e50;

      &.router-link-exact-active {
        color: #42b983;
      }
    }
  }
}
</style>
