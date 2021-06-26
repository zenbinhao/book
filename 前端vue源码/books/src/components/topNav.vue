<template>
    <el-row class="top-box" type="flex" justify="center">
      <el-col :span="4" :offset="2">
        <el-row>
          <el-col :span="6">
            <div class="center-body-img">
            <img src="../assets/img/CNLOGO.png">
            </div>
          </el-col>
          <el-col :span="18">
            <div class="center-body-aa">
              <a href="javascript:;" class="center-body-a">中华图书</a>
            </div>
            </el-col>
        </el-row>
      </el-col>
      <el-col :span="18" class="col" :xs="18">
        <el-row>
          <el-col :span="3">
            <div class="topTag-a" @click="routerToindex()"><a href="javascript:;">首页</a></div>
          </el-col>
          <el-col :span="3">
            <div class="topTag-a" @click="routerToallBooks()"><a href="javascript:;">所有书籍</a></div>
          </el-col>
          <el-col :span="6" :xs="6">
            <!-- <div class="topTag-a" @click="show2=!show2"><a href="javascript:;">点我搜索书籍</a></div>
            <transition name="el-zoom-in-top">
              <div v-show="show2" class="transition-box">
                <div class="search">
                  <el-input v-model="title" placeholder="请输入书籍名" clearable>
                    <el-button slot="append" icon="el-icon-search" @click="setTitle()"></el-button>
                  </el-input>
                </div>
              </div>
            </transition> -->
            <p>&nbsp;</p>
          </el-col>
          <el-col :span="3">
            <div class="topTag-a" @click="routerTologin()" v-if="dengluShow"><a>登录</a></div>
            <div v-else>&nbsp;</div>
          </el-col>
          <el-col :span="3">
            <div class="topTag-a" v-if="zhuceShow" @click="toRouterRegister()"><a>注册</a></div>
            <div v-else>&nbsp;</div>
          </el-col>
          <el-col :span="3">
            <div class="topTag-a" v-if="gerenShow" @click="toRouterPerson()"><a>个人信息</a></div>
            <div v-else>&nbsp;</div>
          </el-col>
          <el-col :span="3">
            <div class="topTag-a" v-if="tuichuShow" @click="cancelLogin()"><a>退出</a></div>
            <div v-else>&nbsp;</div>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      title: '',
      show2: false,
      tuichuShow: false,
      dengluShow: true,
      zhuceShow: true,
      gerenShow: false
    }
  },
  components: {

  },
  methods: {
    routerToallBooks () {
      this.$router.push({name: 'allBooks'})
    },
    routerToindex () {
      this.$router.push({name: 'index'})
    },
    routerTologin () {
      this.$router.push({name: 'login'})
    },
    toRouterPerson () {
      this.$router.push({name: 'personal'})
    },
    cancelLogin () {
      axios.get('book/user/destroy', null).then(() => {
        console.log('销毁成功')
      })
      sessionStorage.clear()
      this.$router.push('/')
      location.reload()
      // this.$confirm('确认注销？')
      //   .then(_ => {
      //     done()
      //   })
      //   .catch(_ => {})
    },
    toRouterRegister () {
      this.$router.push('/register')
    }
  },
  mounted () {
    if (sessionStorage.getItem('user') !== '0') {
      this.tuichuShow = false
      this.dengluShow = true
      this.zhuceShow = true
      this.gerenShow = false
    } else {
      this.tuichuShow = true
      this.dengluShow = false
      this.zhuceShow = false
      this.gerenShow = true
    }
  }
}
</script>

<style lang="scss">
@import '@/assets/style/common.scss';
.top-box{
  height: 5.5rem;
  width: 100%;
  background-color: rgba(0, 0, 0, 0.6);
  .center-body-img{
    display: inline-block;
    position: relative;
    top: 1.2rem;
    height: 3.125rem;
    width: 3.125rem;
    img{
      height: 100%;
      width: 100%;
    }
  }
  .center-body-aa{
    line-height: 5.5rem;
    text-align: center;
    height: 5.5rem;
    .center-body-a{
        font-family: '华文行楷 Bold', '华文行楷';
        font-size: 3rem;
        color: white;
    }
  }
  .topTag-a{
    display: inline-block;
    width: 8rem;
    height: 5.5rem;
    text-align: center;
    line-height: 5.5rem;
    cursor: pointer;
    &:hover{
    cursor: pointer;
    background-color: burlywood;
    }
    a{
    color: #FFFFFF;
    cursor: pointer;
    }
  }
  // .transition-box{
  //   width: 40rem;
  //   margin-left: -15rem;
  // }
}
// .col{
//   height: 5.5rem;
// }
// .center-body{
// height: 5.5rem;
// position: relative;
// }
// .center-body-a{
//   height: 5.5rem;
//   font-family: '华文行楷 Bold', '华文行楷';
//   font-size: 3rem;
//   position: relative;
//   top: .55rem;
//   left: 1rem;
//   color: white;
// }
// .center-body-img{
// display: inline-block;
// position: relative;
// top: 1.2rem;
// height: 3.125rem;
// width: 3.125rem;
// }
// .center-body-img img{
// height: 3.125rem;
// width: 3.125rem;
// }
// .topTag{
// width: 100%;
// height: 5.5rem;
// }
// .topTag-a{
// position: relative;
// left: 1rem;
// display: inline-block;
// width: 8rem;
// height: 5.5rem;
// text-align: center;
// line-height: 5.5rem;
// cursor: pointer;
// }
// .topTag-a:hover{
// position: relative;
// left: 1rem;
// display: inline-block;
// width: 8rem;
// height: 5.5rem;
// text-align: center;
// line-height: 5.5rem;
// cursor: pointer;
// background-color: burlywood;
// }
// .topTag-a a{
// color: #FFFFFF;
// cursor: pointer;
// }
// .search{
// height: 5.5rem;
// width: 30rem;
// display: inline-block;
// text-align: center;
// line-height: 5.5rem;
// position: relative;
// left: 2rem;
// }
// .topTag-a-r{
// position: relative;
// left: 17rem;
// display: inline-block;
// width: 8rem;
// height: 5.5rem;
// text-align: center;
// line-height: 5.5rem;
// cursor: pointer;
// }
// .topTag-a-r:hover{
// position: relative;
// left: 17rem;
// display: inline-block;
// width: 8rem;
// height: 5.5rem;
// text-align: center;
// line-height: 5.5rem;
// cursor: pointer;
// background-color: burlywood;
// }
// .topTag-a-r a{
//    color: #FFFFFF;
//    cursor: pointer;
// }
</style>
