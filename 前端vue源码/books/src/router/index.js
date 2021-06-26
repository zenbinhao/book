import Vue from 'vue'
import Router from 'vue-router'
import index from '@/view/index'
import details from '@/view/details'
import allBooks from '@/view/allBooks'
import login from '@/view/login'
import register from '@/view/register'
import admin from '@/view/admin'
import userLog from '@/components/userLog'
import adminMain from '@/components/adminMain'
import booksData from '@/components/booksData'
import booksCategroy from '@/components/booksCategroy'
import bor from '@/components/bor'
import buy from '@/components/buy'
import user from '@/components/user'
import comment from '@/components/comment'
import personal from '@/view/personal'
Vue.use(Router)

const routerPush = Router.prototype.push
Router.prototype.push = function push (location) {
  return routerPush.call(this, location).catch(error => error)
}
export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: index
    },
    {
      path: '/personal',
      name: 'personal',
      component: personal
    },
    {
      path: '/details',
      name: 'details',
      component: details
    },
    {
      path: '/allBooks',
      name: 'allBooks',
      component: allBooks
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
    {
      path: '/admin',
      name: 'admin',
      component: admin,
      redirect: {name: 'adminMain'},
      children: [
        {
          path: '/userLog',
          name: 'userLog',
          component: userLog
        },
        {
          path: '/adminMain',
          name: 'adminMain',
          component: adminMain
        },
        {
          path: '/booksData',
          name: 'booksData',
          component: booksData
        },
        {
          path: '/booksCategroy',
          name: 'booksCategroy',
          component: booksCategroy
        },
        {
          path: '/bor',
          name: 'bor',
          component: bor
        },
        {
          path: '/buy',
          name: 'buy',
          component: buy
        },
        {
          path: '/user',
          name: 'user',
          component: user
        },
        {
          path: '/comment',
          name: 'comment',
          component: comment
        }
      ]
    }
  ]
})
