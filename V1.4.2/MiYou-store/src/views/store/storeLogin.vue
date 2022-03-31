<template>
  <div>
    <div class="storeReg-box">
      <p>商家登陆</p>
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
            <el-input v-model.number="ruleForm.age" placeholder="请输入账号或手机号"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pass">
            <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登陆</el-button>
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
    return {
      ruleForm: {
        pass: '',
        age: ''
      },
      rules: {
        pass: [
          { required: true, validator: validatePass, trigger: 'blur' }
        ],
        age: [
          { required: true, validator: checkAge, trigger: 'blur' }
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
          postRequst('/storelogin', data).then((res) => {
            if (res.code === 200) {
              sessionStorage.setItem('sjtoken', res.data.sjToken)
              setTimeout(() => {
                this.$router.replace('/storehome')
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
