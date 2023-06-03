import { ref, reactive } from 'vue'
import { defineStore } from 'pinia'

export const useUser = defineStore('user', () => {
  // 登录标注
  const isLogin = ref(true)
  function updateIsLogin(flag: boolean) {
    isLogin.value = flag
  }

  const userInformation = reactive({
    password: "",
    name: "",
    role: "",
    cardNum: ""
  })
  function setUserInformation(user: any) {
    userInformation.password = user.password
    userInformation.name = user.name
    userInformation.role = user.role
    userInformation.cardNum = user.cardNum
  }
  function getInformation() {
    return userInformation;
  }

  return { isLogin, updateIsLogin, userInformation, setUserInformation }
})
