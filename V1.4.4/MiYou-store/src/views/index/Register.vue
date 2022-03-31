<template>
  <div class="reg-box">
    <div class="reg-from">
      <h3>用户注册</h3>
      <el-form :model="ruleForm"
               :rules="rules"
               ref="ruleForm"
               label-width="100px"
               class="demo-ruleForm">
        <el-form-item label="账号"
                      prop="username">
          <el-input type="name"
                    class="regname"
                    prefix-icon="el-icon-user"
                    placeholder="邮箱/手机号"
                    v-model="ruleForm.username" />
        </el-form-item>

        <el-form-item label="密码"
                      prop="password">
          <el-input type="password"
                    class="regpsw"
                    placeholder="最多6个字符"
                    prefix-icon="el-icon-lock"
                    v-model="ruleForm.password" />
        </el-form-item>
        <el-form-item label="昵称"
                      prop="name">
          <el-input type="name"
                    class="regname"
                    placeholder="最多12个字符"
                    v-model="ruleForm.name" />
        </el-form-item>
        <el-form-item label="性别"
                      prop="sex">
          <el-radio v-model="ruleForm.radio"
                    :label="1">男</el-radio>
          <el-radio v-model="ruleForm.radio"
                    :label="0">女</el-radio>
        </el-form-item>
        <el-form-item label="年龄"
                      prop="age">
          <el-input type="number"
                    class="regname"
                    v-model="ruleForm.age" />
        </el-form-item>
        <el-form-item label="手机号"
                      prop="phone">
          <el-input type="text"
                    class="regname"
                    placeholder="手机号"
                    v-model="ruleForm.phone" />
        </el-form-item>
        <el-form-item class="form-btn">
          <el-button type="primary"
                     @click="submitForm('ruleForm')"> 注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { postRequst } from '../../../uilts/api'
export default {
  data () {
    return {
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
          { required: true, message: '请输入密码', trigger: 'blur' },
          { required: true, min: 6, max: 6, message: '请输入6个字符的密码', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入昵称', trigger: 'blur' },
          { required: true, min: 1, max: 12, message: '不能超12个字符哦', trigger: 'blur' }],
        age: [{ required: false, message: '请输入年龄', trigger: 'blur' }],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { required: true, message: '手机号输入不正确', min: 11, max: 11, trigger: 'blur' }
        ]
      }
    }
  },

  methods: {
    // 注册
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const username = this.ruleForm.username
          const password = this.ruleForm.password
          const name = this.ruleForm.name
          const sex = this.ruleForm.radio
          const age = this.ruleForm.age
          const phone = this.ruleForm.phone
          // console.log(username, password, name, sex, age, phone)
          postRequst('/register', { username, password, name, sex, age, phone }).then((res) => {
            console.log(res)
            // 注册成功跳转到首页
            this.$router.replace('/login')
          })
          // console.log(this.ruleForm.username, this.ruleForm.password)
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }

  }
}
</script>

<style lang="less">
.reg-box {
  display: flex;
  justify-content: center;
  h3 {
    padding-left: 90px;
    text-align: center;
    // background-color: aqua;
  }
  .reg-from {
    width: 500px;
    padding-right: 100px;
    .form-btn {
      text-align: center;
      // background-color: aqua;
      button {
        width: 200px;
      }
    }
  }
}
</style>
