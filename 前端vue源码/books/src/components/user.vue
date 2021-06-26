<template>
  <div class="user">
    <p>用户管理/用户信息管理页面</p>
    <p>&nbsp;</p>
      <el-button type="danger" size="mini" @click="deleteUser">批量删除</el-button>
      <el-table
        :data="tableData"
        style="width: 100%"
        @selection-change="getIds">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          label="id"
          prop="user_id"
          width="100">
        </el-table-column>
        <el-table-column
          label="姓名"
          width="150">
          <template slot-scope="scope">
            <el-popover trigger="hover" placement="top">
              <p>姓名: {{ scope.row.user_name }}</p>
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.user_name }}</el-tag>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
          label="account"
          prop="account">
        </el-table-column>
        <el-table-column
          label="phone"
          prop="phone">
        </el-table-column>
        <el-table-column
          label="state">
          <template slot-scope="scope">
            <i class="el-icon-user-solid"></i>
            <span style="margin-left: 10px">{{ userState[scope.row.state]}}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="注册日期"
          width="200">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.create_time | formatDate}}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot="header" slot-scope="scope">
            <el-input
              v-model="search"
              size="mini"
              placeholder="输入关键字搜索"
              @keydown.enter.native="searchUser"
              />
          </template>
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.row.user_id,scope.row.version)">Edit</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currPage"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
      <el-drawer
        title="用户新增"
        :visible.sync="isShow"
        direction="ltr"
        size="30%"
        >
        <el-form class="updateUserForm" :rules="rules" ref="updateForm" :model="user">
          <el-form-item label="账号" prop="account">
            <el-input placeholder="请填写账号" maxlength="11" v-model="user.account" disabled></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="user_name">
            <el-input placeholder="请填写姓名" v-model="user.user_name"></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="phone">
            <el-input placeholder="请填写手机号" maxlength="11" v-model="user.phone" disabled></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input placeholder="请填写密码" maxlength="11" v-model="user.password"></el-input>
          </el-form-item>
          <el-form-item label="用户状态" >
            <el-radio-group v-model="user.state">
              <el-radio :label="0" >正常</el-radio>
              <el-radio :label="1">封禁</el-radio>
              <el-radio :label="2">管理员</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item class="fr">
            <el-button type="primary" @click="update('updateForm')">提交</el-button>
          </el-form-item>
        </el-form>
      </el-drawer>
  </div>
</template>

<script>
import qs from 'qs'
import { Loading } from 'element-ui'
import axios from 'axios'
export default {
  data () {
    let checkPhone = (rule, value, callback) => {
      let phoneReg = /^1[3456789]\d{9}$/
      if (!phoneReg.test(value)) {
        return callback(new Error('账号必须为手机号格式'))
      }
      callback()
    }
    return {
      tableData: [],
      currPage: 1,
      pageSize: 10,
      total: null,
      search: null,
      userState: ['正常', '封禁', '管理员'],
      isShow: false,
      updateById: null,
      updateByVersion: null,
      user: {},
      rules: {
        account: [
          {required: true, message: '请输入用户名', tigger: 'blur'}
        ],
        phone: [
          {required: true, message: '请输入手机号', tigger: 'blur'},
          {validator: checkPhone, tigger: 'change'}
        ],
        user_name: [
          {required: true, message: '请输入姓名', tigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', tigger: 'blur'}
        ]
      },
      ids: ''
    }
  },
  components: {

  },
  methods: {
    searchUser () {
      this.findUser()
    },
    handleEdit (id, version) {
      this.updateById = id
      this.findUserById(id)
      this.updateByVersion = version
      this.isShow = true
    },
    handleSizeChange (val) {
      this.pageSize = val
      this.findUser()
    },
    handleCurrentChange (val) {
      this.currPage = val
      this.findUser()
    },
    findUser () {
      let params = {
        user_name: this.search,
        curr_page: this.currPage,
        page_size: this.pageSize
      }
      axios.get('book/user/selectPageUser', {params}).then((res) => {
        this.tableData = res.data.data.list
        this.total = res.data.data.total
        this.pageSize = res.data.data.pageSize
        this.currPage = res.data.data.pageNum
      })
    },
    findUserById (id) {
      let params = {
        id: id
      }
      axios.get('book/user/selectById', {params}).then((res) => {
        this.user = res.data.data
        console.log(this.user)
      })
    },
    update (refName) {
      this.$refs[refName].validate((valid) => {
        if (valid) {
          let params = {
            user_id: this.user.user_id,
            version: this.user.version,
            user_name: this.user.user_name,
            password: this.user.password,
            state: this.user.state
          }
          // post请求转数据格式
          params = qs.stringify(params)
          // 发送数据给服务器，实现修改
          axios.post('book/user/updateUser', params).then((res) => {
            this.$message({
              type: 'success',
              message: res.data.message
            })
            this.isShow = false
            // 刷新表格数据
            this.findUser()
          })
        } else {
          return false
        }
      })
    },
    getIds (tableData) {
      this.ids = ''
      tableData.forEach((item, index) => {
        this.ids += item.user_id + ','
      })
    },
    deleteUser () {
      this.$confirm('是否确定删除当前记录？', '确定删除？', {
        confirmButtonText: '删除',
        cancelButtonText: '取消'
      }).then(() => {
        // 发送 删除请求
        let ids = this.ids.substring(0, this.ids.length - 1)
        let params = {
          ids: ids
        }
        params = qs.stringify(params)
        axios.post('book/user/deleteUser', params).then((res) => {
          this.$message({
            type: 'success',
            message: res.data.message
          })
          this.findUser()
        })
      })
    }
  },
  mounted () {
    let loadingInstance = Loading.service({})
    this.findUser()
    setTimeout(() => {
      loadingInstance.close()
    }, 1500)
  },
  filters: {
  // 时间戳转换
    formatDate (obj) {
      if (obj === null | obj === '') {
        return '无'
      }
      // eslint-disable-next-line no-multi-spaces
      return  new Date(obj).toLocaleString().replace(/:\d{1,2}$/, ' ')
    }
  }
}
</script>

<style lang="scss">
@import '@/assets/style/common.scss';
.updateUserForm{
  margin-left: 30px;
  width: 80%;
  height: 680px;
}
</style>
