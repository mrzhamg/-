<template>
  <div class="shopUp-box">
    <div class="uploadForm">
      <div>
        <span>商品图片：</span>
        <el-upload action="http://localhost:8082/uploadShop"
                   list-type="picture-card"
                   :on-preview="handlePictureCardPreview"
                   :on-remove="handleRemove"
                   :auto-upload="false"
                   :data="resData"
                   :on-success="onSuccess1"
                   ref="upload">
          <i class="el-icon-plus"></i>
        </el-upload>
      </div>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%"
             :src="dialogImageUrl"
             alt />
      </el-dialog>
      <div>
        <span>商品名称：</span>
        <el-input type="text"
                  :rows="2"
                  placeholder="请输入名称"
                  v-model="resData.shopName"></el-input>
      </div>

      <div>
        <span>商品单价：</span>
        <el-input v-model="resData.shopPrice"
                  placeholder="请输入内容"></el-input>
      </div>
      <div>
        <span>商品分类：</span>
        <el-select v-model="isClassify"
                   placeholder="请选择"
                   @change="upselect">
          <el-option v-for="item in classify"
                     :key="item.index"
                     :label="item.name"
                     :value="item.name">
          </el-option>
        </el-select>
        <el-button type="primary"
                   @click="dialogFormVisible1 = true">添加分类</el-button>
      </div>
      <div>
        <span>商品描述：</span>
        <el-input type="textarea"
                  :rows="2"
                  placeholder="请输入内容"
                  v-model="resData.miaoshu"></el-input>
      </div>
    </div>
    <div class="Btn">
      <el-button type="primary"
                 @click="submit2">提交</el-button>
    </div>
    <el-dialog title="添加分类"
               :visible.sync="dialogFormVisible1">
      <el-form :model="form">
        <el-form-item label="分类名称"
                      :label-width="formLabelWidth">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary"
                   @click="tianjia">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getRequst, postRequst } from '../../../uilts/api'
export default {
  data () {
    return {
      file: '',
      dialogImageUrl: '',
      dialogVisible: false,
      dialogFormVisible1: false,
      imgUrl: '',
      userId: '',
      uid: '',

      form: {
        name: ''
      },
      formLabelWidth: '120px',

      classify: [],
      isClassify: '',

      // 上传图片时附带的额外参数userId
      resData: {
        sjtoken: sessionStorage.getItem('sjtoken'),
        shopName: '',
        shopPrice: 0,
        isClassify: this.isClassify,
        miaoshu: ''
      }
    }
  },
  created () {
    this.getClassify()
  },
  methods: {
    handleRemove (file, fileList) {
      console.log(file, fileList)
    },
    handlePictureCardPreview (file) {
      console.log(file)
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },

    async getClassify () {
      this.classify = await getRequst('/getClassify')
    },

    upselect () {
      this.resData.isClassify = this.isClassify
    },
    // 添加分类
    async tianjia () {
      if (this.form.name === '') {
        this.$message.error('分类名称不能为空！！！')
      } else {
        console.log(this.form.name)
        await postRequst('/insertClassify', { name: this.form.name })
        this.dialogFormVisible1 = false
      }
    },

    // 点击保存按钮上传图片
    submit2: function (res) {
      if (this.resData.shopName === '') {
        this.$message({
          type: 'error',
          message: '商品名称不能为空'
        })
      } else if (this.resData.shopName === 0) {
        this.$message({
          type: 'error',
          message: '请输入正确的价格'
        })
      } else if (this.resData.isClassify === '') {
        this.$message({
          type: 'error',
          message: '请输选择分类'
        })
      } else {
        console.log('上传=>', this.resData)
        console.log('上传成功')
        this.$refs.upload.submit()
        this.$router.replace('/storehome')
        setTimeout(function () { window.location.reload() }, 200)
      }
    },

    // 图片上传成功后，后台返回图片的路径
    onSuccess1: function (res) {
      if (res.code === 200) {
        console.log(res)
        this.$message({
          type: 'success',
          message: res.message
        })
      } else {
        this.$message({
          type: 'error',
          message: '商品上传失败！！！'
        })
      }
    }
  }
}
</script>

<style>
.shopUp-box {
  /* background-color: aqua; */
}
.uploadForm {
  width: 450px;
  margin: 0 auto;
}
.uploadForm > div:nth-child(1),
.uploadForm > div:nth-child(3),
.uploadForm > div:nth-child(4) {
  margin: 10px 0;
  display: flex;
}
.uploadForm > div:nth-child(3) > span,
.uploadForm > div:nth-child(4) > span,
.uploadForm > div:nth-child(6) > span {
  width: 98px;
}
.uploadForm > div:nth-child(6) {
  display: flex;
  margin-top: 10px;
}
.Btn {
  text-align: center;
  margin-top: 50px;
}
</style>
