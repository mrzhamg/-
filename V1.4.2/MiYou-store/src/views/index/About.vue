<template>
  <div class="about">
    <!-- 基本信息开始 -->
    <div class="about-jbxx">
      <strong>基本信息</strong>
      <el-descriptions title :column="2">
        <strong>基本信息</strong>
        <el-descriptions-item label="用户头像">
          <div class="img-box">
            <div class="userImg">
              <img :src="`http://localhost:8082`+user.imgurl" alt />
            </div>
            <el-upload
              class="upload-demo"
              :with-credentials="true"
              action="http://localhost:8082/postpicpath"
              :headers="myHeader"
              :on-success="upSuccess"
            >
              <el-button size="mini" type="primary">更换头像</el-button>
            </el-upload>
          </div>
        </el-descriptions-item>
        <el-descriptions-item label="用户ID">{{userdata.uid}}</el-descriptions-item>
        <el-descriptions-item label="账号">{{userdata.username}}</el-descriptions-item>
        <el-descriptions-item label="昵称">{{userdata.name}}</el-descriptions-item>
        <el-descriptions-item label="性别">{{userdata.sex==1? '男':'女'}}</el-descriptions-item>
        <el-descriptions-item label="年龄">{{userdata.age}}</el-descriptions-item>
        <el-descriptions-item label="注册手机号">{{userdata.phone}}</el-descriptions-item>
        <el-descriptions-item label="注册时间">{{userdata.date}}</el-descriptions-item>
        <el-descriptions-item label="是否为商家">{{userdata.isStore? '是':'否'}}</el-descriptions-item>
        <!-- <el-descriptions-item label="默认地址"><template>
            <el-select v-model="addressValue"
                       size="small"
                       placeholder="请选择">
              <el-option v-for="item in user.address"
                         :key="item.aid"
                         :label="`${item.addres}; ${item.shoujianName}; ${item.sjrPhone}`"
                         :value="item.aid">
              </el-option>
            </el-select>

        </template></el-descriptions-item>-->
      </el-descriptions>

      <strong>我的地址</strong>
      <template>
        <el-table :data="user.address" style="width: 100%">
          <el-table-column prop="aid" label="地址ID" width="180"></el-table-column>
          <el-table-column prop="shoujianName" label="收件人" width="180"></el-table-column>
          <el-table-column prop="sjrPhone" label="收件人电话"></el-table-column>
          <el-table-column prop="addres" label="地址"></el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
              <el-button
                @click="deleteClick(scope.row.aid)"
                type="text"
                style="color:red"
                size="small"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <p class="addBtn" v-show="!user.address">
          <el-button @click="handleClick(address)" type="primary" size="small">添加地址</el-button>
        </p>
      </template>

      <p class="aboutBtn">
        <el-button type="primary" @click="edit">编辑</el-button>
        <el-button type="danger" @click="logout" plain>退出登录</el-button>
      </p>
    </div>
    <!-- 基本信息结束 -->

    <!-- 编辑窗口开始 -->
    <div class="edit-bg"></div>
    <!-- 上传头像窗口开始 -->
    <!-- 添加地址窗口开始 -->
    <div class="address">
      <!-- 添加地址表单开始 -->
      <h3>添加地址</h3>
      <el-form
        :model="addressForm"
        :rules="addressrules"
        ref="addressForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="用户ID" prop="uid">
          <el-input
            type="name"
            class
            prefix-icon="el-icon-user"
            disabled
            v-model="addressForm.uid"
          />
        </el-form-item>
        <el-form-item label="地址ID" prop="aid">
          <el-input type="name" class disabled v-model="addressForm.aid" />
        </el-form-item>

        <el-form-item label="收件人" prop="shoujianren">
          <el-input type="text" class placeholder="最多6个字符" v-model="addressForm.shoujianren" />
        </el-form-item>
        <el-form-item label="收件人电话" prop="sjrPhone">
          <el-input type="name" class placeholder="最多11个字符" v-model="addressForm.sjrPhone" />
        </el-form-item>

        <el-form-item label="收货地址" prop="address">
          <el-input type="textarea" class placeholder="请输入地址" v-model="addressForm.address" />
        </el-form-item>
        <el-form-item class="form-btn">
          <el-button type="primary" @click="editFormBtn('addressForm')">保存修改</el-button>
          <el-button type="primary" @click="addressFormBtn('addressForm')">添加</el-button>
          <el-button @click="resetAddForm()">取消</el-button>
        </el-form-item>
      </el-form>
      <!-- 添加地址表单结束 -->
    </div>
    <!-- 添加窗口地址结束 -->
    <div class="edit-page">
      <h3>修改个人信息</h3>
      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="账号" prop="username">
          <el-input
            type="name"
            class="regname"
            prefix-icon="el-icon-user"
            placeholder="邮箱/手机号"
            disabled
            v-model="ruleForm.username"
          />
        </el-form-item>

        <el-form-item label="新密码" prop="password">
          <el-input
            type="password"
            class="regpsw"
            placeholder="最多6个字符"
            prefix-icon="el-icon-lock"
            v-model="ruleForm.password"
          />
        </el-form-item>
        <el-form-item label="昵称" prop="name">
          <el-input type="name" class="regname" placeholder="最多12个字符" v-model="ruleForm.name" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio v-model="ruleForm.radio" :label="1">男</el-radio>
          <el-radio v-model="ruleForm.radio" :label="0">女</el-radio>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input type="number" class="regname" v-model="ruleForm.age" />
        </el-form-item>
        <el-form-item label="更换手机号" prop="phone">
          <el-input type="text" class="regname" placeholder="新手机号" v-model="ruleForm.phone" />
        </el-form-item>
        <el-form-item class="form-btn">
          <el-button type="primary" @click="submitForm('ruleForm')">保存修改</el-button>
          <el-button @click="resetForm()">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 编辑窗口结束 -->
  </div>
</template>

<script>
// import JiBenXX from '../components/about/JiBenXX'
import { getRequst, postRequst } from '../../../uilts/api'
// import { mapState } from 'vuex'
export default {

  data () {
    return {
      // 用户数据
      user: {
        uid: '',
        username: '',
        name: '',
        sex: '',
        age: '',
        phone: '',
        registerDate: '',
        // 地址数据
        address: null,
        imgurl: '',
        isStore: ''
      },
      addressValue: '',

      // 修改用户信息
      ruleForm: {
        username: '',
        password: '',
        name: '',
        age: 18,
        phone: '',
        radio: 1
      },
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { required: true, min: 6, max: 6, message: '请输入6个字符的密码', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入新昵称', trigger: 'blur' },
          { required: true, min: 1, max: 12, message: '不能超12个字符哦', trigger: 'blur' }],
        age: [{ required: false, message: '请输入年龄', trigger: 'blur' }],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { required: true, message: '手机号输入不正确', min: 11, max: 11, trigger: 'blur' }
        ]
      },

      // 收货地址窗口数据
      addressForm: {
        uid: '',
        aid: '',
        // 收件人
        shoujianren: '',
        sjrPhone: '',
        address: ''
      },
      addressrules: {
        shoujianren: [{ required: true, message: '收件人不能为空', trigger: 'blur' }, { required: true, min: 1, max: 6, message: '不能超6个字符哦', trigger: 'blur' }],
        sjrPhone: [{ required: true, message: '收件人电话不能为空', trigger: 'blur' }],
        address: [{ required: true, message: '收货地址不能为空', trigger: 'blur' }]
      },

      // 更换头像data
      myHeader: {
        Authorization: sessionStorage.getItem('token')
      }

    }
  },
  created () {
    this.getuser()
    this.getpic()
    this.getAddress()
  },
  methods: {
    // 获取用户信息
    async getuser () {
      // var token = sessionStorage.getItem('token')
      await getRequst('/selectusername').then((res) => {
        this.user.uid = res.data.user.uid
        this.myData.uid = res.data.user.uid
        this.user.username = res.data.user.username
        this.user.name = res.data.user.name
        this.user.sex = res.data.user.sex
        this.user.age = res.data.user.age
        this.user.phone = res.data.user.phone
        this.user.registerDate = res.data.user.date
        this.user.isStore = res.data.user.isStore
        // this.user.address = res.data.user.address
        console.log('sex=>', res)
        // this.user.imgurl = 'http://localhost:8082/getpic?uid=' + res.data.user.uid

        // 编辑输入框获取用户信息
        this.ruleForm.username = res.data.user.username
        this.ruleForm.name = res.data.user.name
        this.ruleForm.radio = res.data.user.sex
        this.ruleForm.age = res.data.user.age
        this.ruleForm.phone = res.data.user.phone

        this.getAddress(res.data.user.uid)
        // console.log(this.myHeader)
      })
      // console.log('sex=>', this.user)
    },
    async getAddress () {
      // console.log('1111111;', uid)
      await getRequst('/getCarAddress').then((res) => {
        // console.log('resAddress=>', res)
        this.user.address = res
      })
    },
    edit: function () {
      var uid = this.user.uid
      var username = this.user.username
      var name = this.user.name
      var sex = this.user.sex
      var age = this.user.age
      var phone = this.user.phone
      console.log(uid, username, name, sex, age, phone)
      document.querySelector('.edit-bg').style.display = 'block'
      document.querySelector('.edit-page').style.display = 'block'
    },
    logout: () => {
      console.log('退出登录')
      sessionStorage.removeItem('token')
      setTimeout(() => {
        location.reload()
      }, 200)
    },

    // 封装判断手机号格式方法
    ifPhone (phone) {
      const pat1 = /^[1][3,4,5,7,8,9][0-9]{9}$/
      if (!pat1.test(phone)) {
        this.$message({
          type: 'error',
          message: '手机号输入不正确'
        })
        return false
      } else {
        return true
      }
    },

    // 保存基本信息修改
    submitForm (ruleForm) {
      // var username_edit = this.ruleForm.username
      // var name_edit = this.ruleForm.name
      // var age_edit = this.ruleForm.age
      // var phone_edit = this.ruleForm.phone
      this.$refs[ruleForm].validate((val) => {
        // console.log(this.ruleForm.phone)
        const pat1 = /^[1][3,4,5,7,8,9][0-9]{9}$/
        // 所有输入框输入正确
        if (val) {
          // 判断手机号是否正确
          if (!pat1.test(this.ruleForm.phone)) {
            this.$message({
              type: 'error',
              message: '手机号输入不正确'
            })
          } else {
            console.log('手机号输入正确')
            // 发送请求
            getRequst('/user/updateUsre', {
              username: this.ruleForm.username,
              password: this.ruleForm.password,
              name: this.ruleForm.name,
              sex: this.ruleForm.radio,
              age: this.ruleForm.age,
              phone: this.ruleForm.phone
            }).then((res) => {
              if (res.code === 200) {
                console.log('退出登录')
                sessionStorage.removeItem('token')
                setTimeout(() => {
                  location.reload()
                }, 200)
              }
            })
          }
          console.log('11')
        } else {
          console.log(this.ruleForm.username)
        }
      })
    },
    // 基本信息取消按钮
    resetForm: () => {
      console.log('取消')
      // console.log(document.querySelector('.edit-bg'))
      document.querySelector('.edit-bg').style.display = 'none'
      document.querySelector('.edit-page').style.display = 'none'
    },
    // 编辑地址
    handleClick (row) {
      console.log(row)
      this.addressForm.uid = row.uid
      this.addressForm.aid = row.aid
      this.addressForm.shoujianren = row.shoujianName
      this.addressForm.sjrPhone = row.sjrPhone
      this.addressForm.address = row.addres
      document.querySelector('.edit-bg').style.display = 'block'
      document.querySelector('.address').style.display = 'block'
    },
    deleteClick (aid) {
      getRequst('/deleteAddress', { aid }).then((res) => {
        if (res.code === 200) {
          setTimeout(() => {
            location.reload()
          }, 200)
        }
      })
    },
    // 修改地址按钮
    editFormBtn (addressForm) {
      var aid = this.addressForm.aid
      var shoujianName = this.addressForm.shoujianren
      var sjrPhone = this.addressForm.sjrPhone
      var addres = this.addressForm.address
      this.$refs[addressForm].validate((val) => {
        if (val) {
          console.log('修改地址按钮', val)
          console.log(aid, shoujianName, sjrPhone, addres)
          // 判断手机号格式是否合法
          var ifP = this.ifPhone(sjrPhone)
          if (ifP) {
            // 请求修改地址接口
            postRequst('/undateAddress', { aid, shoujianName, sjrPhone, addres }).then((res) => {
              if (res.code === 200) {
                setTimeout(() => {
                  location.reload()
                }, 200)
              }
            })
          }
        }
      })
    },
    // 保存添加地址按钮
    addressFormBtn (addressForm) {
      var uid = this.addressForm.uid
      var aid = this.addressForm.aid
      var shoujianName = this.addressForm.shoujianren
      var sjrPhone = this.addressForm.sjrPhone
      var addres = this.addressForm.address
      this.$refs[addressForm].validate((val) => {
        if (val) {
          console.log('保存地址按钮', val)
          console.log(aid, uid, shoujianName, sjrPhone, addres)
          // 判断手机号格式是否合法
          var ifP1 = this.ifPhone(sjrPhone)
          if (ifP1) {
            console.log('手机号合法')
            // 请求创建地址接口
            postRequst('/insertAddress', { aid, uid, shoujianName, sjrPhone, addres }).then((res) => {
              if (res.code === 200) {
                setTimeout(() => {
                  location.reload()
                }, 200)
              }
            })
          }
        }
      })
    },

    // 地址窗口取消按钮
    resetAddForm () {
      document.querySelector('.edit-bg').style.display = 'none'
      document.querySelector('.address').style.display = 'none'
    },
    // 上传头像
    // myData () {
    //   return {
    //     uid: this.user.uid
    //   }
    // },
    // myHeader () {
    //   return {
    //     Authorization: window.sessionStorage.getItem('Token')
    //   }
    // },
    // 获取头像
    upSuccess (res) {
      if (res.code === 200) {
        this.$message({
          type: 'success',
          message: res.message
        })
        setTimeout(function () {
          window.location.reload()
        }, 200)
      }
    },
    async getpic () {
      var userimg = await getRequst('/getpic')
      // console.log('img=>', userimg)
      this.user.imgurl = userimg.bid
    }

  },
  computed: {
    userdata: function () {
      return this.$store.state.user
    }
  }
}
</script>

<style lang="less">
.about {
  // display: block;
  position: relative;
  width: 100%;
  background-color: #f4f4f4;
  .about-jbxx {
    width: 1200px;
    margin: 0 auto;
    .img-box {
      display: flex;
      // background-color: aqua;
      .userImg {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        margin-right: 10px;
        background-color: gray;
        overflow: hidden;
        display: flex;
        justify-content: center;
        align-items: center;
        img {
          width: 100%;
          height: 100%;
        }
      }
    }

    .aboutBtn {
      margin: 20px;
      text-align: center;
    }
    .address {
      margin: 0 5px;
    }
    .addBtn {
      text-align: center;
    }
  }
  .address {
  }
  .aboutTitle {
    height: 40px;
    line-height: 40px;
    text-align: center;
    background-color: azure;
    margin: 0;
  }

  .edit-bg {
    display: none;
    position: absolute;
    top: 0;
    width: 100%;
    height: 100vh;
    background-color: rgb(0, 0, 0);
    opacity: 0.5;
    z-index: 99;
  }
  .edit-page,
  .address {
    display: none;
    width: 60%;
    padding: 0 20px 20px 20px;
    position: absolute;
    top: 0;
    left: 50%;
    margin: 0 -30%;
    background-color: white;
    z-index: 999;
    h3,
    .form-btn {
      margin: 20px;
      text-align: center;
    }
  }
}
</style>
