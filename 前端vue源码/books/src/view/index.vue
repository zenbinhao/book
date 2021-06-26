<template>
    <el-container class="box">
      <el-header height="5.5rem" class="theHeader">
        <topNav></topNav>
      </el-header>
      <el-main>
        <slide></slide>
        <mainBody></mainBody>
      </el-main>
      <el-footer class="bottom">
        <bottom></bottom>
      </el-footer>
    </el-container>
</template>

<script>
import topNav from '@/components/topNav.vue'
import mainBody from '@/components/mainBody.vue'
import slide from '@/components/slide.vue'
import bottom from '@/components/bottom.vue'
export default {
  data () {
    return {

    }
  },
  components: {
    topNav,
    mainBody,
    slide,
    bottom
  },
  methods: {

  },
  mounted () {

  }
}
</script>
<style lang="scss" scoped="scoped">
@import '@/assets/style/common.scss';
.el-header,.el-main,.el-footer{
  padding: 0;
  margin: 0;
}
.box{
  width: 100%;
}
.theHeader{
  height: 5rem;
  padding: 0;
  margin: 0;
  position: sticky;
  top: 0rem;
  z-index: 99;
}
.el-main{
  height: 133rem;
}
.bottom{
  background-color: rgba(153, 153, 153, 1);
}
</style>
