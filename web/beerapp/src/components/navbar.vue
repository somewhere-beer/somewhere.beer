<template>
  <div class="nav-bar-wrapper">
    <b-navbar toggleable="lg" type="dark">
      <b-navbar-brand href="/"><img src="../assets/logo.png" style="width:50px;"/></b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"><router-link to="/">Home</router-link></b-nav-item>
          <b-nav-item href="#"><router-link to="/about">About</router-link></b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ul-wrapper">
          <b-nav-form class="form-wrapper" @submit="onSearchFormSubmit">
            <b-form-input size="sm" class="mr-sm-2 input-text" v-model="searchTerm" placeholder="Search"></b-form-input>
            <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
          </b-nav-form>

        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
  export default {
    name: "navbar",
    data(){
      return{
        searchTerm : ""
      }
    },
    methods:{
      onSearchFormSubmit(e){
        e.preventDefault();
        console.log(this.searchTerm);
        this.$router.push({
          name: 'results',
          params: { term: 'textSearch?target='+this.searchTerm }
        });
      }
    },
  }
</script>

<style scoped>
  .nav-bar-wrapper{
    background-color: #a86e46;
  }
  .ul-wrapper{
    margin: 0 auto;
    width: 50%;
  }
  .form-wrapper{
    width: 100%;
  }
  .input-text{
    width: 80% !important;
  }
  a{
    color:#d0c3ab;
  }
  a:hover{
    color:#d0c3ab;
  }
  button{
    background-color:#2d1110;
    border:#2d1110;
  }
  button:hover{
    background-color:#ffffff;
    border:#ffffff;
    color:#2d1110;
  }
</style>
