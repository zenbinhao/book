<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  mounted () {
    const htmlSize = document.documentElement.clientWidth || document.body.clientWidth
    const dom = document.getElementsByTagName('html')[0]
    dom.style.fontSize = htmlSize / 120 + 'px'
    window.addEventListener('resize', function (e) {
      const htmlSize = document.documentElement.clientWidth || document.body.clientWidth
      dom.style.fontSize = htmlSize / 120 + 'px'
    })
  }
}
</script>

<style>
</style>
