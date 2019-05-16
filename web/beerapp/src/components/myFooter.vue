<template>
  <footer class="footer">
    <div class="container-fluid">
      <div class="row">
        <div class="col-lg-4">
          <img src="../assets/logo.png" style="width:50px;" alt="">
        </div>

        <div class="col-lg-4">
          <b>Quick Links</b>
          <div>
            <router-link to="/">Home</router-link>
            <br>
            <router-link to="/about">About</router-link>
          </div>
        </div>

        <div class="col-lg-4">
          <b>License</b>
          <p>
            Beer information obtained from <u><a href="https://catalog.beer">catalog.beer</a></u> under
            <u><a href="https://creativecommons.org/licenses/by/4.0">CC BY 4.0</a></u> license
            (excluding all beer and brewer names and brands, which belong to their respective owners).
          </p>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
  export default {
    name: "myFooter",

  }
</script>

<style scoped>
  .container {
    width: auto;
    max-width: 680px;
    padding: 0 15px;
  }

  .footer {
    padding-top: 30px;
    background-color: #a86e46;
  }
  a{
    color:#d0c3ab;
  }
  a:hover{
    color:#d0c3ab;
  }
</style>
