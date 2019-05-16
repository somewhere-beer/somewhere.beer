<template>
  <div class="row wrapper">
    <div class="col-lg">
      <span v-for="(value, type) in types" class="typeList" @click="onTypeClicked(type)">
        <span>{{value}}</span>
      </span>
    </div>
  </div>
</template>

<script>
  export default {
    name: "typesList",
    data(){
      return{
        types:{
          "ale":"Ale",
          "lager":"Lager",
          "hybrid":"Hybrid",
          "barleywine":"Barleywine",
          "cider":"Cider",
          "amber":"Amber"
        }
      }
    },
    methods:{
      onTypeClicked(type){
        this.$router.push({
          name: 'category',
          params: { type: type }
        });
      }
    }
  }
</script>

<style scoped>
.wrapper{
  padding-bottom:40px;
}
.typeList{
  margin:0px 5px 0px 20px;
  cursor: pointer;
  text-decoration: underline;
}
</style>
