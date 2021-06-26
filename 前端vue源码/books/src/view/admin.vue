<template>
    <el-container class="admin-box">
      <el-header class="admin-header" height="5.5rem">
        <div class="admin-header-left fl">
        <img src="../assets/img/CNLOGO.png" class="admin-header-img">
        <span class="admin-header-span">中华图书管理系统</span>
        </div>
        <div class="admin-header-right fr">
            <el-dropdown>
              <span class="el-dropdown-link">
                登出<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="routerToUserLog(6)">注销</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
        </div>
      </el-header>
      <el-container class="admin-box-box">
        <el-aside width="18rem" class="admin-aside">
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-reading"></i>
                <span>图书管理</span>
              </template>
                <el-menu-item index="1-1" @click="routerToUserLog(0)">图书信息管理</el-menu-item>
                <el-menu-item index="1-2" @click="routerToUserLog(1)">图书分类管理</el-menu-item>
                <el-menu-item index="1-3" @click="routerToUserLog(2)">借书还书记录管理</el-menu-item>
                <el-menu-item index="1-4" @click="routerToUserLog(3)">进购图书列表审核</el-menu-item>
                <el-menu-item index="1-5" @click="routerToUserLog(7)">图书评论管理</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-user"></i>
                <span>用户管理</span>
              </template>
                <el-menu-item index="2-1" @click="routerToUserLog(4)">用户信息管理</el-menu-item>
                <el-menu-item index="2-2" @click="routerToUserLog(5)">用户登录日志</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main class="admin-main">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      routerList: ['/booksData', '/booksCategroy', '/bor', '/buy', '/user', '/userLog', '/login', '/comment']
    }
  },
  components: {

  },
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    routerToUserLog (index) {
      if (index === 6) {
        axios.get('book/user/destroy', null).then(() => {
          console.log('销毁成功')
        })
        sessionStorage.clear()
      }
      this.$router.push(this.routerList[index])
    }
  },
  mounted () {
    if (sessionStorage.getItem('user') !== '2') {
      this.$message({
        type: 'error',
        message: '您不是管理员无法访问'
      })
      this.$router.push('/')
    }
  }
}
</script>

<style lang="scss">
@import '@/assets/style/common.scss';
.admin-box{
  position: relative;
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
  .admin-header{
    position: relative;
    background-color: rgba(0,0,0,.6);
    color: #333;
    text-align: left;
    .admin-header-left{
      margin-left: 5rem;
      position: relative;
      .admin-header-img{
        width: 3.125rem;
        height: 3.125rem;
        position: relative;
        top: 1.2rem;
      }
      .admin-header-span{
        position: relative;
        top: 1.2rem;
        font-family: 华文行楷 Bold,华文行楷;
        font-size: 3rem;
        top: .55rem;
        left: 1rem;
        color: #fff;
      }
    }
    .admin-header-right{
      text-align: center;
      line-height: 5.5rem;
      width: 8rem;
      height: 5.5rem;
      .el-dropdown-link{
        color: white;
      }
    }
  }
  .admin-box-box{
    .admin-aside {
      background-color: white;
      color: #333;
      border-right: 0.0625rem solid #efefef;
      .el-menu{
        border-right:none;
      }
    }
    .admin-main {
      color: #333;
    }
  }
}
</style>
