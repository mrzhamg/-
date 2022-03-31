<template>
  <div class="home-box">
    <Banner />
    <Shangpin />
  </div>
</template>

<script>
import Banner from '../../components/banner.vue'
import Shangpin from '../../components/shangpin.vue'
export default {
  data () {
    return {
      name: '',
      password: ''
    }
  },
  components: {
    Banner,
    Shangpin
  }
}
</script>

<style lang="less">
.home-box {
  background-color: #f4f4f4;
}
</style>
