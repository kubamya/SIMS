import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import ewm from '@/components/ewm.vue'
import main from '@/view/main/main.vue'
import login from '@/view/login/login.vue'
import organManage from '@/view/organManage/organManage.vue'
import schedule from '@/view/schedule/schedule.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/ewm',
      name: 'ewm',
      component: ewm
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/main',
      name: 'main',
      component: main,
      children:[
        {
          path: '/organManage',
          name: 'organManage',
          component: organManage
        },
        {
          path: '/schedule',
          name: 'schedule',
          component: schedule
        },
      ]
    },
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    }
  ]
})
