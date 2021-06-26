<template>
  <el-row type="flex" align="middle" class="register-main" v-loading="loading"
    element-loading-text="拼命加载中"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)">
    <span class="register-span-top">All things can be done if one wishes</span>
    <p class="register-p">人若有志,万事可为</p>
    <span class="register-span">Manage Your Account</span>
    <img src="http://101.132.237.188:8080/book/img/b11.png" class="register-img">
    <el-col :span="8" :offset="12" class="register-main-col">
        <el-form label-width="90px" :rules="rules" :data="user" :model="user" ref="registerForm" label-position="left">
          <el-form-item label="账号:" class="input" prop="account">
            <el-input v-model="user.account" @blur="checkAccount" clearable></el-input>
          </el-form-item>
          <el-form-item label="密码:" class="input" prop="password">
            <el-input v-model="user.password" show-password clearable></el-input>
          </el-form-item>
          <el-form-item label="确认密码:" class="input" prop="repassword">
            <el-input v-model="user.repassword" show-password clearable></el-input>
          </el-form-item>
          <el-form-item label="用户昵称:" class="input" prop="name">
            <el-input v-model="user.name" clearable></el-input>
          </el-form-item>
          <el-form-item label="手机号:" class="input" prop="phone">
            <el-input v-model="user.phone" clearable></el-input>
          </el-form-item>
          <el-form-item label="验证码:" class="input" prop="code">
            <el-input v-model="user.code" clearable>
                  <el-button slot="append" @click="toPhoneCode" v-if="dis">发送验证码</el-button>
                  <el-button slot="append" v-else disabled>{{djs}}&nbsp;已发送验证码</el-button>
            </el-input>
          </el-form-item>
            <el-button class="rigbtn fl" @click="routerTologin">老用户登录</el-button>
            <el-button class="rigbtn fr" @click="submitRegister('registerForm')" :disabled="registerDialog">立即注册</el-button>
        </el-form>
    </el-col>
  </el-row>
</template>

<script>
import axios from 'axios'
import qs from 'qs'
export default{
  data () {
    let checkPassword = (rule, value, callback) => {
      if (value.length < 6) {
        return callback(new Error('密码长度小于6位'))
      }
      callback()
    }
    let checkAccount = (rule, value, callback) => {
      setTimeout(() => {
        if (this.registerAccountDialog === 2005) {
          return callback(new Error('您的账号不能低于6位且需要包含字母数字的字符!'))
        }
        if (this.registerAccountDialog === 7010) {
          return callback(new Error('该账户已经存在!'))
        }
        callback()
      }, 500)
    }
    let checkphone = (rule, value, callback) => {
      let phoneReg = /^1[3456789]\d{9}$/
      if (!phoneReg.test(value)) {
        return callback(new Error('手机号格式不正确'))
      }
      callback()
    }
    let checkpwd = (rule, value, callback) => {
      if (value !== this.user.password) {
        return callback(new Error('两次密码输入不一致!'))
      }
      callback()
    }
    return {
      // 绑定表单值
      user: {
        account: '',
        password: '',
        repassword: '',
        name: '',
        phone: '',
        code: ''
      },
      rules: {
        account: [
          {required: true, message: '请输入账户', trigger: 'blur'},
          {validator: checkAccount, trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入昵称', trigger: 'blur'}
        ],
        phone: [
          {required: true, message: '请输入手机号码', trigger: 'blur'},
          {validator: checkphone, trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {validator: checkPassword, trigger: 'blur'}
        ],
        repassword: [
          {required: true, message: '请再次输入密码', trigger: 'blur'},
          {validator: checkpwd, trigger: 'blur'}
        ],
        code: [
          {required: true, message: '请输入验证码', trigger: 'blur'}
        ]
      },
      dis: true,
      djs: 60,
      isToCode: true,
      registerDialog: false,
      loading: false
    }
  },
  components: {

  },
  methods: {
    checkAccount () {
      let params = {
        account: this.user.account
      }
      axios.get('book/user/registerSelectAccount', {params}).then((res) => {
        if (res.data.resultCode === 7010 || res.data.resultCode === 2005) {
          this.registerAccountDialog = res.data.resultCode
          return false
        }
        if (res.data.code === 0) {
          this.registerAccountDialog = 2019
          this.$message({
            type: 'success',
            message: res.data.message
          })
        }
      })
    },
    routerTologin () {
      this.$router.push({name: 'login'})
    },
    toPhoneCode () {
      if (this.checkPhonIs()) {
        console.log('该手机号码正确可以发送验证码')
        this.dis = false
        let params = {
          phone: this.user.phone
        }
        params = qs.stringify(params)
        axios.post('book/user/phoneCode', params).then((res) => {
          let timer = setInterval(() => {
            this.djs = parseInt(this.djs) - 1
          }, 1000)
          setTimeout(() => {
            this.dis = true
            this.djs = 60
            clearInterval(timer)
          }, 60000)
        })
      }
    },
    submitRegister (refName) {
      this.loading = true
      this.$refs[refName].validate((valid) => {
        if (valid) {
          this.registerDialog = true
          let params = {
            phone: this.user.phone,
            code: this.user.code
          }
          params = qs.stringify(params)
          axios.post('book/user/rePhoneCode', params).then((res) => {
            console.log(res)
            if (res.data) {
              let p = {
                user_name: this.user.name,
                account: this.user.account,
                password: this.user.password,
                phone: this.user.phone
              }
              p = qs.stringify(p)
              axios.post('book/user/insert', p).then((res) => {
                if (res.data.resultCode === 2001 || res.data.resultCode === 2002 || res.data.resultCode === 2003 || res.data.resultCode === 2004 || res.data.resultCode === 2005 || res.data.resultCode === 2006 || res.data.resultCode === 2007 || res.data.resultCode === 1018 || res.data.resultCode === 1019 || res.data.resultCode === 9001) {
                  this.$message({
                    type: 'error',
                    message: res.data.message
                  })
                  this.registerDialog = false
                  this.loading = false
                  return false
                }
                if (res.data.code === 0) {
                  this.$message({
                    type: 'success',
                    message: res.data.message
                  })
                  this.loading = false
                  this.registerDialog = false
                  this.user = null
                  this.$router.push('/login')
                  return true
                }
                this.registerDialog = false
                this.loading = false
              })
            } else {
              this.$message({
                type: 'error',
                message: '您输入的验证码错误'
              })
              this.registerDialog = false
              this.loading = false
            }
          })
        } else {
          this.registerDialog = false
          this.loading = false
          return false
        }
      })
      this.loading = false
    },
    checkPhonIs () {
      let phoneReg = /^1[3456789]\d{9}$/
      if (!phoneReg.test(this.user.phone)) {
        this.$message({
          type: 'error',
          message: '您的手机格式有问题,请重新输入再发送验证码'
        })
        return false
      }
      return true
    }
  }

}
</script>

<style lang="scss">
@import '@/assets/style/common.scss';
.register{
  width: 119.88rem;
  /* border: 1px solid #42B983; */
}
.register-main{
  height: 100%;
  .register-span-top{
    font-size: 3rem;
    position: absolute;
    top: 6rem;
    left: 32rem;
  }
  .register-p{
    position: absolute;
    top: 13rem;
    left: 52rem;
  }
  .register-span{
    position: absolute;
    font-size: 1.5rem;
    top: 21rem;
    left: 23rem;
  }
  .register-img{
    position: absolute;
    top: 20rem;
    left: 8rem;
    z-index: -1;
  }
  .register-main-col{
    padding: 5rem;
    border-radius: 0.625rem 0.625rem;
    background-repeat: no-repeat;
    background-size: 100% 100%;
    .el-input__inner{
      background-color: rgba(255,255,255,0);
    }
  }
}
.form .input{
  width: 30rem;
  margin-bottom: 1.56rem;
}
.rigbtn {
  width: 98px;
  height: 40px;
}
</style>
