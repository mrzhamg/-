<template>
  <div class="goodes">
    <strong>商品详情</strong>
    <div class="g-content">
      <div class="img">
        <img :src="'http://localhost:8082/'+goods.img"
             alt />
      </div>
      <div class="title">
        <div>
          <p class="shnagjia">
            <span class="span-g">商家id：</span>
            {{goods.sjid}}
            <span class="span-g">店铺名称：</span>
            {{goods.shangjia_user[0].sj_name}}
          </p>
          <p>
            <span class="span-g">商品名称：</span>
            {{goods.describe}}
          </p>
          <p>
            <span class="span-g">商品分类：</span>
            {{goods.classify}}
          </p>
          <p>
            <span class="span-g">商品描述：</span>
            {{goods.miaoshu}}
          </p>
          <p>
            <span class="span-g">价格</span>：￥
            <span class="span-red">{{goods.price}}</span>
          </p>
          <div class="num">
            <span class="span-g">数量：</span>
            <div class="numBtn">
              <input type="button"
                     @click="num==1? '1': num--"
                     value="-" />
              <input type="text"
                     v-model="num" />
              <input type="button"
                     @click="num==99? '99': num++"
                     value="+" />
            </div>
          </div>
          <p class="gwcBtn">
            <el-button type="danger"
                       @click="putShopping">加入购物车</el-button>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getRequst, postRequst } from '../../../uilts/api'
export default {
  data () {
    return {
      goods: {},
      price: '',
      num: '1'
    }
  },
  created () {
    this.getsid()
  },
  methods: {
    async getsid () {
      const sid = this.$route.query.sid

      await getRequst('/goodes', { sid: sid }).then((res) => {
        // console.log('sid=>', res)
        this.goods = res
      })
    },
    async putShopping () {
      const cars = {

        gid: this.$route.query.sid,
        sjid: this.goods.sjid,
        price: this.goods.price,
        num: this.num,
        dpname: this.goods.shangjia_user[0].sj_name,
        goods_ms: this.goods.describe,
        imgurl: this.goods.img
      }
      await postRequst('/insertCars', cars)
      console.log('添加购物车信息=》', cars)
    }
  }
}
</script>

<style lang="less">
.goodes {
  .g-content {
    margin: 10px 0;
    display: flex;
    .img {
      height: 300px;
      background-color: rgb(255, 255, 255);
      border: 1px solid gray;
      margin-right: 10px;
      img {
        height: 100%;
        object-fit: cover;
        object-position: center;
      }
    }
    .title {
      flex: 1;
      // background-color: aqua;
      & > div:nth-child(1) {
        .span-g {
          color: gray;
        }
        .span-red {
          color: red;
          font-weight: 700;
          font-size: 20px;
        }
        p {
          margin-bottom: 10px;
        }
        .shnagjia {
        }
        .num {
          display: flex;
          .numBtn {
            & input:nth-child(2) {
              width: 20px;
              text-align: center;
            }
          }
        }
        .gwcBtn {
          margin-top: 10px;
        }
      }
    }
  }
}
</style>
