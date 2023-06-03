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
    path: 'Myborrow',
    name: 'Myborrow',
    meta: {
      name: '我的租用',
      role: ['0', '1', '2']
    }, children: [
      {
        path: 'borrowList',
        name: 'borrowList',
        component: () => import('../views/system/borrow/Myborrow.vue'),
        meta: {
          name: '列表',
          role: ['0', '1', '2']
        }
      },
      {
        path: 'borrowEquip',
        name: 'borrowEquip',
        component: () => import('../views/system/borrow/borrowEquip.vue'),
        meta: {
          name: '租借器材详情',
          role: [NaN]
        }
      },
      {
        path: 'borrowAdd',
        name: 'borrowAdd',
        component: () => import('../views/system/borrow/borrowAdd.vue'),
        meta: {
          name: '租借场地详情',
          role: [NaN]
        }
      },
    ]
  },

  {


    path: 'changePassword',
    name: 'changePassword',
    component: () => import('../views/system/user/changePassword.vue'),
    meta: {
      name: '修改密码',
      role: [NaN]
    }

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
      {

        path: 'user',
        name: 'user',
        component: () => import('../views/system/user/index.vue'),
        meta: {
          name: '用户管理',
          role: ['0', '1', '2']

        },
      },

    ]
  },

  {
    path: 'equipment',
    redirect: { name: 'getequipmentList' },
    meta: {
      name: '器材管理',
      role: ['0', '1', '2']
    },
    children: [
      {
        path: 'addEquipment',
        name: 'addEquipment',
        component: () => import('../views/system/equipment/equipment.vue'),
        meta: {
          name: '新购器材',
          role: ['0', '1', '2']
        }
      },
      {
        path: 'manageEquipment',
        name: 'manageEquipment',
        component: () => import('../views/system/equipment/manageEquipment.vue'),
        meta: {
          name: '器材列表',
          role: ['0', '1', '2']
        }
      },
      {
        path: 'bookEquipment',
        name: 'bookEquipment',
        component: () => import('../views/system/equipment/bookEquipment.vue'),
        meta: {
          name: '器材租用',
          role: [NaN]
        }
      },
      {
        path: 'agreeEquipment',
        name: 'agreeEquipment',
        component: () => import('../views/system/equipment/agreeEquipment.vue'),
        meta: {
          name: '审核器材',
          role: ['1']
        }
      },

    ]
  },

  {
    path: 'address',
    redirect: { name: 'AddressList' },
    meta: {
      name: '场地操作',
      role: ['0', '1', '2']
    },
    children: [
      {
        path: 'addAddress',
        name: 'addAddress',
        component: () => import('../views/system/Address/addAddress.vue'),
        meta: {
          name: '添加场地',
          role: ['0', '1', '2']
        }
      },
      {
        path: 'AddressList',
        name: 'AddressList',
        component: () => import('../views/system/Address/AddressList.vue'),
        meta: {
          name: '场地列表',
          role: ['0', '1', '2']
        }
      },
      {
        path: 'AddressInside',
        name: 'AddressInside',
        component: () => import('../views/system/Address/addressInside.vue'),
        meta: {
          name: '场地内容',
          role: [NaN]
        }
      }
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
        path: 'addGame',
        name: 'addGame',
        component: () => import('../views/system/Game/addGame.vue'),
        meta: {
          name: '赛事创建',
          role: ['0', '1', '2']
        }
      },
      {
        path: 'checkGame',
        name: 'checkGame',
        component: () => import('../views/system/Game/Gamelist.vue'),
        meta: {
          name: '赛事查询',
          role: ['0', '1', '2']
        }

      }

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
