<template>
  <div>
    <el-row type="flex">
      <el-col>
    <topNav></topNav>
      </el-col>
    </el-row>
    <el-row type="flex" justify="center">
      <el-col :span="18">
        <div class="personal-box">
          <el-row type="flex">
            <el-col :span="7">
              <div class="personal-box-left">
                <p class="personal-box-left-p">{{personData.user_name}}</p>
                <div class="personal-box-left-div" @click="divSwich=0">
                  <p class="personal-box-left-div-p">我的信息</p>
                </div>
                <div class="personal-box-left-div" @click="divSwich=1">
                  <p class="personal-box-left-div-p">我的借书记录</p>
                </div>
                <div class="personal-box-left-div" @click="divSwich=2">
                  <p class="personal-box-left-div-p">我的收藏</p>
                </div>
              </div>
            </el-col>
            <el-col :span="17">
              <div class="personal-box-right">
                <div class="personal-box-right-person" v-if="divSwich===2">
                  <div class="personal-box-right-person-collect" v-for="obj in collectData" :key="obj.book_id" @click="routerTodetails(obj.book_id)">
                    <img class="personal-box-right-person-collect-img" :src="obj.book_picture">
                    <p class="personal-box-right-person-collect-p">{{obj.book_title}}</p>
                    <p>&nbsp;</p>
                    <p>&nbsp;</p>
                  </div>
                </div>
                <div class="personal-box-right-person" v-if="divSwich===0">
                  <p class="personal-box-right-person-p1">我的id:&nbsp;&nbsp;&nbsp;&nbsp;{{personData.user_id}}</p>
                  <p class="personal-box-right-person-p1">用户昵称:&nbsp;&nbsp;&nbsp;&nbsp;{{personData.user_name}}</p>
                  <p class="personal-box-right-person-p1">账号:&nbsp;&nbsp;&nbsp;&nbsp;{{personData.account}}</p>
                  <p class="personal-box-right-person-p1">密码:&nbsp;&nbsp;&nbsp;&nbsp;******</p>
                  <p class="personal-box-right-person-p1">我的手机号:&nbsp;&nbsp;&nbsp;&nbsp;{{personData.phone}}</p>
                  <p class="personal-box-right-person-p1">我是否有书未归还:&nbsp;&nbsp;&nbsp;&nbsp;
                  <span style="color: red;" v-if="recordState==1">
{{bor[recordState]}}</br></br>注意:&nbsp;&nbsp;&nbsp;&nbsp;不还书不能再次借书的哟
                  </span>
                  <span style="color:#00FF00" v-else>
{{bor[recordState]}}
                  </span>
                  </p>
                </div>
                <div class="personal-box-right-person" v-if="divSwich===1">
                  <el-table
                    :data="recordData"
                    style="width: 100%">
                    <el-table-column
                      prop="book_title"
                      label="书名"
                      width="100">
                    </el-table-column>
                    <el-table-column
                      label="借书日期"
                      width="180">
                      <template slot-scope="scope">
                        {{scope.row.borrow_time |formatDate}}
                      </template>
                    </el-table-column>
                    <el-table-column
                      label="还书日期"
                      width="180">
                      <template slot-scope="scope">
                        {{scope.row.return_time |formatDate}}
                      </template>
                    </el-table-column>
                    <el-table-column
                      label="是否归还"
                      width="200">
                      <template slot-scope="scope">
                        {{bor[scope.row.book_state]}}
                      </template>
                    </el-table-column>
                  </el-table>
                  <el-pagination
                    @current-change="handleCurrentChange"
                    :current-page="currPage"
                    :page-size="pageSize"
                    layout="total, prev, pager, next"
                    :total="total">
                  </el-pagination>
                </div>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { Loading } from 'element-ui'
import axios from 'axios'
import topNav from '../components/topNav.vue'
export default {
  data () {
    return {
      personData: [],
      recordState: null,
      bor: ['否','是'],
      divSwich: 0,
      currPage: 1,
      pageSize: 5,
      total: 0,
      recordData: [],
      collectData: []
    }
  },
  components: {
    topNav
  },
  methods: {
    routerTodetails (id) {
      this.$router.push({name: 'details', query: {id: id}})
    },
    getPersonData () {
      axios.get('book/user/selectPersonById', null).then((res) => {
        this.personData = res.data.data
      })
    },
    getRecordState () {
      axios.get('book/record/selectRecordBookState', null).then((res) => {
        this.recordState = res.data.data
      })
    },
    getPersonRecord () {
      let params = {
        curr_page: this.currPage,
        page_size: this.pageSize
      }
      axios.get('book/record/selectPageRecordById',{params}).then((res) => {
        this.recordData = res.data.data.list
        this.total = res.data.data.total
      })
    },
    handleCurrentChange (val) {
      this.currPage = val
      this.getPersonRecord()
    },
    getCollect () {
      axios.get('book/collect/selectCollectById',null).then((res) => {
        this.collectData = res.data.data.list
      })
    }

  },
  mounted () {
    let loadingInstance = Loading.service({})
    this.getPersonData()
    this.getRecordState()
    this.getPersonRecord()
    this.getCollect()
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
.personal-box{
  width: 100%;
  height: 40rem;
  position: relative;
  top: 7rem;
  box-shadow: 1px 1px 10px #c9c9c9;
  .personal-box-left{
    position: relative;
    height: 40rem;
    width: 100%;
    background: #eaeaea;
    .personal-box-left-p{
      position: relative;
      top: 3rem;
      font-weight: 500;
      font-size: 1.5rem;
      text-align: center;
    }
    .personal-box-left-div{
      position: relative;
      line-height: 4rem;
      margin-top: 1rem;
      top: 5.5rem;
      height: 4rem;
      transition: background-color 2s;
      &:hover{
        background-color: #00FF00;
        transition: background-color 2s;
        cursor: pointer;
      }
      &:active{
        background-color: burlywood;
        transition: background-color;
      }
      // background-color: yellow;
      .personal-box-left-div-p{
        font-size: 1rem;
        text-align: center;
      }
    }
  }
  .personal-box-right{
    position: relative;
    height: 40rem;
    .personal-box-right-person{
      overflow: auto;
      position: relative;
      height: 34rem;
      width: 91%;
      margin:3rem;
      .personal-box-right-person-p1{
        margin-bottom: 2rem;
        font-size: 1.3rem;
      }
      .personal-box-right-person-collect{
        cursor: pointer;
        margin-left: 3.5rem;
        display: inline-block;
        height: 45%;
        width: 25%;
        .personal-box-right-person-collect-img{
          width: 100%;
          height: 100%;
        }
        .personal-box-right-person-collect-p{
          padding-top: 1rem;
          text-align: center;
        }
      }
    }
  }
}
</style>
