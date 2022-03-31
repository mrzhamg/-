<template>
  <div class="login-box">
    <!--  -->
    <!-- 登录表单开始 -->
    <div class="loginForm">
      <p class="login-title">用户登录</p>
      <!-- <div class="login-leixing">
        <div>账号登录</div>
        <div>手机号登录</div>
      </div>-->
      <!-- 账号登录开始 -->
      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        :hide-required-asterisk="true"
        class="demo-ruleForm"
      >
        <el-form-item label="账号" prop="username">
          <el-input
            type="name"
            class="loginname"
            prefix-icon="el-icon-user"
            v-model="ruleForm.username"
          />
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input
            type="password"
            class="loginpsw"
            prefix-icon="el-icon-lock"
            v-model="ruleForm.password"
          />
        </el-form-item>

        <p class="link-wj-zc">
          <span>
            <router-link to>忘记密码？</router-link>
          </span>
          <span>
            <router-link to="/register">立即注册</router-link>
          </span>
        </p>

        <el-form-item class="form-btn">
          <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
      <!-- 账号登录结束 -->
      <!-- 手机号登录开始 -->
      <!-- <el-form :model="ruleForm_sjh"
               :rules="rules_sjh"
               ref="ruleForm_sjh"
               label-width="100px"
               :hide-required-asterisk="true"
               class="Shouji-ruleForm">
        <el-form-item label="手机号"
                      prop="phone">
          <el-input type="name"
                    class="phone"
                    prefix-icon="el-icon-user"
                    v-model="ruleForm_sjh.phone" />
        </el-form-item>

        <el-form-item label="验证码"
                      prop="yanzhengma">
          <el-input type="text"
                    class="YanZengMa"
                    v-model="ruleForm_sjh.yanzhengma" />
          <el-button id="getyzmBtn"
                     @click="getYanZhenMa()"> {{yzmBtn}}</el-button>
        </el-form-item>

        <el-form-item class="form-btn">
          <el-button type="primary"
                     @click="submitForm_sjh('ruleForm_sjh')"> 登录</el-button>
          <el-button @click="resetForm_sjh('ruleForm_sjh')"> 重置</el-button>
        </el-form-item>
      </el-form>-->
      <!-- 手机号登录结束 -->
    </div>
    <!-- 登录表单结束 -->
  </div>
</template>

<script>
import { postRequst, getRequst } from '../../../uilts/api'
import { mapActions } from 'vuex'
export default {
  data () {
    return {
      ruleForm: {
        username: '',
        password: ''
      },
      ruleForm_sjh: {
        phone: '',
        yanzhengma: ''
      },
      yzmBtn: '获取验证码',
      isdjs: false,
      djs: 60,
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      },
      rules_sjh: {
        phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
        yanzhengma: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
      }
    }
  },
  methods: {
    ...mapActions(['getUser', 'getAddress']),
    // 登录
    submitForm (formName) {
      // console.log(formName)
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const username = this.ruleForm.username
          const password = this.ruleForm.password
          postRequst('/login', { username, password }).then((res) => {
            console.log('登录成功=》', res)
            // 登录成功保存token
            sessionStorage.setItem('token', res.data.token)

            // 获取用户信息并保存到vuex
            this.getUser()
            // 获取用户地址
            this.getAddress()
            // 跳转到首页
            this.$router.replace('/')
            // setTimeout(() => {
            // 2毫秒后刷新一次页面
            // location.reload()
            // }, 200)
          })

          // console.log(this.ruleForm.username, this.ruleForm.password)
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    // 重置
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },

    // 手机号
    submitForm_sjh (formName) {
      // console.log(formName)
      this.$refs[formName].validate((val) => {
        if (val) {
          const phone = this.ruleForm_sjh.phone
          const yanzhengma = this.ruleForm_sjh.yanzhengma
          console.log(phone, yanzhengma)
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm_sjh (formName) { this.$refs[formName].resetFields() },
    getYanZhenMa () {
      // 验证手机号是否合法
      const phone = this.ruleForm_sjh.phone

      // 手机号正则表达式
      const pat1 = /^[1][3,4,5,7,8,9][0-9]{9}$/

      if (phone) {
        // console.log(phone)
        // 电话号不为空,判断是否合法
        if (!pat1.test(phone)) {
          console.log('手机号不正确')
          this.$message({
            type: 'error',
            message: '手机号不正确'
          })
        } else {
          console.log('手机号正确')
          // this.$message({
          //   type: 'success',
          //   message: '验证码已发送'
          // })
          // 点击后不能再点击
          document.getElementById('getyzmBtn').disabled = true
          // 发送验证码
          getRequst('/phoneLogin', { phone: phone }).then((res) => {
            console.log(res)
          })
          this.isdjs = true
          // 按钮显示倒计时
          if (this.isdjs) {
            const timer = setInterval(() => {
              this.yzmBtn = this.djs-- + 's'
              if (this.djs === 0) {
                this.isdjs = false
                clearInterval(timer)
                this.yzmBtn = '重新发送'
                this.djs = 60
                document.getElementById('getyzmBtn').disabled = false
              }
            }, 1000)
          }
        }
      } else {
        this.$message({
          type: 'error',
          message: '请输入手机号'
        })
      }
    }

  }
}
</script>

<style lang="less">
a {
  text-decoration: none;
  color: #5f6266;
}
.login-box {
  display: flex;
  justify-content: center;
  align-items: center;
  .loginForm {
    // 345*305
    width: 445px;
    height: 305px;
    // padding-right: 100px;
    // background-color: aqua;

    .el-form-item__content {
      padding-right: 100px;
    }
    .login-title {
      font-weight: 700;
      color: #5f6266;
      // padding-left: 80px;
      text-align: center;
    }
    .login-leixing {
      // width: 100%;
      height: 40px;
      // padding-left: 60px;
      display: flex;
      justify-content: center;
      line-height: 40px;
      div {
        flex: 1;
        text-align: center;
        background-color: #e1e6ee;
        &:hover {
          background-color: rgb(242, 247, 247);
          cursor: pointer;
        }
      }
    }
    .demo-ruleForm {
      padding: 10px 0;
      label {
        width: 50px;
      }
    }
    .link-wj-zc {
      // background-color: aliceblue;
      padding: 0 100px;
      display: flex;
      justify-content: space-between;
    }
    .form-btn {
      display: flex;
      justify-content: center;
    }

    // 手机号登录
    .Shouji-ruleForm {
      padding: 10px 0;
      background-color: #c5d4e9;
      .el-form-item__content {
        display: flex;
        justify-content: space-between;
        .YanZengMa {
          // width: 130px;
        }
      }
    }
  }
}
</style>
