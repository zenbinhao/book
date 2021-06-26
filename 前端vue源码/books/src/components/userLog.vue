<template>
  <div>
    <p>用户管理/日志页面</p>
    <p>&nbsp;</p>
    <el-table
      :data="userLogData"
      style="width: 100%">
      <el-table-column
        label="日志id"
        prop="loginLog_id"
        width="100"
        align="center">
      </el-table-column>
      <el-table-column
        label="用户id"
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
        prop="user_account">
      </el-table-column>
      <el-table-column
        label="ip地址"
        prop="ip">
      </el-table-column>
      <el-table-column
        label="登录时间"
        width="200">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.login_date | formatDate}}</span>
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
  </div>
</template>

<script>
import axios from 'axios'
import { Loading } from 'element-ui'
export default {
  data () {
    return {
      userLogData: [],
      currPage: 1,
      pageSize: 10,
      total: null,
      search: null
    }
  },
  components: {

  },
  methods: {
    searchUser () {
      this.getLoginLogData()
    },
    handleSizeChange (val) {
      this.pageSize = val
      this.currPage = 1
      this.getLoginLogData()
    },
    handleCurrentChange (val) {
      this.currPage = val
      this.getLoginLogData()
    },
    getLoginLogData () {
      let params = {
        user_name: this.search,
        curr_page: this.currPage,
        page_size: this.pageSize
      }
      axios.get('book/loginLog/selectPageLoginLog', {params}).then((res) => {
        this.userLogData = res.data.data.list
        this.total = res.data.data.total
        this.pageSize = res.data.data.pageSize
        this.currPage = res.data.data.pageNum
      })
    }
  },
  mounted () {
    let loadingInstance = Loading.service({})
    this.getLoginLogData()
    setTimeout(() => {
      loadingInstance.close()
    }, 1500)
  },
  filters: {
  // 时间戳转换
    formatDate (obj) {
      // eslint-disable-next-line no-multi-spaces
      return  new Date(obj).toLocaleString().replace(/:\d{1,2}$/, ' ')
    }
  }
}
</script>

<style lang="scss">
@import '@/assets/style/common.scss';
</style>
