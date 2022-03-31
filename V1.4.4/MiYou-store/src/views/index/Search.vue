<template>
  <div class="shangpin">
    <div class="ssnr">{{`以下为“${searchs}”的全部搜索内容`}}</div>
    <div style="text-align: center;"
         v-show="goodsList==''||goodsList==null">
      没有搜索结果
      <router-link :to="'/'">返回首页</router-link>
    </div>
    <goods :goodsList="goodsList" />
  </div>
</template>

<script>
import goods from '../../components/shangpinList.vue'
import { getRequst } from '../../../uilts/api'
export default {
  name: 'Shangpin',
  components: { goods },
  data () {
    return {
      searchs: '',
      goodsList: ''

    }
  },
  created () {
    this.getGoods()
  },
  methods: {
    // 获取商品信息
    async getGoods () {
      const search = this.$route.query.searchs
      this.searchs = search
      // console.log(search)
      await getRequst('/getSearch', { searchs: search }).then((res) => {
        console.log(res)
        this.goodsList = res
      })
    }

  }
}
</script>

<style lang="less">
@2rem: 2rem;
@base-size: 50;
@box: 1210;
.shangpin {
  .ssnr {
    text-align: center;
  }
}
</style>
