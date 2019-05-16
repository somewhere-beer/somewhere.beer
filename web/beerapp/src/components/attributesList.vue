<template>
  <div class="col-lg-4">
    <div class="col-lg-12">
      <h2>Search by attribute</h2>
      <b-form @submit="onAttributeFormSubmit">

        <b-form-group
            id="input-group-1"
            label="Type"
            label-for="attr-type">
          <b-form-input id="att-type" type="text" v-model="type"></b-form-input>
        </b-form-group>

        <b-form-group
            id="input-group-1"
            label="ABV"
            label-for="attr-abv">
          <b-form-input id="att-abv" type="number" v-model="abvValue" min="0" max="100" step="0.5" ></b-form-input>
        </b-form-group>

        <b-form-group
            id="input-group-1"
            label="IBU"
            label-for="attr-ibn">
          <b-form-input id="att-ibn" type="number" v-model="ibuValue" min="0" max="100" step="0.5" ></b-form-input>
        </b-form-group>

        <b-form-group
            id="input-group-1"
            label="Brewery"
            label-for="brower">
          <b-form-input id="brower" type="text" v-model="brewery"></b-form-input>
        </b-form-group>

        <b-button class="float-right" type="submit">Search</b-button>
      </b-form>

      </div>
  </div>
</template>

<script>
  export default {
    name: "attributesList",
    data(){
      return{
        abvValue:'',
        ibuValue:'',
        brewery: '',
        type:'',
      }
    },
    methods:{
      onAttributeFormSubmit(e){
        e.preventDefault();
          this.$router.push({
            name: 'results',
            params: { term: "abv="+this.abvValue+"&ibu="+this.ibuValue+"&brewery="+this.brewery+"&type="+this.type }
          });
      },
    }
  }
</script>

<style scoped>
  button{
    background-color:#a86e46;
    border:#a86e46;
  }
  button:hover{
    background-color:#ffffff;
    border:#ffffff;
    color:#a86e46;
  }
  input:disabled{
    background-color: #b2b5b7;
  }
</style>
