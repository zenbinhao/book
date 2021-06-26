<template>
  <div class="categoryController">
    <p>图书管理/图书分类管理页面</p>
    <el-button type="success" size="mini" @click="addBookCategory">添加</el-button>
    <el-button type="danger" size="mini" @click="deleteBooksCategory">批量删除</el-button>
    <el-table
      :data="categoryData"
      style="width: 100%"
      @selection-change="getIds">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        label="id"
        prop="category_id"
        width="100"
        align="center">
      </el-table-column>
      <el-table-column
        label="图书类别名称"
        width="240"
        align="center" prop="category_name" show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="创建时间及其信息触碰查看"
        width="300"
        align="center">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>创建时间: {{ scope.row.create_time | formatDate}}</p>
            <p>创建人id: {{ scope.row.create_user_id}}</p>
            <p>创建人姓名: {{ scope.row.create_user_name}}</p>
            <div slot="reference" class="name-wrapper">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px">{{ scope.row.create_time | formatDate}}</span>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="修改人及时间信息触碰查看"
        width="300"
        align="center">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>修改时间: {{ scope.row.update_time | formatDate}}</p>
            <p>修改人id: {{ scope.row.update_user_id}}</p>
            <p>修改人姓名: {{ scope.row.update_user_name}}</p>
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
            @keydown.enter.native="searchCategory"
            />
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="update(scope.row.category_id,scope.row.version)">Edit</el-button>
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
      title="图书类别新增"
      :visible.sync="addCategoryShow"
      direction="ltr"
      size="40%"
      >
      <el-form class="addCategoryform" :rules="rules" ref="addCategoryForm" :model="add">
        <el-form-item label="图书名图书类别名称" prop="category_name">
          <el-input placeholder="请填写图书类别名" v-model="add.category_name"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addSubmitCategroy('addCategoryForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
    <el-drawer
      title="图书类别修改"
      :visible.sync="updateCategoryShow"
      direction="rtl"
      size="40%"
      >
      <el-form class="addCategoryform" :rules="rules" ref="updateCategoryForm" :model="updateData" :data="updateData">
        <el-form-item label="图书名图书类别名称" prop="category_name">
          <el-input placeholder="请填写图书类别名" v-model="updateData.category_name"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateSubmitCategory('updateCategoryForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </div>
</template>

<script>
import { Loading } from 'element-ui'
import qs from 'qs'
import axios from 'axios'
export default {
  data () {
    return {
      currPage: 1,
      pageSize: 10,
      total: null,
      search: null,
      categoryData: null,
      addCategoryShow: false,
      updateCategoryShow: false,
      rules: {
        category_name: [
          {required: true, message: '请输入图书类别名', tigger: 'blur'}
        ]
      },
      add: {
        category_name: ''
      },
      updateData: {},
      updateToken: {
        id: null,
        version: null
      }
    }
  },
  components: {

  },
  methods: {
    handleSizeChange (val) {
      this.pageSize = val
      this.currPage = 1
      this.getAllCategory()
    },
    handleCurrentChange (val) {
      this.currPage = val
      this.getAllCategory()
    },
    getAllCategory () {
      let params = {
        name: this.search,
        curr_page: this.currPage,
        page_size: this.pageSize
      }
      axios.get('book/category/selectPageCategory', {params}).then((res) => {
        this.categoryData = res.data.data.list
        this.total = res.data.data.total
        this.pageSize = res.data.data.pageSize
        this.currPage = res.data.data.pageNum
        console.log(this.categoryData)
      })
    },
    searchCategory () {
      this.currPage = 1
      this.getAllCategory()
    },
    deleteBooksCategory () {
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
        axios.post('book/category/deleteCategory', params).then((res) => {
          this.$message({
            type: 'success',
            message: res.data.message
          })
          this.currPage = 1
          this.getAllCategory()
        })
      })
    },
    getIds (categoryData) {
      this.ids = ''
      categoryData.forEach((item, index) => {
        this.ids += item.category_id + ','
      })
      console.log(this.ids)
    },
    addBookCategory () {
      this.addCategoryShow = true
      this.add.category_name = null
    },
    addSubmitCategroy (refName) {
      this.$refs[refName].validate((valid) => {
        if (valid) {
          // 校验通过
          let params = {
            category_name: this.add.category_name
          }
          params = qs.stringify(params)
          axios.post('book/category/insertCategory', params).then((res) => {
            this.$message({
              type: 'success',
              message: res.data.message
            })
            this.addCategoryShow = false
            this.getAllCategory()
          })
        } else {
          // 校验不通过
          return false
        }
      })
    },
    update (id, version) {
      console.log(id)
      this.findCategoryById(id)
      this.updateCategoryShow = true
      this.updateToken = {
        id: id,
        version: version
      }
    },
    findCategoryById (id) {
      let params = {
        id: id
      }
      axios.get('book/category/selectCategoryById', {params}).then((res) => {
        this.updateData = res.data.data
        console.log(this.updateData)
      })
    },
    updateSubmitCategory (refName) {
      this.$refs[refName].validate((valid) => {
        if (valid) {
          // 校验通过
          let params = {
            category_name: this.updateData.category_name,
            version: this.updateToken.version,
            category_id: this.updateToken.id
          }
          params = qs.stringify(params)
          axios.post('book/category/updateCategory', params).then((res) => {
            this.$message({
              type: 'success',
              message: res.data.message
            })
            this.updateCategoryShow = false
            this.currPage = 1
            this.getAllCategory()
          })
        } else {
          // 校验不通过
          return false
        }
      })
    }
  },
  mounted () {
    let loadingInstance = Loading.service({})
    this.getAllCategory()
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
  .addCategoryform{
    margin-left: 30px;
    height: 680px;
    width: 80%;
    overflow-y: hidden;
    position: relative;
    overflow-x: hidden;
  }
</style>
