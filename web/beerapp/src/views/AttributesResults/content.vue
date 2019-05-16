<template>
  <div class="container-fluid">
    <h1>Results</h1>
    <h3>We have found {{data.numberFound}} results</h3>

    <div class="row">
      <div class="col-lg-12 text-left">
        <ul class="list-unstyled">
          <li v-for="beer in data.beers"><router-link :key="beer.beerId" :to="{ name: 'beer', params: { id: beer.beerId }}">{{beer.beerName}}</router-link></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
  import {instance} from '@/utils/http_request';

  export default {
    name: 'attributesResults',
    data(){
      return{
        searchTerm:"",
        data:{}
      }
    },
    created() {
      this.searchTerm = this.$route.params.term;
      instance.get('/attribueSearch?'+ this.searchTerm)
        .then( (response) => {
          // handle success
          this.data = response.data;
        })
        .catch(function (error) {
          // handle error
          console.log(error);
        });
    },
    components: {
    }
  }
</script>

<style scoped>
  a{
    color:#d0c3ab;
  }
  a:hover{
    color:#d0c3ab;
  }
</style>
