<template>
  <div class="Good">
    <div class="goods-box">
      <div class="goods" v-for="item in goodsList" :key="item.index">
        <img :src="`http://localhost:8082/`+item.img" alt />
        <p class="sp-ms">{{item.describe}}</p>
        <p class="sp-jg">
          <span>￥</span>
          <span>{{item.price}}</span>
        </p>
        <p class="btn">
          <el-button type="primary" @click="toGood(item.sid)">编辑</el-button>
          <el-button v-if="item.isputaway" type="danger" @click="OutGood(item.sid,false)">下架</el-button>
          <el-button v-else type="success" @click="OutGood(item.sid,true)">上架</el-button>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import { postRequst } from '../../../uilts/api'
export default {
  name: 'goods',
  props: { goodsList: Array },
  data () {
    return {
      sjtoken: sessionStorage.getItem('sjtoken'),
      goodData: this.goodsList
    }
  },
  created () {
    setTimeout(() => {
      console.log(this.goodData)
    }, 1000)
  },
  methods: {
    toGood (sid) {
      console.log(sid)
      this.$router.push({ path: '/sjgoodes', query: { sid: sid } })
    },
    // 商品下架
    OutGood (gid, isputaway) {
      console.log(gid, isputaway, this.sjtoken)
      const obj = {
        sid: gid,
        isputaway: isputaway
      }
      postRequst('/UpdataGoods', obj, this.sjtoken).then((res) => {
        console.log(res.code)
        if (res.code === 200) {
          window.location.reload()
        }
      })
    }
  }
}
</script>

<style lang="less">
.Good {
  width: 100%;
  display: flex;
  flex-direction: column;
  .goods-box {
    width: 1200px;
    margin: 0 auto;
    margin-top: 10px;
    display: flex;
    flex-wrap: wrap;
    div:nth-child(1) {
    }
    .goods {
      width: 230px;
      // height: 300px;
      background-color: white;
      margin: 0 5px 10px 5px;
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
      .btn {
        margin-bottom: 10px;
        display: flex;
        justify-content: space-around;
      }
    }
  }
}
</style>
