<template>
  <div class="shangpin-box">
    <div class="shangpin"
         v-for="item in goodsList"
         :key="item.index">
      <router-link :to="'goodes?sid='+item.sid">
        <img :src="`http://localhost:8082/`+item.img"
             alt />
        <p class="sp-ms name1">{{item.describe}}</p>
        <div class="sp-ms ms1">{{item.miaoshu}}</div>
        <p class="sp-jg">
          <span>￥</span>
          <span>{{item.price}}</span>
        </p>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  props: { goodsList: Array },
  data () {
    return {}
  }
}
</script>

<style lang="less" scoped>
.shangpin-box {
  width: 1200px;
  margin: 0 auto;
  margin-top: 10px;
  -moz-column-count: 5; /* Firefox */
  -webkit-column-count: 5; /* Safari 和 Chrome */
  column-count: 5;
  position: relative;
  margin-bottom: 50px;
  a.btn {
    position: absolute;
    bottom: -30px;
    left: 50%;
  }
  div:nth-child(1) {
  }
  .shangpin {
    width: 230px;
    background-color: white;
    box-sizing: border-box;
    -moz-page-break-inside: avoid;
    -webkit-column-break-inside: avoid;
    break-inside: avoid;
    margin-bottom: 10px;
    a {
      width: 100%;
      height: 100%;
      display: flex;
      flex-direction: column;
      img {
        width: 90%;
        height: 55%;
        margin: 10px auto;
        // background-color: #fa0000;
        object-fit: cover;
        object-position: center;
      }
      .sp-ms {
        font-size: 16px;
        padding: 0 20px;
        overflow: hidden; /** 隐藏超出的内容 **/
        word-break: break-all;
        text-overflow: ellipsis; /** 多行 **/
      }
      .name1 {
        display: -webkit-box;
        -webkit-line-clamp: 1;
        -webkit-box-orient: vertical;
        overflow: hidden;
        font-size: 16px;
        font-weight: 600;
        margin-bottom: 5px;
      }
      .ms1 {
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        overflow: hidden;
        font-size: 14px;
      }
      .sp-jg {
        margin: 10px 20px;
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
