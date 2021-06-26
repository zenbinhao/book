<template>
  <el-row  class="bottom-div" type="flex">
    <el-col :span="18" :offset="2">
      <span class="bottom-span">人若有志,万事可为</span>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data () {
    return {

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

<style>
.bottom-div{
  background-color: #999;
  height: 5rem;
}
.bottom-span{
  position: relative;
  top: 1rem;
  font-family: '华文行楷';
  font-weight: 400;
  font-size: 3rem;
  color: white;
}
</style>
