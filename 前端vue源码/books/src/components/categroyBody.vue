<template>
  <el-row  class="infinite-list-wrapper" style="overflow:auto" type="flex">
    <el-col :span="19" :offset="2">
      <div class="categroy-content-title">
        <p>分类</p>
      </div>
      <div class="categroy-content">
        <el-button size="mini" @click.native="findAllBooks()">全部</el-button>
        <el-button size="mini" v-for="(obj,index) in category" :key="index" :label="obj.category_name" @click.native="getCategoryId(obj.category_id)">{{obj.category_name}}</el-button>
      </div>
      <h1 class="title-h-two">全部书籍</h1>
        <ul class="list">
          <li v-for="(item,index) in books" :key="index" class="list-item fl">
            <div class="book">
              <div class="book-img">
                <img :src="item.book_picture">
              </div>
              <h2>{{item.book_title}}</h2>
              <p>{{item.introduce}}</p>
              <div class="book-btn">
                <el-button type="info" class="cate-btn fr" plain @click="routerToDetails(item.book_id)">了解详情</el-button>
              </div>
            </div>
          </li>
        </ul>
        <el-button type="text" class="fr" @click="getMore()">点我加载更多</el-button>
    </el-col>
  </el-row>
</template>

<script>
import { Loading } from 'element-ui'
import axios from 'axios'
// import qs from 'qs'
export default {
  props: ['title'],
  data () {
    return {
      tabPosition: '全部',
      loading: false,
      count: 9,
      isK: false,
      action: {
        getCategoryAll: 'book/category/selectPageCategory',
        getBooks: 'book/books/selectBooksByCategoryId'
      },
      category: [],
      categoryId: null,
      pageSize: 9,
      books: [],
      booksTotal: null,
      moreCount: 9,
      bookTitle: null
    }
  },
  methods: {
    load () {
      this.loading = true
      setTimeout(() => {
        this.count += 3
        this.loading = false
      }, 2000)
    },
    getCategory () {
      let params = {
        curr_page: 1,
        page_size: 20
      }
      axios.get(this.action.getCategoryAll, {params}).then((res) => {
        this.category = res.data.data.list
        console.log(res)
      })
    },
    getCategoryId (id) {
      this.categoryId = id
      this.getBooks()
    },
    getBooks () {
      let loadingInstance = Loading.service({})
      let params = {
        curr_page: 1,
        page_size: this.pageSize,
        category_id: this.categoryId,
        title: this.bookTitle
      }
      console.log(params)
      axios.get(this.action.getBooks, {params}).then((res) => {
        this.books = res.data.data.list
        this.booksTotal = res.data.data.total
      })
      setTimeout(() => {
        loadingInstance.close()
      }, 1000)
    },
    findAllBooks () {
      this.categoryId = null
      this.getBooks()
    },
    getMore () {
      if (this.moreCount < this.booksTotal) {
        this.moreCount = this.moreCount + 5
        this.pageSize = this.pageSize + 5
        this.getBooks()
      } else {
        alert('已经没有更多图书了')
      }
    },
    findBooksByTitle (title) {
      this.bookTitle = title
      this.getBooks()
    },
    routerToDetails (id) {
      // 地址栏传id的方法
      this.$router.push({name: 'details', query: {id: id}})
      // sessionStorage.setItem('id', id)
    }
  },
  mounted () {
    let loadingInstance = Loading.service({})
    this.getCategory()
    this.getBooks()
    setTimeout(() => {
      loadingInstance.close()
    }, 1500)
  },
  computed: {
    noMore () {
      return this.count >= 20
    },
    disabled () {
      return this.loading || this.noMore
    }
  }
}
</script>

<style lang="scss">
@import '@/assets/style/common.scss';
.categroy-content{
  width: 90%;
  height: 9rem;
  border: 1px solid #e9ebef;
  margin-top: 5rem;
  position: relactive;
  z-index: -1;
  line-height: 9rem;
  text-align: left;
  padding-left: 4rem;
  .el-tag{
    margin-left: 0.5rem;
    cursor: pointer;
    &:hover {
      color:#999999;
    }
  }
}
.categroy-content-title{
  border: 1px solid #e9ebef;
  width: 7rem;
  height: 3.5rem;
  vertical-align: middle;
  line-height: 3.5rem;
  text-align: center;
  position: absolute;
  top: 3rem;
  left: 14rem;
  background-color: white;
}
.title-h-two{
  font-size: 1.8rem;
  font-weight: 500;
  color: #666666;
  margin-bottom: 2rem;
  margin-top: 2rem;
}
.infinite-list-wrapper{
  .list-item{
    position: relative;
    margin-right: 2.6rem;
    margin-bottom: 3.6rem;
    .book{
      width: 28rem;
      height: 35rem;
      border: 1px solid #eaeaea;
      text-align: center;
      .book-img{
        width: 70%;
        height: 50%;
        overflow: hidden;
        display: inline-block;
        img{
          width: 100%;
          height: 100%;
        }
      }
      h2{
        margin-top: 1.5rem;
        text-align: left;
        font-size: 1.3rem;
        font-weight: 500;
        padding-left: 2.25rem;
      }
      p{
        text-indent: 1rem;
        margin-top: 1.5rem;
        padding: 0rem 2.25rem;
        font-weight: 500;
        text-align: left;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 3;
        overflow: hidden;
        color: #999999;
        line-height: 2;
      }
      .book-btn{
        width: 80%;
        height: 10%;
        display: inline-block;
        position: absolute;
        left: 3rem;
        bottom: 1.5rem;
        .cate-btn{
          width: 8rem;
          height: 3rem;
          font-size: 1.1rem;
        }
      }
    }
  }
}
</style>
