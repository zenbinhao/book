<template>
  <div>
    <p>图书管理/图书评论管理</p>
    <p>&nbsp;</p>
    <el-button type="danger" size="mini" @click="deleteComment">批量删除</el-button>
    <el-table
      :data="commentData"
      style="width: 100%"
      @selection-change="getIds">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        label="id"
        prop="comment_id"
        width="50">
      </el-table-column>
      <el-table-column
        label="评论者"
        width="100"
        align="center">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>用户id: {{ scope.row.user_id }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.user_name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="评论内容"
        prop="content"
        width="200" show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="评论的书籍id"
        prop="book_id"
        align="center"
        width="130">
      </el-table-column>
      <el-table-column
        label="评论时间"
        width="220">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.create_time | formatDate}}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        width="200">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"
            @keydown.enter.native="searchBooks"
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
import { Loading } from 'element-ui'
import qs from 'qs'
import axios from 'axios'
export default {
  data () {
    return {
      commentData: [],
      currPage: 1,
      pageSize: 10,
      total: null,
      search: null,
      ids: ''
    }
  },
  components: {

  },
  methods: {
    handleSizeChange (val) {
      this.pageSize = val
      this.currPage = 1
      this.findAllComment()
    },
    handleCurrentChange (val) {
      this.currPage = val
      this.findAllComment()
    },
    findAllComment () {
      let params = {
        userName: this.search,
        curr_page: this.currPage,
        page_size: this.pageSize
      }
      axios.get('book/comment/selectPageComment', {params}).then((res) => {
        this.commentData = res.data.data.list
        this.total = res.data.data.total
        this.pageSize = res.data.data.pageSize
        this.currPage = res.data.data.pageNum
        console.log(this.commentData)
      })
    },
    searchBooks () {
      this.currPage = 1
      this.findAllComment()
    },
    getIds (commentData) {
      this.ids = ''
      commentData.forEach((item, index) => {
        this.ids += item.comment_id + ','
      })
      console.log(this.ids)
    },
    deleteComment () {
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
        axios.post('book/comment/deleteComment', params).then((res) => {
          this.$message({
            type: 'success',
            message: res.data.message
          })
          this.currPage = 1
          this.findAllComment()
        })
      })
    }
  },
  mounted () {
    let loadingInstance = Loading.service({})
    this.findAllComment()
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

</style>
