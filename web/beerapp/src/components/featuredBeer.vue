<template>
  <div class="col-lg-8">

    <h3>Featured Beer</h3>

    <div class="wrapper">

      <div class="row">
        <div class="col-lg-4 title-wrapper">
          <h2>{{this.data.beerName}}</h2>
        </div>
        <div class="col-lg-8 main-img-wrapper">
          <img :src="dataImage" alt="">
        </div>
      </div>

      <div class="text-wrapper">
        <p class="text-left">{{this.data.description}}</p>
      </div>

      <div class="text-wrapper text-left">
        <h3>Reviews</h3>
        <div v-for="review in data.staffReviews">
          <p>{{review.review}}</p>
          <p>~{{review.staffMember}}</p>
        </div>
      </div>

      <div>
        <b-button size="sm" variant="info" class="float-left" @click="onBeerClicked">Show</b-button>
      </div>
    </div>

  </div>
</template>

<script>
  import {instance} from '@/utils/http_request';

  export default {
    name: "featuredBeer",
    data(){
      return{
        data : {},
        dataImage: ""
      }
    },
    created(){
      instance.get('/featured')
        .then( (response) => {
          // handle success
          this.data = response.data;
          this.dataImage = response.data.images[0];
        })
        .catch(function (error) {
          // handle error
          console.log(error);
        });
    },
    methods:{
      onBeerClicked(){
        this.$router.push({
          name: 'beer',
          params: { id: this.data.beerId } // 1 = id of the beer
        });
      }
    }
  }
</script>

<style scoped>
  .title-wrapper{
    display:flex;
    align-items: center;
    justify-content: flex-start;
  }
  .text-wrapper{
    margin-top:20px;
  }
  button{
    background-color:#a86e46;
    border:#a86e46;
  }
  button:hover{
    background-color:#ffffff;
    border:#ffffff;
    color:#a86e46;
  }
</style>
