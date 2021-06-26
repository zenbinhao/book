<template>
  <el-row class="the-box-row">
    <el-row class="gd" type="flex">
      <el-col :span="24">
        <topNav></topNav>
      </el-col>
    </el-row>
    <el-col :span="18" :offset="2" class="the-box-col">
      <detailsBody></detailsBody>
    </el-col>
    <el-col :span="24" class="bottom">
      <bottom></bottom>
    </el-col>
  </el-row>
</template>

<script>
import topNav from '@/components/topNav.vue'
import detailsBody from '@/components/detailsBody.vue'
import bottom from '@/components/bottom.vue'
export default {
  data () {
    return {

    }
  },
  components: {
    topNav,
    detailsBody,
    bottom
  },
  methods: {

  },
  mounted () {

  }
}
</script>

<style lang="scss">
@import '@/assets/style/common.scss';
.the-box-row{
  width: 100%;
  height: 100%;
  .gd{
    position: sticky;
    top: 0px;
    z-index: 99;
  }
  .bottom{
    background-color: rgba(153, 153, 153, 1);
    line-height: 3.6rem;
  }
  .the-box-col{
    margin-bottom: 3rem;
  }
}
</style>
