<template>
  <div class="user">
    <h3>我的租用</h3>
    <div class="nav-tab">

    </div>
    <div class="table">
      <el-table :data="equipment" style="width: 100%">
        <el-table-column prop="ename" label="器材名" />
        <el-table-column prop="money" label="金额" />
        <el-table-column prop="borrowBegin" label="开始时间" />
        <el-table-column prop="borrowEnd" label="结束时间" />
        <el-table-column fixed="right" label="Operations" width="220">
          <template #default="scope">
            <el-button link @click.prevent="reviewEquip(scope.row)" type="success" size="small">
              查看详情
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="table">
      <el-table :data="address" style="width: 100%">
        <el-table-column prop="addressName" label="场地名" />
        <el-table-column prop="money" label="金额" />
        <el-table-column prop="borrowBegin" label="开始时间" />
        <el-table-column prop="borrowEnd" label="结束时间" />

        <el-table-column fixed="right" label="Operations" width="220">
          <template #default="scope">
            <el-button link @click.prevent="review(scope.row)" type="success" size="small">
              查看详情
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

  </div>
</template>

<script  lang="ts">
import {
  ref,
  onMounted,
  reactive,
  computed,
  defineComponent,
  toRefs,
} from "vue";
import { ElMessage, ElMessageBox } from "element-plus";

import { number } from "echarts";
import { useUser } from "@/stores";
import { BorrowByUser, getBorrow, insertBorrow } from "@/network/module/borrow";
import { GameByName, getGame } from "@/network/module/game";
import { BorrowAddByName } from "@/network/module/borrowAdd";
import router from "@/router";
export default defineComponent({
  setup() {
    const useUserStore = useUser();
    let input = ref("");
    const borrow = reactive({
      name: "",
      ename: "",
      eNum: 0,
    });
    const getList = () => {
      borrow.name = useUserStore.userInformation.name;
      console.log(borrow.name);

      BorrowByUser(borrow.name).then((res) => {
        equipment.value = res.data[0];
      });
      BorrowAddByName(borrow.name).then((res) => {
        address.value = res.data[0];
      });
    };

    const review = (data: any) => {
      console.log(data);
      router.push({
        path: "/home/Myborrow/borrowAdd",
        query: { id: data.borrowAdd_id },
      });
    };
    const reviewEquip = (data: any) => {
      router.push({
        path: "/home/Myborrow/borrowEquip",
        query: { id: data.borrowId },
      });
    };
    let equipment = ref([]);
    let address = ref([]);
    interface User {
      date: string;
      name: string;
      address: string;
    }

    //启动时调用
    onMounted(() => {
      getList();
    });
    return {
      ...toRefs(borrow),
      review,
      reviewEquip,
      equipment,
      address,
    };
  },
});
</script>
 
<style lang="less" scoped>
.user {
  h3 {
    margin-bottom: 20px;
  }

  .nav-tab {
    margin-bottom: 16px;
    display: flex;
    justify-content: end;

    .input {
      width: 200px;
      margin-right: 20px;
    }
  }
}

.table {
  padding: 10px;
  border: 1px solid #ebebeb;
}

.add_name {
  margin: 0 auto;
  margin-bottom: 19px;
  width: 450px;
}

.add_password {
  margin: 0 auto;
  width: 450px;
}
</style>