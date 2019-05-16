<template>
  <div class="container-fluid">
    <h1>{{beer.beerName}}</h1>

    <div class="row">
        <div class="col-lg-8 text-left">
          <img v-if="beer.images" :src="beerImage" alt="">
        </div>
        <div class="col-lg-4">
          <h2>Attributes List</h2>
          <p>ABV: {{beer.ABV}}</p>
          <p>IBU: {{beer.IBU}}</p>
          <p>Brewery: {{beer.brewery}}</p>
          <p>Style: {{beer.beerStyle}}</p>
        </div>
    </div>

    <div class="text-wrapper text-left">
      <p v-if="beer.description">{{beer.description}}</p>

      <h3 v-if="beer.staffReviews">Reviews</h3>
      <div v-for="review in beer.staffReviews">
        <p>{{review.review}}</p>
        <p>~{{review.staffMember}}</p>
      </div>
    </div>



  </div>
</template>

<script>
  import {instance} from '@/utils/http_request';

  export default {
    name: 'beer',
    props: ["id"],
    data(){
      return{
        beer: {},
        beerImage : ""
      }
    },
    created() {
      let id = this.$route.params.id;
      instance.get('/beer?id='+id)
        .then( (response) => {
          this.beer = response.data,
          this.beerImage = response.data.images[0];
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

</style>
