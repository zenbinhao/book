<template>
  <div>
    <el-row type="flex" class="contro-fixed">
      <el-col :span="24">
        <topNav @giveTitle="giveTitle"></topNav>
      </el-col>
    </el-row>
    <categroyBody ref="mychild"></categroyBody>
    <el-row type="flex">
      <el-col :span="24">
        <bottom></bottom>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import topNav from '@/components/topNavTwo.vue'
import bottom from '@/components/bottom.vue'
import categroyBody from '@/components/categroyBody.vue'
export default {
  data () {
    return {
    }
  },
  components: {
    topNav,
    bottom,
    categroyBody
  },
  methods: {
    giveTitle (title) {
      this.$refs.mychild.findBooksByTitle(title)
    }
  },
  mounted () {

  }
}
</script>

<style lang="scss">
@import '@/assets/style/common.scss';
.contro-fixed{
  position: sticky;
  top: 0;
  z-index: 99;
}
</style>
