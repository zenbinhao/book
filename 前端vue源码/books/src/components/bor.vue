<template>
  <div>
    <p>图书管理/借书还书记录管理</p>
    <p>&nbsp;</p>
    <el-button type="danger" size="mini" @click="deleteBor">批量删除</el-button>
    <el-table
      :data="BorData"
      style="width: 100%"
      @selection-change="getIds">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        label="id"
        prop="record_id"
        width="50">
      </el-table-column>
      <el-table-column
        label="借书人姓名"
        width="100"
        align="center">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>id: {{ scope.row.user_id }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.user_name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="图书的id"
        prop="book_id"
        width="100"
        align="center">
      </el-table-column>
      <el-table-column
        label="借的图书名称"
        prop="book_title"
        width="120" align="center" show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="是否归还"
        align="center"
        width="100">
        <template slot-scope="scope">
          <p v-if="scope.row.book_state==0" style="color: red;">{{ returnState[scope.row.book_state]}}</p>
          <p style="color: #00FF00;" v-else>{{ returnState[scope.row.book_state]}}</p>
        </template>
      </el-table-column>
      <el-table-column
        label="借书日期"
        width="200">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.borrow_time | formatDate}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="还书管理员信息"
        width="150">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>还书时间: {{ scope.row.return_time | formatDate}}</p>
            <p>还书管理员id: {{ scope.row.update_user_id}}</p>
            <p>还书管理员: {{ scope.row.update_user_name}}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">碰我看信息</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        align="center">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"
            @keydown.enter.native="searchBor"
            />
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            @click="update(scope.row.record_id)" v-if="scope.row.book_state==0" >还书</el-button>
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
import { Loading } from 'element-ui'
import axios from 'axios'
import qs from 'qs'
export default {
  data () {
    return {
      colorList: ['red','yellow'],
      currPage: 1,
      pageSize: 10,
      total: null,
      search: null,
      BorData: [],
      ids: '',
      returnState: ['未归还', '已归还']
    }
  },
  components: {

  },
  methods: {
    handleSizeChange (val) {
      this.pageSize = val
      this.currPage = 1
      this.getAllBor()
    },
    handleCurrentChange (val) {
      this.currPage = val
      this.getAllBor()
    },
    getAllBor () {
      let params = {
        name: this.search,
        curr_page: this.currPage,
        page_size: this.pageSize
      }
      axios.get('book/record/selectPageRecord', {params}).then((res) => {
        this.BorData = res.data.data.list
        this.total = res.data.data.total
        this.pageSize = res.data.data.pageSize
        this.currPage = res.data.data.pageNum
        console.log(this.BorData)
      })
    },
    searchBor () {
      this.currPage = 1
      this.getAllBor()
    },
    update (id) {
      this.$confirm('是否确定还书？', '管理员操作', {
        confirmButtonText: '还书',
        cancelButtonText: '取消'
      }).then(() => {
        let params = {
          record_id: id
        }
        params = qs.stringify(params)
        axios.post('book/record/updateReturnRecord', params).then((res) => {
          this.$message({
            type: 'success',
            message: res.data.message
          })
          this.currPage = 1
          this.getAllBor()
        })
      })
    },
    deleteBor () {
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
        axios.post('book/record/deleteRecord', params).then((res) => {
          this.$message({
            type: 'success',
            message: res.data.message
          })
          this.currPage = 1
          this.getAllBor()
        })
      })
    },
    getIds (BorData) {
      this.ids = ''
      BorData.forEach((item, index) => {
        this.ids += item.record_id + ','
      })
      console.log(this.ids)
    }
  },
  mounted () {
    let loadingInstance = Loading.service({})
    this.getAllBor()
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

<style>

</style>
