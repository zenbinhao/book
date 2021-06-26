  <template>
  <div class="details-box">
    <el-page-header @back="goBack" content="书籍详情" class="details-box-yt">
    </el-page-header>
    <div class="details-top">
      <div class="details-top-img fl">
      <img :src="details.book_picture">
      </div>
      <!-- <div class="details-top-img-fdj">
      <img src="../assets/img/红楼梦.jpg">
      </div> -->
      <div class="details-top-right fr">
        <div class="details-top-right-content">
          <p class="details-top-right-content-p1">{{details.book_title}}</p>
          <div class="details-top-right-content-zuozhe">
            <p class="details-top-right-content-p2 fl">
              <span>作者:&nbsp;&nbsp;&nbsp;</span>
              <span>{{details.author}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
              <!-- <span>数量:&nbsp;&nbsp;&nbsp;</span> -->
              <!-- <span>{{details.count}}</span> -->
            </p>
            <div class="details-top-right-content-collect" v-if="scDialog" @click="shoucang">
              <img src="http://101.132.237.188:8080/book/img/ysc.png">点我可以收藏哟
            </div>
            <div class="details-top-right-content-collect" v-else @click="quxiaoshoucang">
              <img src="http://101.132.237.188:8080/book/img/wsc.png">已收藏
            </div>
            <div class="details-top-right-content-btn fr" @click="clearEmailFrom()">
              <p>预约借书</p>
            </div>
<!--            <el-dialog
              title="请扫描二维码进行预约"
              :visible.sync="erweimaDialog"
              width="15%">
              <div id="qrcode"></div>
            </el-dialog> -->
            <el-dialog
            title="只有输入正确的邮箱才会发送验证码"
            :visible.sync="emailDialog"
            width="40%">
            <el-form label-width="90px" :rules="rules" :model="emailData" ref="email" label-position="left">
              <el-form-item label="qq邮箱:" class="input" prop="email">
                <el-input v-model="emailData.email">
                  <template slot="append">@qq.com</template>
                </el-input>
              </el-form-item>
              <el-form-item label="验证码:" class="input" prop="code">
                <el-input v-model="emailData.code" clearable>
                      <el-button slot="append" v-if="toCodeDialog" @click="axiosToEmail">发送验证码</el-button>
                      <el-button slot="append" v-else>{{djs}}&nbsp;已发送</el-button>
                </el-input>
              </el-form-item>
                <el-button @click="submitEmail('email')" :disabled="emailSubmitDialog">确定</el-button>
                <el-button @click="cancelButton" class="fr">取消</el-button>
            </el-form>
            </el-dialog>
          </div>
          <span class="details-top-right-content-span1">简介:&nbsp;&nbsp;&nbsp;</span>
          <div class="details-top-right-content-text">
            <span class="details-top-right-content-span2" >{{details.introduce}}</span>
          </div>
          <div class="details-top-right-content-dz fr" @click="dianZan()" v-if="dzDialog">
            <img src="../assets/img/未点赞.png">
            <span>{{details.stock}}</span>
          </div>
          <div class="details-top-right-content-dz fr" v-else>
            <img src="../assets/img/已点赞.png">
            <span>{{details.stock}}</span>
          </div>
        </div>
      </div>
    </div>
    <el-row type="flex">
      <el-col :offset="1" :span="17" class="details-box-pl">
        <div class="details-box-pl-top">
          <p>评论区块</p>
        </div>
        <ul>
          <li>
            <div class="pl-box" v-for="(obj,index) in comment" :key="index">
                <p class="pl-box-p1">评论人:&nbsp;&nbsp;{{obj.user_name}}</p>
                <div style="width: 90%;margin-left: 2rem;margin-top: 0.5rem;">
                  <p v-html="obj.content"></p>
                </div>
            </div>
          </li>
        </ul>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
          <el-pagination
            @current-change="handleCurrentChange"
            :page-size="7"
            layout="total, prev, pager, next"
            :total="total">
          </el-pagination>
          <div id="editor" ref="editorRef">
          </div>
          <el-button type="success" class="fr" @click="dialogVisible=true">提交</el-button>
      </el-col>
    </el-row>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%">
      <span>是否确定评论</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" id="tijiao" @click="submitTheComment()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { Loading } from 'element-ui'
import axios from 'axios'
import qs from 'qs'
import QRCode from 'qrcodejs2'
import E from 'wangeditor'
import emojiJSON from '@/assets/data/emoji.json'
export default {
  data () {
    let checkEmail = (rule, value, callback) => {
      if (value.length < 5) {
        return callback(new Error('请输入正确的QQ邮箱'))
      }
      callback()
    }
    return {
      scDialog: true,
      emailAnswer: '',
      emailSubmitDialog: false,
      dzDialog: true,
      drawer: false,
      textarea: '',
      loading: false,
      erweimaDialog: false,
      erweimaIs: 0,
      details: [],
      comment: [],
      bookId: null,
      total: null,
      emailDialog: false,
      emailData: {
        email: '',
        code: ''
      },
      dialogVisible: false,
      djs: 60,
      toCodeDialog: true,
      rules: {
        email: [
          {required: true, validator: checkEmail, trigger: 'blur'}
        ],
        code: [
          {required: true, message: '验证码', trigger: 'blur'}
        ]
      },
      htmlText: '',
      emoji: [],
      emojiList: emojiJSON,
      isShoucang: true
    }
  },
  components: {

  },
  methods: {
    shifoushoucang () {
      let params = {
        book_id: this.bookId
      }
      axios.get('book/collect/selectIs',{params}).then((res) => {
        if(res.data.data===1){
          this.scDialog = false
        }
      })
    },
    quxiaoshoucang () {
      this.scDialog=true
      let params = {
        book_id: this.bookId
      }
      params = qs.stringify(params)
      axios.post('book/collect/deleteCollect', params).then((res) => {
      })
    },
    shoucang () {
      if (!this.isShoucang) {
        this.$message({
          type: 'warning',
          message: '请登录后再试'
        })
        return false
      }
      this.scDialog=false
      let params = {
        book_id: this.bookId
      }
      params = qs.stringify(params)
      axios.post('book/collect/insertCollect', params).then((res) => {
      })
    },
    submitTheComment () {
      if(this.htmlText===null||this.htmlText===''){
        this.$message({
          type: 'error',
          message: '请输入评论内容'
        })
        return false
      }
      let loadingInstance = Loading.service({})
      let params = {
        book_id: this.bookId,
        content: this.htmlText
      }
      params = qs.stringify(params)
      axios.post('book/comment/insertComment', params).then((res) => {
        if (res.data.resultCode === 1033) {
          this.$message({
            type: 'error',
            message: res.data.message
          })
          this.dialogVisible = false
          return false
        }
        if (res.data.code === 0) {
          setTimeout(() => {
            this.$message({
              type: 'success',
              message: '评论成功'
            })
          }, 1500)
          this.dialogVisible = false
          this.selectBooksById(this.bookId)
        } else {
          this.$message({
            type: 'error',
            message: res.data.message
          })
          this.dialogVisible = false
        }
      setTimeout(() => {
      loadingInstance.close()
      }, 2500)
      })
      this.dialogVisible = false
    },
    clearEmailFrom () {
      this.emailDialog = true
      this.emailData = {
        email: '',
        code: ''
      }
    },
    submitEmail (refName) {
      this.$refs[refName].validate((valid) => {
        if (valid) {
          // 通过
          if (this.emailAnswer === this.emailData.code) {
            this.emailDialog = false
            this.open()
          } else {
            alert('您输入的验证码有误,请重新输入')
          }
        } else {
          // 没通过
          return false
        }
      })
    },
    axiosToEmail () {
      let email = this.emailData.email + '@qq.com'
      // eslint-disable-next-line no-useless-escape
      let emailReg = /^\w{5,}@[a-z0-9]{2,3}\.[a-z]+$|\,$/
      if (emailReg.test(email)) {
        this.toCodeDialog = false
        let params = {
          email: email
        }
        // params = qs.stringify(params)
        axios.get('book/record/sendEmail', {params}).then((res) => {
          let timer = setInterval(() => {
            this.djs = parseInt(this.djs) - 1
          }, 1000)
          setTimeout(() => {
            this.djs = 60
            this.toCodeDialog = true
            clearInterval(timer)
          }, 60000)
          this.emailAnswer = res.data.data
        })
      }
    },
    cancelButton () {
      this.emailDialog = false
    },
    handleClose (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    },
    aftersSuccessClose (done) {
      this.$confirm('确定要提交评论吗？')
        .then(_ => {
          this.loading = true
          let params = {
            book_id: this.bookId,
            content: this.textarea
          }
          params = qs.stringify(params)
          axios.post('book/comment/insertComment', params).then((res) => {
            if (res.data.resultCode === 1033) {
              this.$message({
                type: 'error',
                message: res.data.message
              })
              return false
            }
            if (res.data.code === 0) {
              setTimeout(() => {
                this.loading = false
                this.drawer = false
                this.$message({
                  type: 'success',
                  message: '评论成功'
                })
              }, 2000)
              this.selectBooksById(this.bookId)
            } else {
              this.$message({
                type: 'error',
                message: res.data.message
              })
            }
          })
        })
        .catch(_ => {})
    },
    goBack () {
      this.$router.push('/')
    },
    open () {
      // 直接借
      this.$confirm('是否确定借取该书籍??注意：需要您线下自己取噢！', '友情提示', {
        confirmButtonText: '决定借取',
        cancelButtonText: '取消'
      }).then(() => {
        let params = {
          book_id: this.details.book_id,
          book_title: this.details.book_title
        }
        params = qs.stringify(params)
        axios.post('book/record/insertRecord', params).then((res) => {
          if (res.data.resultCode === 1003 || res.data.resultCode === 5905) {
            this.$message({
              type: 'error',
              message: res.data.message
            })
            return false
          }
          this.$message({
            type: 'success',
            message: res.data.message
          })
        })
      })
      // 二维码
      // this.erweimaDialog = true
      // this.textarea = null
      // setTimeout(() => {
      //   if (this.erweimaIs === 0) {
      //     this.qrcode()
      //     this.erweimaIs = 1
      //   }
      // }, 1)
    },
    qrcode () {
      // eslint-disable-next-line no-unused-vars
      let qrcode = new QRCode('qrcode', {
        width: 132,
        height: 132,
        text: 'https://www.baidu.com'
        // 二维码地址
      })
    },
    selectBookDetails (id) {
      let params = {
        id: id
      }
      axios.get('book/books/selectBooksById', {params}).then((res) => {
        console.log(res)
        this.details = res.data.data
      })
    },
    selectBooksById (id) {
      let params = {
        book_id: id,
        curr_page: this.currPage,
        page_size: 7
      }
      axios.get('book/comment/selectCommentByBookId', {params}).then((res) => {
        console.log(res)
        this.comment = res.data.data.list
        this.total = res.data.data.total
      })
    },
    handleCurrentChange (currentPage) {
      this.currPage = currentPage
      this.selectBooksById(this.bookId)
    },
    dianZan () {
      let params = {
        id: this.details.book_id,
        version: this.details.version
      }
      axios.get('book/books/dianZan', {params}).then((res) => {
        console.log(res)
        this.dzDialog = false
        if (res.data.resultCode === 1003) {
          this.$message({
            type: 'error',
            message: res.data.message
          })
          return false
        }
        if (res.data.resultCode === 9009) {
          this.$message({
            type: 'error',
            message: res.data.message
          })
        }
        if (res.data.code === 0) {
          this.details.stock = parseInt(this.details.stock) + 1
        }
      })
    }
  },
  mounted () {
    let loadingInstance = Loading.service({})
    let theId = this.$route.query.id
    // 地址栏传id的方法
    // console.log(this.$route.query.id)
    this.bookId = theId
    this.selectBookDetails(theId)
    this.selectBooksById(theId)
    if (sessionStorage.getItem('user') === '0') {
    this.shifoushoucang()
    } else {
      this.isShoucang = false
    }
    setTimeout(() => {
      loadingInstance.close()
    }, 1500)
    var editor = new E('#editor')
    tijiao.addEventListener('click', function(e){
      setTimeout(() => {
      editor.txt.clear()
      }, 2000)
    })
    editor.customConfig.zIndex = 1
    editor.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片
    editor.customConfig.uploadImgServer = 'book/comment/commentImgupload'  // 上传图片到服务器
    editor.customConfig.uploadFileName = 'picture'
    editor.customConfig.emotions = [
        {
            // tab 的标题
            title: '默认',
            // type -> 'emoji' / 'image'
            type: 'image',
            // content -> 数组
            content: this.emojiList
        }
    ]
    editor.customConfig.onchange = (html) => {
        // html 即变化之后的内容
        this.htmlText = html
    }

    editor.create()
  }
}
</script>

<style lang="scss">
.details-box{
  margin-top: 6.2rem;
  width: 100%;
  border: 1px solid #c9c9c9;
  .details-box-yt{
    position: absolute;
    top: 8rem;
  }
  .details-top{
    height: 22rem;
    border-bottom: 1px solid #c9c9c9;
    .details-top-img{
    width: 25%;
    height: 22rem;
    display: inline-block;
      img{
        height: 22rem;
        width: 100%;
        transition: transform 1s;
      }
      img:hover{
        transform: scale(1.1);
      }
    }
    .details-top-right{
      display: inline-block;
      width: 75%;
      height: 22rem;
      .details-top-right-content{
        margin: 2rem 0 0 1rem;
        .details-top-right-content-p1{
          font-size: 2.2rem;
          font-family: '华文行楷 Bold', '华文行楷 Regular', '华文行楷';
          font-weight: 700;
        }
        .details-top-right-content-zuozhe{
          margin-top: 1rem;
          width: 100%;
          height: 4rem;
          text-align: left;
          vertical-align: middle;
          line-height: 4rem;
          .details-top-right-content-collect{
            cursor: pointer;
            position: absolute;
            top: 13.45rem;
            left: 74rem;
            img{
              width: 2rem;
              height: 2rem;
            }
          }
          .details-top-right-content-p2{
            font-family: '微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';
            font-weight: 700;
            font-style: normal;
            color: #999999;
          }
          .details-top-right-content-btn{
            width: 12rem;
            height: 80%;
            display: inline-block;
            border: 1px solid rgba(240, 72, 68, 1);
            text-align: center;
            line-height: 3.3rem;
            cursor: pointer;
            margin-right: 1rem;
            p{
              box-shadow: none;
              font-family: '微软雅黑';
              font-weight: 400;
              font-style: normal;
              color: #F04844;
            }
          }
        }
        .details-top-right-content-span1{
          font-family: '微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';
          font-weight: 700;
          font-style: normal;
          color: #999999;
        }
        .details-top-right-content-text{
          margin-top: .5rem;
          width: 60rem;
          .details-top-right-content-span2{
          line-height: 2.5;
          text-indent: 2.2rem;
          font-family: '微软雅黑';
          font-weight: 400;
          font-style: normal;
          color: #999999;
          display: -webkit-box;
          -webkit-box-orient: vertical;
          -webkit-line-clamp: 3;
          overflow: hidden;
          }
        }
        .details-top-right-content-dz{
          display: inline-block;
          text-align: left;
          position: relative;
          width: 6rem;
          cursor: pointer;
          height: 2rem;
          img{
            width: 2rem;
            height: 2rem;
          }
        }
      }
    }
  }
  .details-box-pl{
    position: relative;
    margin-top: 3rem;
    .details-box-pl-top{
      z-index: 0;
      background-color: white;
      position: absolute;
      top: -2.2rem;
      left: 3rem;
      padding: .5rem;
      border: 1px solid #c9c9c9;
      p{
        font-family: '华文行楷';
        font-weight: 400;
        font-style: normal;
        font-size: 2.5rem;
      }
    }
    .pl-box{
    margin-bottom: 2rem;
    width: 100%;
    height: 14rem;
    z-index: -1;
    border: 1px solid #e4e4e4;
    .pl-box-p1{
      margin-top: 2.4rem;
      margin-left: 2rem;
      font-family: '微软雅黑';
      font-weight: 400;
      font-style: normal;
      font-size: 1.2rem;
      color: #666666;
      text-align: left;
    }
    .pl-box-p2{
      margin-top: 1rem;
      margin-left: 2rem;
      font-family: '微软雅黑';
      font-weight: 400;
      font-style: normal;
      font-size: 1rem;
      color: #666666;
      text-align: left;
    }
    }
  }
}
.the-pl-text{
  width: 60%;
  height: 60%;
  display: inline-block;
  position: relative;
  left: 1.5rem;
}
#editor{
z-index: -1;
}
</style>
