<template>
  <div class="loginBg" v-loading="loading"
    element-loading-text="拼命加载中"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)">
      <vue-particles
          color="#fff"
          :particleOpacity="0.3"
          :particlesNumber="150"
          shapeType="circle"
          :particleSize="3"
          linesColor="#fff"
          :linesWidth="1"
          :lineLinked="false"
          :lineOpacity="0.4"
          :linesDistance="150"
          :moveSpeed="6"
          :hoverEffect="true"
          hoverMode="repulse"
          :clickEffect="true"
          clickMode="push"
          class="lizi"
        >
        </vue-particles>
        <div class="login-input">
        <el-form ref="loginForm" :model="user" size="small" :rules="rules">
          <el-form-item prop="account">
            <el-input placeholder="请输入账号" maxlength="11" class="inp-login" prefix-icon="el-icon-user" v-model="user.account" clearable></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input placeholder="请输入密码" class="inp-login" prefix-icon="el-icon-more" v-model="user.password" show-password clearable></el-input>
          </el-form-item>
        <el-form-item>
          <button class="contro-login-btn fr" round @click="login('loginForm')">登录</button>
          <button class="contro-login-btn fl" round @click="routerToregister">注册</button>
        </el-form-item>
        </el-form>
        </div>
        <div class="contro-login-title"  @click="toLogingogo()">
<!--   <svg viewBox="0 0 600 300"> -->
          <!-- Pattern -->
<!-- <pattern
                   id="p-fire"
                   viewBox="30 100 186 200"
                   patternUnits="userSpaceOnUse"
                   width="216" height="200"
                   x="-70" y="35"> -->
            <!-- Fire -->
<!--            <image xlink:href="https://tympanus.net/codrops-playground/assets/images/posts/23145/fire.gif" width="256" height="300"/>
          </pattern> -->
          <!-- Text -->
<!--          <text text-anchor="middle"
                x="50%"
                y="50%"
                dy=".35em"
                class="contro-login-title-text"
                >
           中华图书登录
          </text>
        </svg> -->
        <div class="stage">
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
          <div class="layer"></div>
        </div>
        </div>
  </div>
</template>

<script>
import axios from 'axios'
import qs from 'qs'
export default {
  data () {
    // let checkPhone = (rule, value, callback) => {
    //   let phoneReg = /^1[3456789]\d{9}$/
    //   if (!phoneReg.test(value)) {
    //     return callback(new Error('账号必须为手机号格式'))
    //   }
    //   callback()
    // }
    // let checkPwd = (rule, value, callback) => {
    //   let pwdReg = /^[a-z0-9_-]{6,18}$/
    //   if (!pwdReg.test(value)) {
    //     return callback(new Error('请输入6到18为数字或字母'))
    //   }
    //   callback()
    // }
    return {
      loginDialog: true,
      user: {
        account: '',
        password: ''
      },
      rules: {
        account: [
          {required: true, message: '请输入用户名', tigger: 'blur'}
          // {validator: checkPhone, tigger: 'change'}
        ],
        password: [
          {required: true, message: '请输入正确的密码', tigger: 'blur'}
          // {validator: checkPwd, tigger: 'change'}
        ]
      },
      action: {
        login: 'book/user/login'
      },
      loading: false
    }
  },
  components: {

  },
  methods: {
    login (refName) {
      this.loading = true
      this.$refs[refName].validate((valid) => {
        if (valid) {
          // 校验通过
          // 对接登录
          this.getRsaKey(this.user.password, (res) => {
            let params = {
              account: this.user.account,
              password: res
            }
            params = qs.stringify(params)
            // let data = new FormData()
            // data.append('account',this.user.account)
            // data.append('password',this.user.password)
            setTimeout(() => {
              axios.post(this.action.login, params).then((res) => {
                console.log(res)
                if (res.data.resultCode === 2019) {
                  this.$message({
                    type: 'error',
                    message: res.data.message
                  })
                  this.loading = false
                  return false
                }
                if (res.data.resultCode === 2008) {
                  this.$message({
                    type: 'error',
                    message: res.data.message
                  })
                  this.loading = false
                  return false
                }
                if (res.data.code === 0) {
                  if (res.data.data.state === 2) {
                    this.$message({
                      type: 'success',
                      message: '欢迎管理员' + res.data.data.user_name + '登录'
                    })
                    sessionStorage.setItem('user', '2')
                    setTimeout(() => {
                      this.$router.push('/admin')
                    }, 500)
                    this.loading = false
                    return true
                  }
                  this.$message({
                    type: 'success',
                    message: '欢迎' + res.data.data.user_name + '登录'
                  })
                  sessionStorage.setItem('user', '0')
                  setTimeout(() => {
                    this.$router.push('/')
                  }, 500)
                  this.loading = false
                  return true
                } else {
                  this.$message({
                    type: 'error',
                    message: '账户或密码错误'
                  })
                  this.loading = false
                  return false
                }
              })
            }, 500)
          })
        } else {
          // 校验不通过
          this.loading = false
          return false
        }
      })
    },
    routerToregister () {
      this.$router.push({name: 'register'})
    },
    getRsaKey (password, callback) {
      axios.get('book/rsa/generateRSAJsKey').then((res) => {
        let keyArr = res.data.data.split(';')
        // 公钥模板
        let keyModel = keyArr[0]
        // 公钥指数
        let keyExponent = keyArr[1]
        // 生成公钥
        // eslint-disable-next-line no-undef
        let keyPair = RSAUtils.getKeyPair(keyExponent, '', keyModel)
        // 通过公钥加密密码
        password = password.split('').reverse().join('')
        // eslint-disable-next-line no-undef
        let newPwd = RSAUtils.encryptedString(keyPair, password)
        callback(newPwd)
      })
    },
    toLogingogo () {
      this.$router.push({name:'index'})
    }
  },
  mounted () {
  }
}
</script>

<style lang="scss">
// @import url(https://fonts.googleapis.com/css?family=Open+Sans:800);
@import '@/assets/style/common.scss';
.lizi{
  z-index: 1001;
  width: 99%;
}
.login-input{
  width: 18rem;
  z-index: 99;
  position: absolute;
  top: 25rem;
  left:20rem
}
.contro-login-btn{
  background-color: rgba(255,255,255,.3);
  border: none;
  outline: none;
  padding: .8rem 1.2rem;
  border-radius: 0.625rem;
  cursor: pointer;
  color: white;
  &:hover{
    background-color: rgba(255,255,255,.5);
    border: none;
    outline: none;
    color: white;
    cursor: pointer;
  }
}
.inp-login{
  .el-input__inner{
    padding-left: 2.7rem;
    background-color: rgba(255,255,255,.3);
    color: white;
    border: none;
    outline: none;
  }
  .el-input__inner:hover{
    background-color: rgba(255,255,255,.5);
  }
  .yzm{
    width: 2rem;
  }
}
.loginBg{
  width: 100%;
  height: 100%;
  background-image: url(http://101.132.237.188:8080/book/img/loginbg.jpg);
  background-size: 100% 100%;
}
.contro-login-title{
  position: absolute;
  top: 12rem;
  left: 5rem;
  width: 60rem;
  height: 10rem;
  z-index: 1;
  font-size: 30rem;
  cursor: pointer;
}

// .contro-login-title-text {
//   fill: url(#p-fire);
//   stroke: #330000;
//   stroke-width: 8;
//   stroke-opacity: .5;
// }
// svg {
//   position: absolute;
//   width: 100%;
//   height: 100%;
// }
@charset "UTF-8";
@import url("//fonts.googleapis.com/css?family=Pacifico&text=Pure");
@import url("//fonts.googleapis.com/css?family=Roboto:700&text=css");
@import url("//fonts.googleapis.com/css?family=Kaushan+Script&text=!");

.stage {
  height: 300px;
  width: 500px;
  margin: auto;
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  perspective: 9999px;
  transform-style: preserve-3d;
}

.layer {
  width: 100%;
  height: 100%;
  position: absolute;
  transform-style: preserve-3d;
  animation: ಠ_ಠ 5s infinite alternate ease-in-out -7.5s;
  animation-fill-mode: forwards;
  transform: rotateY(40deg) rotateX(33deg) translateZ(0);
}

.layer:after {
  font: 150px/0.65 'Pacifico', 'Kaushan Script', Futura, 'Roboto', 'Trebuchet MS', Helvetica, sans-serif;
  content: '中华图书';
  white-space: pre;
  text-align: center;
  height: 100%;
  width: 100%;
  position: absolute;
  top: 50px;
  color: whitesmoke;
  letter-spacing: -2px;
  text-shadow: 4px 0 10px rgba(0, 0, 0, 0.13);
}

.layer:nth-child(1):after {
  transform: translateZ(0px);
}

.layer:nth-child(2):after {
  transform: translateZ(-1.5px);
}

.layer:nth-child(3):after {
  transform: translateZ(-3px);
}

.layer:nth-child(4):after {
  transform: translateZ(-4.5px);
}

.layer:nth-child(5):after {
  transform: translateZ(-6px);
}

.layer:nth-child(6):after {
  transform: translateZ(-7.5px);
}

.layer:nth-child(7):after {
  transform: translateZ(-9px);
}

.layer:nth-child(8):after {
  transform: translateZ(-10.5px);
}

.layer:nth-child(9):after {
  transform: translateZ(-12px);
}

.layer:nth-child(10):after {
  transform: translateZ(-13.5px);
}

.layer:nth-child(11):after {
  transform: translateZ(-15px);
}

.layer:nth-child(12):after {
  transform: translateZ(-16.5px);
}

.layer:nth-child(13):after {
  transform: translateZ(-18px);
}

.layer:nth-child(14):after {
  transform: translateZ(-19.5px);
}

.layer:nth-child(15):after {
  transform: translateZ(-21px);
}

.layer:nth-child(16):after {
  transform: translateZ(-22.5px);
}

.layer:nth-child(17):after {
  transform: translateZ(-24px);
}

.layer:nth-child(18):after {
  transform: translateZ(-25.5px);
}

.layer:nth-child(19):after {
  transform: translateZ(-27px);
}

.layer:nth-child(20):after {
  transform: translateZ(-28.5px);
}

.layer:nth-child(n+10):after {
  -webkit-text-stroke: 3px rgba(0, 0, 0, 0.25);
}

.layer:nth-child(n+11):after {
  -webkit-text-stroke: 15px dodgerblue;
  text-shadow: 6px 0 6px #00366b, 5px 5px 5px #002951, 0 6px 6px #00366b;
}

.layer:nth-child(n+12):after {
  -webkit-text-stroke: 15px #0077ea;
}

.layer:last-child:after {
  -webkit-text-stroke: 17px rgba(0, 0, 0, 0.1);
}

.layer:first-child:after {
  color: #fff;
  text-shadow: none;
}

@keyframes ಠ_ಠ {
  100% {
    transform: rotateY(-40deg) rotateX(-43deg);
  }
}
</style>
