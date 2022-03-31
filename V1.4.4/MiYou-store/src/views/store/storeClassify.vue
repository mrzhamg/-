<template>
  <div class="classBox">
    <div class="classBtn">
<!-- Form -->
<el-button type="primary" @click="dialogFormVisible = true">添加分类</el-button>

<el-dialog title="添加分类" :visible.sync="dialogFormVisible">
  <el-form :model="form">
    <el-form-item label="分类名称" :label-width="formLabelWidth">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="tianjia">确 定</el-button>
  </div>
</el-dialog>

<el-dialog title="修改分类" :visible.sync="dialogFormVisible1">
  <el-form :model="form">
    <el-form-item label="分类ID" :label-width="formLabelWidth">
      <el-input v-model="form.id"></el-input>
    </el-form-item>
    <el-form-item label="分类名称" :label-width="formLabelWidth">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible1 = false, form.id = '', form.name = ''">取 消</el-button>
    <el-button type="primary" @click="updataclassify">确 定</el-button>
  </div>
</el-dialog>
    </div>
    <el-table
      :data="classifyList"
      style="width: 100%">
      <el-table-column
        prop="cid"
        label="分类ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="分类名称"
        width="280">
      </el-table-column>
      <el-table-column
        prop=""
        label="操作">
        <template slot-scope="scope">
          <el-button @click="showUpdataDialog(scope.row)" type="primary" icon="el-icon-edit" circle></el-button>
          <el-button @click="DelClick(scope.row)"
                     type="danger" icon="el-icon-delete" circle></el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getRequst, postRequst } from '../../../uilts/api'
export default {
  data () {
    return {
      classifyList: [],
      dialogFormVisible: false,
      dialogFormVisible1: false,
      form: {
        id: '',
        name: ''
      },
      formLabelWidth: '120px'
    }
  },
  created () {
    this.getClassify()
  },
  methods: {
    async getClassify () {
      this.classifyList = await getRequst('/getClassify')
    },
    // 删除分类
    async DelClick (row) {
      console.log(row.cid)
      await getRequst('/delClassify', { cid: row.cid })
      setTimeout(function () { window.location.reload() }, 200)
    },
    async tianjia () {
      if (this.form.name === '') {
        this.$message.error('分类名称不能为空！！！')
      } else {
        console.log(this.form.name)
        await postRequst('/insertClassify', { name: this.form.name })
        this.dialogFormVisible = false
        setTimeout(function () { window.location.reload() }, 200)
      }
    },
    // 修改分类
    showUpdataDialog (row) {
      this.form.id = row.cid
      this.form.name = row.name
      this.dialogFormVisible1 = true
    },
    async updataclassify () {
      const res1 = await postRequst('/updataClassify', this.form)
      console.log(res1)
      if (res1) { setTimeout(function () { window.location.reload() }, 200) }
    }
  }
}
</script>

<style lang="less" scoped>
  .classBox{
    width: 100%;
  }
</style>
