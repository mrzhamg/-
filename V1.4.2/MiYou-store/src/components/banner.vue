<template>
  <div class="banner">
    <ul>
      <li>
        <a href>
          <img :src="`http://localhost:8082`+bannerList[bannerList.length-1].img" alt />
        </a>
      </li>
      <li v-for="item in bannerList" :key="item.index">
        <a href="#">
          <img :src="`http://localhost:8082`+item.img" alt />
        </a>
      </li>
      <li>
        <a href>
          <img :src="`http://localhost:8082`+bannerList[0].img" alt />
        </a>
      </li>
    </ul>
    <div class="b-left" @click="leftBtn">
      <div></div>
    </div>
    <div class="b-right" @click="rightBtn">
      <div></div>
    </div>
    <!-- 焦点开始 -->
    <ul>
      <li>1</li>
      <li>2</li>
      <li>3</li>
      <li>4</li>
      <li>5</li>
    </ul>
    <!-- 焦点结束 -->
    <swiper
      :slides-per-view="3"
      :space-between="50"
      @swiper="onSwiper"
      @slideChange="onSlideChange"
    >
      <swiper-slide>Slide 1</swiper-slide>
      <swiper-slide>Slide 2</swiper-slide>
      <swiper-slide>Slide 3</swiper-slide>...
    </swiper>
  </div>
</template>

<script>
// import { Navigation, Pagination, Scrollbar, A11y } from "swiper";
import { Swiper, SwiperSlide } from 'swiper/vue/swiper-vue.js'
import 'swiper/swiper.min.css'
import 'swiper/modules/navigation/navigation.min.css'
import 'swiper/modules/pagination/pagination.min.css'
import 'swiper/modules/scrollbar/scrollbar.min.css'
import { getRequst } from '../../uilts/api'
export default {
  name: 'Banner',
  components: {
    Swiper,
    SwiperSlide
  },
  data () {
    return {
      bannerList: '',
      left_i: 1
    }
  },
  created () {
    this.getBanner()
  },
  methods: {
    // 获取banner
    async getBanner () {
      const bannerlist = await getRequst('/findBannerAll')
      this.bannerList = bannerlist
      console.log(bannerlist)
    },

    // 左边按钮
    leftBtn () {
      const lBtn = document.querySelector('.banner>ul:nth-child(1)')
      // this.left_i++
      // if (this.left_i <= 0) {
      //   this.left_i = (this.bannerList.length)
      //   lBtn.style.transition = ''
      //   lBtn.style.transform = `translateX(${-826 * this.left_i}px)`
      // } else {
      this.left_i--
      if (this.left_i <= -1) {
        this.left_i = (this.bannerList.length - 1)
        lBtn.style.transform = `translateX(${-1200 * this.left_i}px)`
      } else {
        lBtn.style.transition = 'transform 0.5s'
        lBtn.style.transform = `translateX(${-1200 * this.left_i}px)`
      }

      console.log(lBtn, this.left_i, -1200 * this.left_i)
    },
    // 右边按钮
    rightBtn () {
      const lBtn = document.querySelector('.banner>ul:nth-child(1)')
      this.left_i++
      if (this.left_i >= (this.bannerList.length + 1)) {
        this.left_i = 1
        lBtn.style.transition = 'transform 0.5s'
        lBtn.style.transform = `translateX(${-1200 * this.left_i}px)`
      } else {
        lBtn.style.transition = 'transform 0.5s'
        lBtn.style.transform = `translateX(${-1200 * this.left_i}px)`
      }
    }
  }
}
</script>

<style lang="less">
.banner {
  position: relative;
  margin: 0 auto;
  width: 1200px;
  height: 600px;
  overflow: hidden;
  background-color: aqua;
  &:hover .b-left {
    opacity: 1;
  }
  &:hover .b-right {
    opacity: 1;
  }
  ul {
    width: 1200 * 6px !important;
    height: 100%;
    // transform: translateX(-826px);
    // transition: transform 0.5s;
    background-color: rgb(215, 224, 233);
    li {
      float: left;
      width: 1200px;
      height: 100%;
      a {
        display: inline-block;
        width: 100%;
        height: 100%;
        img {
          width: 100%;
          height: 100%;
          object-fit: contain;
        }
      }
    }
  }
  .b-left {
    position: absolute;
    left: 0;
    top: 50%;
    margin: -5% 0;
    width: 50px;
    height: 80px;
    background-color: rgb(250, 93, 45);
    border-top-right-radius: 35px;
    border-bottom-right-radius: 35px;
    z-index: 9999;
    display: flex;
    justify-content: center;
    align-items: center;
    // display: none;
    opacity: 0;
    &::before {
      content: '';
      display: inline-block;
      width: 25px;
      height: 25px;
      border-top: 3px solid white;
      border-left: 3px solid white;
      transform: rotate(-45deg);
      // background-color: #f4f4f4;
    }
  }
  .b-right {
    position: absolute;
    right: 0;
    top: 50%;
    margin: -5% 0;
    width: 50px;
    height: 80px;
    background-color: rgb(250, 93, 45);
    border-top-left-radius: 35px;
    border-bottom-left-radius: 35px;
    z-index: 9999;
    display: flex;
    justify-content: center;
    align-items: center;
    opacity: 0;
    &::before {
      content: '';
      display: inline-block;
      width: 25px;
      height: 25px;
      border-top: 3px solid white;
      border-left: 3px solid white;
      transform: rotate(135deg);
      // background-color: #f4f4f4;
    }
  }
}
</style>
