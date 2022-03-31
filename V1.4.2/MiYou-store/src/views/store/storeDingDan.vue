<template>
  <div class="orders-box">
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column fixed label="用户信息" width="200">
        <template slot-scope="scope">
          <p>
            <span>用户ID：{{scope.row.user.uid}}</span>
          </p>
          <p>
            用户昵称：
            <span>{{scope.row.user.name}}</span>
          </p>
          <p>用户电话：{{scope.row.user.phone}}</p>
        </template>
      </el-table-column>
      <el-table-column fixed label="下单日期" width="200">
        <template slot-scope="scope">
          <p>
            <span>{{scope.row.date}}</span>
          </p>
          <p>
            订单ID：
            <span>{{scope.row.oid}}</span>
          </p>
        </template>
      </el-table-column>
      <el-table-column label="商品信息" width="300">
        <template slot-scope="scope">
          <div class="sp-xx">
            <div>
              <img :src="'http://localhost:8082/'+scope.row.imgurl" alt />
            </div>
            <div>
              <p>
                店铺：
                <span class="sp-g">{{scope.row.dpname}}</span>
              </p>
              <p>
                描述：
                <span class="sp-g">{{scope.row.goods_ms}}</span>
              </p>
              <p>
                单价：
                <span class="sp-g">{{scope.row.price}}</span>
              </p>
              <p>
                数量：
                <span class="sp-g">{{scope.row.num}}</span>
              </p>
              <p>
                总价：
                <span class="sp-r">{{scope.row.num*scope.row.price}}</span>
              </p>
            </div>
          </div>
        </template>
      </el-table-column>

      <el-table-column label="收货地址" width="400">
        <template slot-scope="scope">
          <div class="address">
            <p>
              收件人：
              <span>{{scope.row.sjr_name}}</span>
            </p>
            <p>
              收件人电话：
              <span>{{scope.row.sjr_phone}}</span>
            </p>
            <p>
              收件地址：
              <span>{{scope.row.address}}</span>
            </p>
          </div>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="状态" width="100">
        <!-- <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">删除</el-button>
        </template>-->
        <template>已发货</template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getRequst } from '../../../uilts/api'
export default {
  data () {
    return {
      tableData: []
    }
  },
  created () {
    this.getOrder()
  },
  methods: {
    // 获取订单信息
    async getOrder () {
      const sjtoken = sessionStorage.getItem('sjtoken')
      await getRequst('/getSjOrder', { sjtoken: sjtoken }).then((res) => {
        console.log(res)
        this.tableData = res
      })
    },

    async handleClick (row) {
      console.log(row.oid)
      await getRequst('/deleteOrder', { oid: row.oid }).then((res) => {
        if (res.code === 200) {
          location.reload()
        }
      })
    }
  }
}
</script>

<style lang="less">
.orders-box {
  .sp-xx {
    display: flex;
    & > div:nth-child(1) {
      width: 100px;
      height: 100px;
      img {
        width: 100%;
        height: 100%;
      }
    }
    & > div:nth-child(2) {
      margin-left: 10px;
      p {
        .sp-g {
          font-weight: 700;
          color: gray;
        }
        .sp-r {
          font-weight: 700;
          color: red;
        }
      }
    }
  }
}
</style>
