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
      password: '',
      num1: 0,
      num2: 0,
      sw: true
    }
  },
  components: {
    Banner,
    Shangpin
  },
  created () {
  },
  methods: {
  },
  mounted () {
  }
}
</script>

<style lang="less">
.home-box {
  background-color: #f4f4f4;
}
</style>
