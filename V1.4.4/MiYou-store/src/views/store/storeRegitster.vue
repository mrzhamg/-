<template>
  <div>
    <div class="storeReg-box">
      <p>注册成为商家</p>
      <div class="from">
        <el-form
          :model="ruleForm"
          status-icon
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="账号" prop="age">
            <el-input v-model.number="ruleForm.age"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pass">
            <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPass">
            <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="店铺名称" prop="storeName">
            <el-input v-model="ruleForm.storeName"></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="storePhone">
            <el-input v-model="ruleForm.storePhone"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import { postRequst } from '../../../uilts/api'
export default {
  data () {
    var checkAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('账号不能为空'))
      } else {
        callback()
      }
    }
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    var checkSdName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('店铺名称不能为空！'))
      } else {
        callback()
      }
    }
    var checkPhone = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('手机号不能为空！'))
      } else {
        callback()
      }
    }
    return {
      ruleForm: {
        pass: '',
        checkPass: '',
        age: '',
        storeName: '',
        storePhone: ''
      },
      rules: {
        pass: [
          { required: true, validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { required: true, validator: validatePass2, trigger: 'blur' }
        ],
        age: [
          { required: true, validator: checkAge, trigger: 'blur' }
        ],
        storeName: [
          { required: true, validator: checkSdName, trigger: 'blur' }
        ],
        storePhone: [
          { required: true, validator: checkPhone, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      const data = {
        sjid: null,
        sj_user: this.ruleForm.age,
        sj_password: this.ruleForm.pass,
        sj_name: this.ruleForm.storeName,
        sj_phone: this.ruleForm.storePhone
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          postRequst('/registerStore', data).then((res) => {
            if (res.code === 200) {
              setTimeout(() => {
                this.$router.replace('/storeLogin')
              }, 1000)
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}

</script>

<style lang="less" scoped>
.storeReg-box {
  width: 1200px;
  margin: 40px auto;
  p {
    text-align: center;
    margin-bottom: 40px;
  }
  .from {
    width: 500px;
    margin: 0 auto;
  }
}
</style>
