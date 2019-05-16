<template>
  <div class="home container-fluid">
    <typesList/>
    <div class="row">
      <featuredBeer/>

      <attributesList/>
    </div>
  </div>
</template>

<script>
import featuredBeer from '@/components/featuredBeer';
import typesList from '@/components/typesList';
import attributesList from '@/components/attributesList';

export default {
  name: 'home',
  components: {
    featuredBeer,
    typesList,
    attributesList
  }
}
</script>

<style>
  .home{
    background-color: #221715;
  }
</style>
