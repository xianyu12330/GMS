<template>
  <div class="menu">
    <el-menu :unique-opened="true" default-active="2" class="el-menu-vertical-demo" text-color="#60646f"
      active-text-color="#3c6eff">
      <div v-for="(item, index) in homeChildren" :key="index">
        <el-sub-menu
          v-if="(item.children && ((item.meta!.role as string[]).includes('0') || (item.meta!.role as string[]).includes(useUserStore.userInformation.role)))"
          :index="index.toString()">
          <template #title>
            <el-icon>
              <img class="icon" :src="'/src/assets/image/' + (item.meta)!.name + '.png'" alt="">
            </el-icon>
            <span>{{ (item.meta)!.name }}</span>
          </template>
          <div v-for="(item2, index2) in item.children" :key="index2">
            <el-menu-item
              v-if="((item2.meta!.role as string[]).includes('0') || (item2.meta!.role as string[]).includes(useUserStore.userInformation.role))"
              @click="jump(item2.name as string)" :index="(index + '-' + index2)">{{ item2.meta!.name
                            }}</el-menu-item>
          </div>
        </el-sub-menu>
        <div
          v-else-if="((item.meta!.role as string[]).includes('0') || (item.meta!.role as string[]).includes(useUserStore.userInformation.role))"
          @click="jump(item.name as string)">
          <el-menu-item :index="index.toString()">
            <el-icon>
              <img class="icon" :src="'/src/assets/image/' + (item.meta)!.name + '.png'" alt="">
            </el-icon>
            <span>{{ (item.meta)!.name }}</span>
          </el-menu-item>
        </div>
      </div>

    </el-menu>
  </div>
</template>

<script setup lang="ts">
import { homeChildren } from "@/router/index";
import { useRouter } from "vue-router";
import { useUser } from "@/stores/index";

const useUserStore = useUser();
const router = useRouter();

const jump = (name: string) => {
  router.push({ name: name });
};
</script>
 
<style lang="less" scoped>
.menu {
  width: 100%;
  background-color: aquamarine;
}

.icon {
  width: 20px;
  height: 20px;
  vertical-align: middle;
}
</style>