<template>
  <div class="shangpin-box">
    <div class="shangpin" v-for="item in goodsList" :key="item.index">
      <router-link :to="'goodes?sid='+item.sid">
        <img :src="`http://localhost:8082/`+item.img" alt />
        <p class="sp-ms">{{item.describe}}</p>
        <p class="sp-jg">
          <span>￥</span>
          <span>{{item.price}}</span>
        </p>
      </router-link>
    </div>
  </div>
</template>

<script>
import { getRequst } from '../../uilts/api'
export default {
  name: 'Shangpin',
  // props: { goodsList },
  data () {
    return {
      goodsList: ''
    }
  },
  created () {
    this.getGoods()
  },
  methods: {
    // 获取商品信息
    async getGoods () {
      const goods = await getRequst('/findGoodsAll')
      this.goodsList = goods
      console.log(goods)
    }

  }
}
</script>

<style lang="less">
.shangpin-box {
  width: 1200px;
  margin: 0 auto;
  margin-top: 10px;
  display: flex;
  flex-wrap: wrap;
  div:nth-child(1) {
  }
  .shangpin {
    width: 230px;
    height: 280px;
    background-color: white;
    margin: 0 5px 10px 5px;
    a {
      width: 100%;
      height: 100%;
      display: flex;
      flex-direction: column;
      img {
        width: 70%;
        height: 55%;
        margin: 25px auto;
        // background-color: #fa0000;
        object-fit: cover;
        object-position: center;
      }
      .sp-ms {
        font-size: 13px;
        padding: 0 10px;
        overflow: hidden; /** 隐藏超出的内容 **/
        word-break: break-all;
        text-overflow: ellipsis; /** 多行 **/
        display: -webkit-box; /** 对象作为伸缩盒子模型显示 **/
        -webkit-box-orient: vertical; /** 设置或检索伸缩盒对象的子元素的排列方式 **/
        -webkit-line-clamp: 2;
      }
      .sp-jg {
        margin: 10px;
        color: #fa0000;
        font-weight: 700;
        span:nth-child(1) {
          font-size: 12px;
        }
      }
    }
  }
}
</style>
