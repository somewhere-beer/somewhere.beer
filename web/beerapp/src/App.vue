<template>
  <div id="app">
    <navbar/>
    <div id="mainSection">
      <router-view/>
    </div>
    <myFooter/>
  </div>
</template>

<script>
  import navbar from '@/components/navbar.vue';
  import myFooter from '@/components/myFooter';

  export default {
    name: 'home',
    components: {
      navbar,
      myFooter
    }
  }
</script>

<style>
html{
  position: relative;
  min-height: 100%;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #D0C3AB;
}
#mainSection {
  padding-bottom: 30px;
  padding-top:40px;
  background-color: #221715;
}
</style>
