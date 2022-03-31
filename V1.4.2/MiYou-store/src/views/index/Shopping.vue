<template>
  <div class="car-box">
    <div><strong>我的购物车</strong></div>
    <div>
      <div class="car-top">
        <p>全部商品：<span>{{cars.length}}</span></p>
        <div class="add-select">
          <span>选择配送地址：</span>
          <template>
            <el-select v-model="addressValue"
                       @change="changeSelect"
                       size="small"
                       placeholder="请选择">
              <el-option v-for="item in address"
                         :key="item.aid"
                         :label="`${item.shoujianName};${item.addres};  ${item.sjrPhone}`"
                         :value="item.aid">
              </el-option>
            </el-select>

          </template>
        </div>
      </div>
      <table cellpadding="5"
             cellspacing="5"
             width="100%">
        <tr>
          <td><input type="checkbox"
                   @click="checkboxAll"
                   name=""
                   id=""></td>
          <td>商品信息</td>
          <td>单价</td>
          <td>数量</td>
          <td>小计</td>
          <td>操作</td>
        </tr>

        <tr v-for="(item,index) in cars"
            :key="index">
          <td><input type="checkbox"
                   @click="checkboxItem(item,index)"
                   name=""
                   :id="checkboxID(index)"></td>
          <td>
            <div class="sp-box">
              <div>
                <img :src="'http://localhost:8082/'+item.imgurl"
                     alt="">
              </div>
              <div>
                <p>店铺：<span class="sp-g">{{item.dpname}}</span></p>
                <p>描述：<span class="sp-g">{{item.goods_ms}}</span></p>
              </div>
            </div>
          </td>
          <td>￥{{item.price}}</td>
          <td>
            <div class="num-box">
              <input type="button"
                     name=""
                     value="-"
                     @click="item.num==1 ? 1:item.num--">
              <input type="text"
                     name=""
                     id=""
                     v-model="item.num">
              <input type="button"
                     name=""
                     value="+"
                     @click="item.num==99 ? 99:item.num++">
            </div>
          </td>
          <td>￥<span class="sp-xj zj">{{(item.price*item.num).toFixed(2)}}</span></td>
          <td>
            <el-button type="danger"
                       icon="el-icon-delete"
                       circle
                       @click="deleteCar(item.cid)"></el-button>
          </td>
        </tr>
      </table>
      <div class="car-bottom">
        <div>

          <p>已选商品：<span class="sp-xj">{{getPrice.num}}</span></p>
        </div>
        <div>
          <div>总价：<span class="sp-xj">{{getPrice.zj}}</span></div>
          <div>
            <el-button type="danger"
                       @click="toSettle"
                       round>去结算</el-button>
          </div>
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
      // 购物车全部商品
      cars: '',
      // 已选商品
      putCar: [],
      // num: 1,
      PriceAll: 0,
      // 已选商品
      selectNum: 0,
      // 配送地址
      addressValue: '',
      // 我的地址
      address: []
    }
  },
  created () {
    this.getCars()
    this.getAddress()
  },
  computed: {
    getPrice: function () {
      // 查找this.cars里select属性为true的数据
      var proList = this.cars.filter((val) => { return val.select })
      var zj = 0
      var num = 0
      if (proList !== 0) {
        // console.log(proList)
        num = proList.length
        for (const i in proList) {
          zj += proList[i].price * proList[i].num
        }
      }
      return { zj: zj.toFixed(2), num }
    }
  },

  methods: {
    // 获取地址信息
    async getAddress () {
      await getRequst('/getCarAddress').then((res) => {
        // console.log(res)
        this.address = res
      })
    },

    // 获取购物车信息
    async getCars () {
      await getRequst('/getCars').then((res) => {
        console.log('cars=>', res)
        this.cars = res
        this.insertSelect()
      })
    },
    // 删除购物车
    async deleteCar (cid) {
      console.log('删除cid=>', cid)
      await getRequst('/deleteCars', { cid: cid })
      setTimeout(() => {
        location.reload()
      })
    },
    // 商品数据列表中插入Select:checked属性
    insertSelect () {
      const _this = this
      this.cars.map((item) => {
        _this.$set(item, 'select', false)
      })
    },
    // 全选
    checkboxAll (e) {
      console.log(e.target.checked)
      // this.selectNum = this.cars.length
      for (const i in this.cars) {
        this.cars[i].select = e.target.checked
        const checkboxID = document.querySelector('#checkboxID' + i)
        checkboxID.checked = e.target.checked
        if (e.target.checked) {
          this.PriceAll += this.cars[i].price * this.cars[i].num
        } else {
          this.PriceAll = 0
        }
      }
    },
    // 单选
    checkboxItem (item, index) {
      const checkboxID = document.querySelector('#checkboxID' + index)
      if (checkboxID.checked) {
        this.cars[index].select = true
      } else {
        this.cars[index].select = false
      }
    },

    // 绑定id
    checkboxID: (index) => {
      return 'checkboxID' + index
    },
    // 选择地址
    changeSelect (aid) {
      // 查找address数据中aid=aid的数据对象
      var addSelect = this.address.filter((val) => { return val.aid === aid })
      console.log(addSelect)
      // 把选择的地址数据插入商品数据中，以便生成订单信息
      const _this = this
      this.cars.map((item) => {
        _this.$set(item, 'address', addSelect[0])
      })
    },

    // 去结算
    async toSettle () {
      // console.log('结算')
      var proList = this.cars.filter((val) => { return val.select })
      // console.log(proList.length)
      if (this.addressValue === '' || this.addressValue === null) {
        this.$message({
          type: 'error',
          message: '请选择配送地址'
        })
      } else if (proList.length === 0) {
        this.$message({
          type: 'error',
          message: '请选择需要购买的商品'
        })
      } else {
        console.log('生成订单', proList)
        // 生成下单时间

        var orderList = []
        for (const i in proList) {
          // 发送生成订单请求
          var order = {
            oid: 0,
            uid: proList[i].uid,
            sjid: proList[i].sjid,
            dpname: proList[i].dpname,
            gid: proList[i].gid,
            goods_ms: proList[i].goods_ms,
            price: proList[i].price,
            num: proList[i].num,
            aid: proList[i].address.aid,
            sjr_name: proList[i].address.shoujianName,
            sjr_phone: proList[i].address.sjrPhone,
            address: proList[i].address.addres,
            imgurl: proList[i].imgurl
          }
          orderList.push(order)
        }
        console.log(orderList)
        await postRequst('/insertOrder', orderList)
      }
    }

  }

}
</script>

<style lang="less">
.car-box {
  .sp-box {
    display: flex;
    & > div:nth-child(1) {
      width: 100px;
      height: 100px;
      border: 1px solid gray;
      img {
        width: 100%;
        height: 100%;
      }
    }
    & > div:nth-child(2) {
      margin-left: 10px;
      .sp-g {
        color: gray;
      }
    }
  }
  .car-top {
    display: flex;
    justify-content: space-between;
    // background-color: aqua;
    .add-select {
    }
  }

  .num-box {
    display: flex;
    & > input:nth-child(2) {
      width: 20px;
      text-align: center;
    }
  }
  .sp-xj {
    font-size: 18px;
    color: red;
    font-weight: 700;
  }
  .car-bottom {
    // position: fixed;
    // bottom: 0;
    display: flex;
    justify-content: space-between;
    align-items: center;
    // background-color: aqua;
    & > div:nth-child(2) {
      display: flex;
      align-items: center;
      button {
        margin: 0 30px;
      }
    }
  }
}
</style>
