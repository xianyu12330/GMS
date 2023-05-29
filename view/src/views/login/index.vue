<template>
  <div class="body">
    <div class="handle">
      <div>
        <h3>欢迎登录</h3>

        <div class="input">
          <p>用户工号(账号)</p>
          <input placeholder="请输入工号" type="text" v-model="user.name">
        </div>
        <div class="input">
          <p>密码</p>
          <input placeholder="请输入密码" type="password" v-model="user.password">
        </div>
        <p class="register">
          还没有账号？
          <router-link to="/login/register">
            <span>立即注册</span>
          </router-link>

        </p>
        <button class="login" @click="login">登录</button>
      </div>

    </div>
    <!-- <button @click="login">登录</button> -->
  </div>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";
import { reactive } from "vue";
import { useUser } from "@/stores/index";
import authentication from "@/network/module/login";
import { ElMessage } from "element-plus";

const useUserStore = useUser();
const router = useRouter();

const user = reactive({
  name: "",
  password: "",
});

const login = async () => {
  authentication.login(user).then((res) => {
    if (res.data[0]) {
      localStorage.setItem("token", res.data[0]);
      res.data[1].role = res.data[1].role.toString();
      useUserStore.updateIsLogin(true);
      useUserStore.setUserInformation(res.data[1]);
      localStorage.setItem("user", JSON.stringify(res.data[1]));
      ElMessage({
        message: "登录成功，正在跳转页面！",
        type: "success",
      });

      router.push({ name: "home" });
    }
  });
};
</script>
 
<style lang="less" scoped>
.body {
  display: flex;
  justify-content: space-between;
  height: 500px;
  // border: 1px solid #333;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  overflow: hidden;
  box-shadow: 0px 12px 32px 4px rgba(0, 0, 0, 0.04),
    0px 8px 20px rgba(0, 0, 0, 0.08);
}

.bg,
.handle {
  width: 400px;
  height: 500px;
}

.bg {
  background-image: url("../../assets/image/img.jpg");
  background-size: auto 100%;
  background-color: #5960e3;
}

.bg::after {
  display: inherit;
  content: "";
  width: 100%;
  height: 100%;
  background-color: rgba(96, 98, 228, 0.4);
}

.handle {
  padding: 40px;
  padding-top: 60px;
  text-align: left;

  h3 {
    font-weight: 700;
    font-size: 24px;
    margin-bottom: 40px;
  }

  .tip {
    display: flex;
    justify-content: center;
    align-items: center;

    margin-top: 14px;
    margin-bottom: 34px;
    font-size: 14px;
    color: #d1d5db;

    span {
      vertical-align: middle;
    }

    i {
      width: 50px;
      height: 1px;
      line-height: 20px;
      background-color: #d1d5db;
      margin: 14px;
    }
  }

  .input {
    height: 80px;
    // border: 1px solid #333;
    margin-top: 10px;

    p {
      font-size: 14px;
      text-align: left;
      font-weight: 1000;
      font-family: inherit;
    }

    input {
      width: 100%;
      margin-top: 10px;
      height: 38px;
      border-radius: 13px;
      border: 0;
      border-bottom: 1px solid rgba(209, 213, 219, 1);
      outline: 0;
      padding: 0 16px;
      font-size: 13px;
    }
  }

  .register {
    text-align: right;
    font-size: 12px;
    margin-top: 6px;
    color: #6b7280;

    span {
      color: #818cf8;
    }
  }

  .login {
    width: 100%;
    height: 40px;
    border: 0;
    background-color: #3064ec;
    color: #fff;
    letter-spacing: 5px;
    font-weight: 700;
    font-size: 13px;
    margin-top: 24px;
    cursor: pointer;
  }
}
</style>