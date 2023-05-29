import { createRouter, createWebHistory } from 'vue-router'
import type { RouteRecordRaw } from "vue-router";
import { useUser } from "../stores/index";
import { ElMessage } from 'element-plus'


export const homeChildren: Array<RouteRecordRaw> = [
  {


    path: 'analysis',
    name: 'analysis',
    component: () => import('../views/system/test/test2.vue'),
    meta: {
      name: '首页',
      role: ['0', '1', '2']
    }
  },
  {

    path: 'user',
    name: 'user',
    component: () => import('../views/system/user/index.vue'),
    // path: 'system-setting',
    // redirect: { name:'institution-professional'},
    meta: {
      name: '用户管理',
      role: ['0', '1', '2']

    },
  },
  {
    path: 'record',
    redirect: { name: 'getRecordList' },
    meta: {
      name: '人员档案',
      role: ['0', '1', '2']
    },
    children: [
      {
        path: 'addRecord',
        name: 'addRecord',
        component: () => import('../views/system/record/recordTab/index.vue'),
        meta: {
          name: '新建人员',
          role: ['0', '1', '2']
        }
      },

    ]
  },

  {
    path: 'record',
    redirect: { name: 'getRecordList' },
    meta: {
      name: '赛事操作',
      role: ['0', '1', '2']
    },
    children: [
      {
        path: 'addRecord',
        name: 'addRecord',
        component: () => import('../views/system/record/recordTab/index.vue'),
        meta: {
          name: '赛事预约',
          role: ['0', '1', '2']
        }
      },

    ]
  },


]

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: "/login/lo",
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('../views/system/home.vue'),
    redirect: "/home/analysis",
    meta: {
      isAut2equired: true
    },
    children: homeChildren
  },
  {
    path: '/login/lo',
    name: 'login',
    component: () => import('../views/login/index.vue'),
  },
  {
    path: '/login/register',
    name: 'register',
    component: () => import('../views/register/register.vue')
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  const useUserStore = useUser()

  localStorage.getItem('token') ? useUserStore.updateIsLogin(true) : useUserStore.updateIsLogin(false)

  //判断是否登录
  if (to.meta.isAut2equired && useUserStore.isLogin === false) {
    console.log("还未登录");
    ElMessage({
      message: '还未登录，请先登录！',
      type: 'warning',
    })
    return next('/login/lo');
  } else {
    next();
  }
})



export default router
