<template>
  <div class="shopUp-box">
    <div class="uploadForm">
      <div>
        <span>商品图片：</span>
        <el-upload
          action="http://localhost:8082/uploadShop"
          list-type="picture-card"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove"
          :auto-upload="false"
          :data="resData"
          :on-success="onSuccess1"
          ref="upload"
        >
          <i class="el-icon-plus"></i>
        </el-upload>
      </div>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt />
      </el-dialog>
      <div>
        <span>商品描述：</span>
        <el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="resData.shopName"></el-input>
      </div>
      <div>
        <span>商品单价：</span>
        <el-input v-model="resData.shopPrice" placeholder="请输入内容"></el-input>
      </div>
    </div>
    <div class="Btn">
      <el-button type="primary" @click="submit2">提交</el-button>
    </div>
  </div>
</template>

<script>
// import { postRequst } from '../../../uilts/api'
export default {
  data () {
    return {
      file: '',
      dialogImageUrl: '',
      dialogVisible: false,
      imgUrl: '',
      userId: '',
      uid: '',

      // 上传图片时附带的额外参数userId
      resData: {
        sjId: 1,
        shopName: '',
        shopPrice: 0
      }
    }
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

    // 点击保存按钮上传图片
    submit2: function (res) {
      if (this.resData.shopName === '') {
        this.$message({
          type: 'error',
          message: '商品描述不能为空'
        })
      } else if (this.resData.shopName === 0) {
        this.$message({
          type: 'error',
          message: '请输入正确的价格'
        })
      }
      this.$refs.upload.submit()
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
.uploadForm > div:nth-child(4) > span {
  width: 98px;
}
.Btn {
  text-align: center;
}
</style>
