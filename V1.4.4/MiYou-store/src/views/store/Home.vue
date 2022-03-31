<template>
  <div class="store-home">
    <p>
      全部:
      <span class="all">{{goodsList.length}}</span>
      已上架:
      <span class="Is">{{isputaway.length}}</span>
      已下架:
      <span class="No">{{Unputaway.length}}</span>
    </p>
    <goods :goodsList="goodsList" />
  </div>
</template>

<script>
import goods from './goodsList.vue'
import { getRequst } from '../../../uilts/api'
export default {
  components: {
    goods
  },
  data () {
    return {
      goodsList: '',
      isputaway: [],
      Unputaway: []
    }
  },
  created () {
    this.getGoods()
  },
  methods: {
    // 获取商品信息
    async getGoods () {
      const sjtoken = sessionStorage.getItem('sjtoken')
      const goods = await getRequst('/getSjidfindGoods', { sjtoken: sjtoken })
      this.goodsList = goods
      goods.forEach(element => {
        if (element.isputaway) {
          this.isputaway.push(element)
        } else {
          this.Unputaway.push(element)
        }
      })
    }

  }
}
</script>

<style lang="less" scoped>
* {
  padding: 0;
  margin: 0 auto;
}
.all {
  font-size: 20px;
  color: rgb(255, 0, 0);
  font-weight: bold;
  margin-right: 10px;
}
.Is {
  font-size: 20px;
  color: rgb(3, 192, 51);
  font-weight: bold;
  margin-right: 10px;
}
.No {
  font-size: 20px;
  color: rgb(255, 166, 0);
  font-weight: bold;
  margin-right: 10px;
}
</style>
