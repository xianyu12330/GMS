<template>
  <!-- <button @click="loginOut">退出登录</button> -->

  <div class="home">
    <div class="head">
      <div class="name">
        <div class="icon">
          <svg t="1670136839839" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
            p-id="7227" width="25" height="25">
            <path
              d="M512 1024v-83.2h512V1024H512z m0-283.2h512v83.2H512v-83.2z m0-200h512v83.2H512v-83.2z m395.296-102.4h-113.408A291.808 291.808 0 1 0 436.16 793.216v113.504a399.168 399.168 0 0 1-152.032-63.296l-82.336 82.304-103.456-103.456 82.336-82.336a400.608 400.608 0 0 1-64-154.752H0v-146.304h116.704a398.752 398.752 0 0 1 64-154.752L98.368 201.792l103.456-103.456L284.16 180.672a400.896 400.896 0 0 1 154.752-64V0h146.304v116.544a400 400 0 0 1 154.752 64l82.336-82.304 103.456 103.456-82.304 82.336a401.056 401.056 0 0 1 63.84 154.368z"
              fill="#0590DF" p-id="7228"></path>
          </svg>
        </div>
        <p>体育馆管理系统</p>
      </div>
      <div class="logout">
        <div class="head-img"></div>
        <div class="lgo">
          <el-button type="info" @click="loginOut" round size="small">| -> 退出 </el-button>
        </div>
      </div>
    </div>
    <div class="body">
      <div class="menu">
        <Menu></Menu>
      </div>
      <div class="content">
        <Bar></Bar>
        <RouterView class="view"></RouterView>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter, RouterView } from "vue-router";
import { useUser } from "@/stores/index";
import Menu from "@/components/common/menu.vue";
import { ElMessage } from "element-plus";
import Bar from "@/components/content/bar.vue";

const useUserStore = useUser();
const router = useRouter();
const loginOut = () => {
  localStorage.setItem("token", "");
  useUserStore.updateIsLogin(false);
  useUserStore.setUserInformation({});
  localStorage.setItem("user", "");
  ElMessage({
    message: "已退出！",
    type: "success",
  });
  router.push({ name: "login" });
};
</script>
 
<style lang="less" scoped>
.home {
  max-width: 1980px;
  height: 100vh;
  background-color: #f2f3f5;
  margin: 0 auto;

  .head {
    height: 60px;
    background-color: #ffffff;
    padding: 0 20px;
    line-height: 60px;
    border-bottom: 1px solid #e5e6eb;

    .name {
      float: left;

      .icon {
        float: left;
        display: flex;
        flex-direction: column;
        justify-content: center;
        line-height: 60px;
        font-size: 14px;
        height: 60px;
      }

      p {
        float: left;
        font-size: 14px;
        margin-left: 10px;
        font-weight: 700;
        letter-spacing: 1px;
      }
    }

    .head-img {
      float: right;
      width: 32px;
      height: 32px;
      background-color: red;
      margin: 14px 0;
      border-radius: 50%;
      background-image: url("@/assets/image/head.png");
      background-size: cover;
    }
  }

  .body {
    height: calc(100% - 60px);
    display: flex;

    .menu {
      min-height: calc(100% - 60px);
      width: 220px;
      background-color: #fff;
    }

    .content {
      display: flex;
      flex: 1;
      flex-direction: column;
      min-height: calc(100% - 60px);
      overflow: auto;
      // background-color: red;
      padding: 0 20px;

      .view {
        flex: 1;
        background-color: #fff;
        border-radius: 4px;
        padding: 20px;
      }
    }
  }
}
.logout {
  float: right;
  margin-right: 10px;
}
.lgo {
  float: right;
  margin-right: 10px;
  margin-top: -2px;
}
</style>