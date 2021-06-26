<template>
  <div class="booksController" v-loading="loading"
    element-loading-text="拼命加载中"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)">
    <p>图书管理/图书信息管理页面</p>
    <p>&nbsp;</p>
    <el-button type="success" size="mini" @click="addBook">添加</el-button>
    <el-button type="danger" size="mini" @click="deleteBooks">批量删除</el-button>
    <el-table
      :data="booksData"
      style="width: 100%"
      @selection-change="getIds">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        label="id"
        prop="book_id"
        width="50">
      </el-table-column>
      <el-table-column
        label="图书名称"
        width="100"
        align="center">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>图书名称: {{ scope.row.book_title }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.book_title }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="书籍简介"
        prop="introduce"
        width="120" align="center" show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="作者"
        prop="author"
        align="center"
        width="100">
      </el-table-column>
      <el-table-column
        label="点赞数"
        prop="stock"
        width="70">
      </el-table-column>
      <el-table-column
        label="数量"
        prop="count"
        width="70"
        align="center">
      </el-table-column>
      <el-table-column
        label="分类id" width="80" align="center">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.category_id}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="创建信息"
        width="150">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>创建时间: {{ scope.row.create_time | formatDate}}</p>
            <p>创建人id: {{ scope.row.create_user_id}}</p>
            <p>创建人姓名: {{ scope.row.create_user_name}}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">碰我看</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="创建信息"
        width="150">
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
            @keydown.enter.native="searchBooks"
            />
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="update(scope.row.book_id,scope.row.version)">Edit</el-button>
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
      title="图书新增"
      :visible.sync="addBookShow"
      direction="ltr"
      size="40%"
      >
      <el-form class="addBooksform" :rules="rules" ref="addBookForm" :model="add">
        <el-form-item label="图书名" prop="book_title">
          <el-input placeholder="请填写图书名" v-model="add.book_title"></el-input>
        </el-form-item>
        <el-form-item label="简介" prop="introduce">
          <el-input placeholder="请填写简介" v-model="add.introduce"></el-input>
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input placeholder="请填写作者" v-model="add.author"></el-input>
        </el-form-item>
       <el-form-item label="图书图片:" prop="book_picture">
          <el-upload
            class="avatar-uploader"
            action="book/books/imgupload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            name="picture">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <div class="pictureDisable">
            <input v-model="add.book_picture" type="text"/>
          </div>
        </el-form-item>
        <el-form-item label="点赞数" prop="stock">
          <el-input placeholder="请填写点赞数" maxlength="9" v-model="add.stock"></el-input>
        </el-form-item>
        <el-form-item label="数量" prop="count">
          <el-input placeholder="请填写数量" maxlength="9" v-model="add.count"></el-input>
        </el-form-item>
        <el-form-item label="分类id" prop="category_id">
          <el-input placeholder="请填写分类id" maxlength="5" v-model="add.category_id"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addSubmitBook('addBookForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>

    <el-drawer
      title="图书修改"
      :visible.sync="updateBookShow"
      direction="rtl"
      size="40%"
      >
      <el-form class="addBooksform" :rules="rules" ref="updateBookForm" :model="updateData" :data="updateData">
        <el-form-item label="图书名" prop="book_title">
          <el-input placeholder="请填写图书名" v-model="updateData.book_title"></el-input>
        </el-form-item>
        <el-form-item label="简介" prop="introduce">
          <el-input placeholder="请填写简介" v-model="updateData.introduce"></el-input>
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input placeholder="请填写作者" v-model="updateData.author"></el-input>
        </el-form-item>
       <el-form-item label="图书图片:" prop="book_picture">
          <el-upload
            class="avatar-uploader"
            action="book/books/imgupload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            name="picture">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <div class="pictureDisable">
            <input v-model="updateData.book_picture" type="text"/>
          </div>
        </el-form-item>
        <el-form-item label="点赞数" prop="stock">
          <el-input placeholder="请填写点赞数" maxlength="9" v-model="updateData.stock"></el-input>
        </el-form-item>
        <el-form-item label="数量" prop="count">
          <el-input placeholder="请填写数量" maxlength="9" v-model="updateData.count"></el-input>
        </el-form-item>
        <el-form-item label="分类id" prop="category_id">
          <el-input placeholder="请填写分类id" maxlength="5" v-model="updateData.category_id"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateSubmitBook('updateBookForm')">提交</el-button>
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
      booksData: [],
      currPage: 1,
      pageSize: 10,
      total: null,
      search: null,
      updateBookShow: false,
      addBookShow: false,
      bookById: {},
      imageUrl: '',
      img: '',
      rules: {
        book_title: [
          {required: true, message: '请输入图书名', tigger: 'blur'}
        ],
        introduce: [
          {required: true, message: '请输入图书简介', tigger: 'blur'}
        ],
        author: [
          {required: true, message: '请输入作者名', tigger: 'blur'}
        ],
        count: [
          {required: true, message: '请输入图书数量'}
        ],
        stock: [
          {required: true, message: '请输入点赞数'}
        ],
        category_id: [
          {required: true, message: '请输入分类id'}
        ],
        book_picture: [
          {required: true, message: '选择上传图片', tigger: 'blur'}
        ]
      },
      add: {
        book_title: '',
        introduce: '',
        author: '',
        book_picture: '',
        stock: '',
        count: '',
        category_id: ''
      },
      updateData: {},
      updateToken: {
        id: '',
        version: ''
      },
      loading: false
    }
  },
  components: {

  },
  methods: {
    handleSizeChange (val) {
      this.pageSize = val
      this.currPage = 1
      this.findAllBooks()
    },
    handleCurrentChange (val) {
      this.currPage = val
      this.findAllBooks()
    },
    findAllBooks () {
      this.loading = true
      let params = {
        title: this.search,
        curr_page: this.currPage,
        page_size: this.pageSize
      }
      axios.get('book/books/selectPageBooks', {params}).then((res) => {
        this.booksData = res.data.data.list
        this.total = res.data.data.total
        this.pageSize = res.data.data.pageSize
        this.currPage = res.data.data.pageNum
        console.log(this.booksData)
      })
      this.loading = false
    },
    deleteBooks () {
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
        axios.post('book/books/deleteBooks', params).then((res) => {
          this.$message({
            type: 'success',
            message: res.data.message
          })
          this.currPage = 1
          this.findAllBooks()
        })
      })
    },
    getIds (booksData) {
      this.ids = ''
      booksData.forEach((item, index) => {
        this.ids += item.book_id + ','
      })
      console.log(this.ids)
    },
    searchBooks () {
      this.currPage = 1
      this.findAllBooks()
    },
    addBook () {
      this.addBookShow = true
      this.add = {
        book_title: '',
        introduce: '',
        author: '',
        book_picture: '',
        stock: '',
        count: '',
        category_id: ''
      }
      this.imageUrl = ''
    },
    beforeAvatarUpload (file) {
      const Format = file.type === 'image/jpeg' || file.type === 'image/jpg' || file.type === 'image/jpg' || file.type === 'image/gif'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!Format) {
        this.$message.error('上传头像图片只能是 JPG或者PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return Format && isLt2M
    },
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
      this.imgUrl = 'http://localhost:8080/book' + res.data
      this.add.book_picture = this.imgUrl
      this.updateData.book_picture = this.imgUrl
      console.log(this.imgUrl)
    },
    addSubmitBook (refName) {
      this.$refs[refName].validate((valid) => {
        if (valid) {
          // 校验通过
          let params = {
            book_title: this.add.book_title,
            introduce: this.add.introduce,
            author: this.add.author,
            book_picture: this.add.book_picture,
            stock: this.add.stock,
            count: this.add.count,
            category_id: this.add.category_id
          }
          params = qs.stringify(params)
          axios.post('book/books/insertBooks', params).then((res) => {
            this.$message({
              type: 'success',
              message: res.data.message
            })
            this.addBookShow = false
            this.findAllBooks()
          })
        } else {
          // 校验不通过
          return false
        }
      })
    },
    update (id, version) {
      console.log(id)
      this.findBookById(id)
      this.updateBookShow = true
      this.updateToken = {
        id: id,
        version: version
      }
    },
    findBookById (id) {
      let params = {
        id: id
      }
      axios.get('book/books/selectBooksById', {params}).then((res) => {
        this.updateData = res.data.data
        console.log(this.updateData)
        this.imageUrl = res.data.data.book_picture
        console.log(this.imageUrl)
      })
    },
    updateSubmitBook (refName) {
      this.$refs[refName].validate((valid) => {
        if (valid) {
          // 校验通过
          let params = {
            book_id: this.updateToken.id,
            version: this.updateToken.version,
            book_title: this.updateData.book_title,
            introduce: this.updateData.introduce,
            author: this.updateData.author,
            book_picture: this.updateData.book_picture,
            stock: this.updateData.stock,
            count: this.updateData.count,
            category_id: this.updateData.category_id
          }
          params = qs.stringify(params)
          axios.post('book/books/updateBooks', params).then((res) => {
            this.$message({
              type: 'success',
              message: res.data.message
            })
            this.updateBookShow = false
            this.findAllBooks()
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
    this.findAllBooks()
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
  .addBooksform{
    margin-left: 30px;
    height: 680px;
    overflow-y: scroll;
    position: relative;
    overflow-x: hidden;
  }
    .avatar-uploader .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
      border-color: #409EFF;
    }
    .avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 178px;
      height: 178px;
      line-height: 178px;
      text-align: center;
    }
    .avatar {
      width: 178px;
      height: 178px;
      display: block;
    }
    .pictureDisable{
      position: absolute;
      top: 6rem;
      right: 0rem;
      width: 0%;
      input{
        background-color: white;
        color: white;
        border: none;
      }
    }
</style>
