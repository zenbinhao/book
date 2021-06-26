<template>
  <el-carousel class="slideshow" indicator-position="none">
    <el-carousel-item v-for="(item, index) in pList" :key="index">
      <div class="control_p">
        <p class="p1">{{item.p1}}</p>
        <p class="p2">{{item.p2}}</p>
        </div>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
  data () {
    return {
      pList: [{
        p1: '赋能你我 连接未来',
        p2: '与合作伙伴共同营造健康的互联网生态环境'
      },
      {
        p1: '用心服务 关爱社会',
        p2: '成为最受尊敬的企业是我们景愿的目标'
      },
      {
        p1: '享受卓越 享受责任',
        p2: '努力通过互联网服务提升人类生活品质'
      },
      {
        p1: '创造需求 享受责任',
        p2: '不断倾听和满足用户需求，引导并超越用户需求'
      }
      ]
    }
  },
  components: {

  },
  methods: {

  },
  mounted () {

  }
}
</script>

<style lang="scss">
.slideshow .el-carousel__container{
  width: 100%;
  height: 45rem;
}
.slideshow .el-carousel__item:nth-child(n) {
  background-color: #d7d7d7;
  z-index: -1;
}
.control_p{
  width: 100%;
  position: relative;
  top: 16rem;
  color: #FFFFFF;
  text-align: center;
}
.p1{
  font-size: 4rem;
}
.p2{
  font-size: 2rem;
}
</style>
