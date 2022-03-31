<template>
  <div class="shangpin">
    <div class="ssnr">{{`以下为“${searchs}”的全部搜索内容`}}</div>
    <div style="text-align: center;" v-show="goodsList==''||goodsList==null">
      没有搜索结果
      <router-link :to="'/'">返回首页</router-link>
    </div>
    <div class="shangpin-box">
      <div
        v-show="goodsList!=''||goodsList!=null"
        class="shangpin"
        v-for="item in goodsList"
        :key="item.index"
      >
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
  </div>
</template>

<script>
import { getRequst } from '../../../uilts/api'
export default {
  name: 'Shangpin',
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
  .shangpin-box {
    width: ((1290 * @2rem) / @base-size);
    margin: 0 auto;
    margin-top: ((10 * @2rem) / @base-size);
    display: flex;
    flex-wrap: wrap;

    div:nth-child(1) {
    }
    .shangpin {
      width: (((@box / 5) * @2rem) / @base-size);
      height: ((320 * @2rem) / @base-size);
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
          margin: ((25 * @2rem) / @base-size) auto;
          // background-color: #fa0000;
        }
        .sp-ms {
          font-size: ((13 * @2rem) / @base-size);
          padding: 0 ((10 * @2rem) / @base-size);
          overflow: hidden; /** 隐藏超出的内容 **/
          word-break: break-all;
          text-overflow: ellipsis; /** 多行 **/
          display: -webkit-box; /** 对象作为伸缩盒子模型显示 **/
          -webkit-box-orient: vertical; /** 设置或检索伸缩盒对象的子元素的排列方式 **/
          -webkit-line-clamp: 2;
        }
        .sp-jg {
          margin: ((10 * @2rem) / @base-size);
          color: #fa0000;
          font-weight: 700;
          span:nth-child(1) {
            font-size: ((12 * @2rem) / @base-size);
          }
        }
      }
    }
  }
}
</style>
