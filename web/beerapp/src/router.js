import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home/content.vue'
import Category from './views/Category/content.vue'
import Beer from './views/Beer/content.vue'
import Results from './views/AttributesResults/content.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    },
    {
      path: '/category/:type',
      name: 'category',
      component: Category
    },
    {
      path: '/beer/:id',
      name: 'beer',
      component: Beer,
      props: true
    },
    {
      path: '/results/:term',
      name: 'results',
      component: Results
    },
  ]
})
