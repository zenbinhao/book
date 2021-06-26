<template>
  <div>
    <p>图书管理/进购图书审核信息管理</p>
    <p>&nbsp;</p>
    <el-button type="success" size="mini" @click="addBuy">添加</el-button>
    <el-button type="danger" size="mini" @click="deleteBuy">批量删除</el-button>
    <el-table
      :data="buyData"
      style="width: 100%"
      @selection-change="getIds">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        label="id"
        prop="examine_id"
        width="100"
        align="center">
      </el-table-column>
      <el-table-column
        label="进购图书名称"
        width="150"
        align="center">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>图书名称: {{ scope.row.purchase_book }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.purchase_book }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="书籍状态"
        width="120" align="center" show-overflow-tooltip>
        <template slot-scope="scope">
          <p style="color: #CF9236;" v-if="scope.row.examine_state==0">{{stateList[scope.row.examine_state]}}</p>
          <p style="color: #0000FF;" v-if="scope.row.examine_state==1">{{stateList[scope.row.examine_state]}}</p>
          <p style="color: #00FF00;" v-if="scope.row.examine_state==2">{{stateList[scope.row.examine_state]}}</p>
          <p style="color: red;" v-if="scope.row.examine_state==3">{{stateList[scope.row.examine_state]}}</p>
        </template>
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
            @keydown.enter.native="searchBy"
            />
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="update(scope.row.examine_id,scope.row.version)">Edit</el-button>
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
      title="图书进购审核新增"
      :visible.sync="addBuyShow"
      direction="ltr"
      size="40%"
      >
      <el-form class="addBuyform" :rules="rules" ref="addBuyForm" :model="add">
        <el-form-item label="进购图书名" prop="purchase_book">
          <el-input placeholder="请填写进购图书名" v-model="add.purchase_book"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addSubmitBuy('addBuyForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
    <el-drawer
      title="图书进购审核修改"
      :visible.sync="updateBuyShow"
      direction="rtl"
      size="40%"
      >
      <el-form class="addBuyform" :rules="rules" ref="updateBuyForm" :model="updateData" :data="updateData">
        <el-form-item label="进购图书名" prop="purchase_book">
          <el-input placeholder="请填写进购图书名" v-model="updateData.purchase_book"></el-input>
        </el-form-item>
        <el-form-item label="性别" >
          <el-radio-group v-model="updateData.examine_state">
            <el-radio :label="0" >未处理</el-radio>
            <el-radio :label="1">未通过</el-radio>
            <el-radio :label="2">已通过</el-radio>
            <el-radio :label="3">已删除</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateSubmitBuy('updateBuyForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </div>
</template>

<script>
import qs from 'qs'
import axios from 'axios'
import { Loading } from 'element-ui'
export default {
  data () {
    return {
      currPage: 1,
      pageSize: 10,
      total: null,
      search: null,
      buyData: [],
      stateList: ['未处理', '未通过', '已通过', '已删除'],
      addBuyShow: false,
      rules: {
        purchase_book: [
          {required: true, message: '请输入进购图书名', tigger: 'blur'}
        ]
      },
      add: {
        purchase_book: null
      },
      updateData: {},
      updateToken: {},
      updateBuyShow: false
    }
  },
  components: {

  },
  methods: {
    handleSizeChange (val) {
      this.pageSize = val
      this.currPage = 1
      this.findAllBuy()
    },
    handleCurrentChange (val) {
      this.currPage = val
      this.findAllBuy()
    },
    findAllBuy () {
      let params = {
        purchase_book: this.search,
        curr_page: this.currPage,
        page_size: this.pageSize
      }
      axios.get('book/examine/selectPageExamine', {params}).then((res) => {
        this.buyData = res.data.data.list
        this.total = res.data.data.total
        this.pageSize = res.data.data.pageSize
        this.currPage = res.data.data.pageNum
        console.log(this.buyData)
      })
    },
    deleteBuy () {
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
        axios.post('book/examine/deleteExamine', params).then((res) => {
          this.$message({
            type: 'success',
            message: res.data.message
          })
          this.currPage = 1
          this.findAllBuy()
        })
      })
    },
    getIds (buyData) {
      this.ids = ''
      buyData.forEach((item, index) => {
        this.ids += item.examine_id + ','
      })
      console.log(this.ids)
    },
    addBuy () {
      this.addBuyShow = true
      this.add.purchase_book = null
    },
    addSubmitBuy (refName) {
      this.$refs[refName].validate((valid) => {
        if (valid) {
          // 校验通过
          let params = {
            purchase_book: this.add.purchase_book
          }
          params = qs.stringify(params)
          axios.post('book/examine/insertExamine', params).then((res) => {
            this.$message({
              type: 'success',
              message: res.data.message
            })
            this.addBuyShow = false
            this.currPage = 1
            this.findAllBuy()
          })
        } else {
          // 校验不通过
          return false
        }
      })
    },
    searchBy () {
      this.currPage = 1
      this.findAllBuy()
    },
    update (id, version) {
      console.log(id)
      this.findBuyById(id)
      this.updateBuyShow = true
      this.updateToken = {
        id: id,
        version: version
      }
    },
    findBuyById (id) {
      let params = {
        id: id
      }
      axios.get('book/examine/selectExamineById', {params}).then((res) => {
        this.updateData = res.data.data
        console.log(this.updateData)
      })
    },
    updateSubmitBuy (refName) {
      this.$refs[refName].validate((valid) => {
        if (valid) {
          // 校验通过
          let params = {
            purchase_book: this.updateData.purchase_book,
            examine_state: this.updateData.examine_state,
            examine_id: this.updateToken.id,
            version: this.updateToken.version
          }
          params = qs.stringify(params)
          axios.post('book/examine/updateExamine', params).then((res) => {
            this.$message({
              type: 'success',
              message: res.data.message
            })
            this.updateBuyShow = false
            this.currPage = 1
            this.findAllBuy()
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
    this.findAllBuy()
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
  .addBuyform{
    margin-left: 30px;
    height: 680px;
    overflow-y: scroll;
    position: relative;
    overflow-x: hidden;
  }
</style>
