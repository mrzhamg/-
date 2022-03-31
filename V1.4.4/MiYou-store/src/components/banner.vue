<template>
  <div class="banner">
    <swiper :options="swiperOptionTop"
            class="gallery-top"
            ref="mySwiper">
      <swiper-slide v-for="item in bannerList"
                    :key="item.index">
        <router-link to="/">
          <img :src="`http://localhost:8082`+item.img"
               alt />
        </router-link>
      </swiper-slide>
    </swiper>
    <div class="swiper-pagination"
         slot="pagination"></div>
    <div class="b-left"></div>
    <div class="b-right"></div>
  </div>
</template>

<script>
import { swiper, swiperSlide } from 'vue-awesome-swiper'
import 'swiper/dist/css/swiper.css'
import { getRequst } from '../../uilts/api'
export default {
  name: 'Banner',
  components: {
    swiper,
    swiperSlide
  },
  data () {
    return {
      bannerList: '',
      left_i: 1,
      swiperOptionTop: {
        loop: false,
        autoplay: {
          delay: 3000,
          stopOnLastSlide: false,
          disableOnInteraction: false
        },
        // 显示分页
        pagination: {
          el: '.swiper-pagination',
          clickable: true // 允许分页点击跳转
        },
        // 设置点击箭头
        navigation: {
          nextEl: '.b-right',
          prevEl: '.b-left'
        }
      }
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
    }
  },
  computed: {
  }
}
</script>

<style lang="less" scoped>
.banner {
  position: relative;
  margin: 0 auto;
  width: 1200px;
  height: 600px;
  overflow: hidden;
  .gallery-top {
    width: 100%;
    height: 100%;
  }
  &:hover .b-left,
  &:hover .b-right {
    opacity: 1;
    transition: all 0.5s;
  }
  .b-left {
    width: 40px;
    height: 70px;
    border-bottom-right-radius: 30px;
    border-top-right-radius: 30px;
    background: #fb7f83;
    position: absolute;
    left: 0;
    top: 50%;
    transform: translateY(-35px);
    z-index: 99;
    opacity: 0;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .b-left::after {
    content: '';
    display: inline-block;
    width: 20px;
    height: 20px;
    border-top: 3px solid #fff;
    border-left: 3px solid #fff;
    transform: rotate(-45deg);
  }
  .b-right {
    width: 40px;
    height: 70px;
    border-bottom-left-radius: 30px;
    border-top-left-radius: 30px;
    background: #fb7f83;
    position: absolute;
    right: 0;
    top: 50%;
    transform: translateY(-35px);
    z-index: 99;
    opacity: 0;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .b-right::after {
    content: '';
    display: inline-block;
    width: 20px;
    height: 20px;
    border-top: 3px solid #fff;
    border-left: 3px solid #fff;
    transform: rotate(135deg);
  }
  .swiper-pagination {
    width: 100%;
    position: absolute;
    bottom: 0;
  }
  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    object-position: center;
  }
}
</style>
