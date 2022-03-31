<template>
  <div class="UpdataGood">
    <div class="img">
      <img :src="`http://localhost:8082/`+goodData.img" alt />
    </div>
    <div class="form">
      <el-form
        :model="newData"
        :rules="rules"
        ref="newData"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label>
          我的商家ID:
          <span
            style="color:red;font-weight: bold;margin-right:20px;"
          >{{goodData.shangjia_user[0].sjid}}</span>
          我的店铺名称:
          <span
            style="color:red;font-weight: bold;"
          >{{goodData.shangjia_user[0].sj_name}}</span>
        </el-form-item>
        <el-form-item label="商品ID" prop="sid">
          <el-input v-model="newData.sid" disabled></el-input>
        </el-form-item>
        <el-form-item label="商品名称 " prop="describe">
          <el-input v-model="newData.describe"></el-input>
        </el-form-item>
        <el-form-item label="单价(元)" prop="price">
          <el-input v-model="newData.price"></el-input>
        </el-form-item>
        <el-form-item label="商品分类" prop="classify">
          <el-input v-model="newData.classify"></el-input>
        </el-form-item>
        <el-form-item label="是否上架" prop="isputaway">
          <span>{{goodData.isputaway? '是' : '否'}}</span>
        </el-form-item>
        <el-form-item label="商品描述" prop="miaoshu">
          <el-input type="textarea" v-model="newData.miaoshu"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('newData')">保存修改</el-button>
          <el-button @click="resetForm('newData')">重置</el-button>
          <el-button type="danger" @click="del(newData.sid)">删除商品</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { getRequst, postRequst } from '../../../uilts/api'
export default {
  name: 'goodUpdata',
  data () {
    return {
      goodData: '',
      sjtoken: sessionStorage.getItem('sjtoken'),
      newData: {
        sid: '',
        describe: '',
        price: '',
        classify: '',
        miaoshu: ''
      },
      rules: {
        describe: [
          { required: true, message: '请输入商品名称', trigger: 'blur' },
          { min: 3, message: '长度至少 3 个字符', trigger: 'blur' }
        ],
        price: [
          { required: true, message: '请输入商品单价', trigger: 'blur' }
        ],
        classify: [
          { required: true, message: '请输入商品分类', trigger: 'blur' }
        ],
        isputaway: [
          { required: false, message: '请填写商品描述', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    this.getData()
  },
  methods: {
    async getData () {
      await getRequst('/sjgoodes', { sid: this.$route.query.sid }).then((res) => {
        this.goodData = res
        this.newData.sid = res.sid
        this.newData.describe = res.describe
        this.newData.price = res.price
        this.newData.classify = res.classify
        this.newData.miaoshu = res.miaoshu
        console.log(res)
      })
    },
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          postRequst('/UpGood', this.newData, this.sjtoken)
        } else {
          console.log('保存失败!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    async del (sid) {
      await getRequst('/delGood', { sid: sid })
      console.log(sid)
      setTimeout(() => {
        this.$router.replace('/storehome')
      }, 200)
    }
  }
}
</script>

<style scoped>
.UpdataGood .img {
  width: 300px;
  margin: 0 auto;
}
.UpdataGood .img img {
  width: 100%;
  object-fit: cover;
  object-position: center;
}
.form {
  width: 600px;
  margin: 0 auto;
}
.form form {
  display: flex;
  flex-direction: column;
}
</style>
