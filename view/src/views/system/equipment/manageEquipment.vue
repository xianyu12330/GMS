<template>
  <div class="user">
    <h3>器材管理</h3>
    <div class="nav-tab">
      <el-input class="input" v-model="input" placeholder="器材名" />
      <el-button color="#3c6eff" @click="select"> 查询</el-button>

    </div>
    <div class="table">
      <el-table :data="list" style="width: 100%">
        <el-table-column prop="ename" label="器材名" />
        <el-table-column prop="money" label="单价" />
        <el-table-column prop="count" label="剩余数量" />
        <el-table-column prop="state" :formatter="showRole" label="状态" />
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

<script setup lang="ts">
import { ref, onMounted, reactive, computed } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import {
  getEquipment,
  DeleteEquip,
  equipByName,
} from "@/network/module/equipment";
import { number } from "echarts";
import { useUser } from "@/stores";
import { insertBorrow } from "@/network/module/borrow";
import router from "@/router";
const useUserStore = useUser();
let input = ref("");
const borrow = reactive({
  name: "",
  ename: "",
  eNum: 0,
});
const showRole = (row: any, column: any, role: number) => {
  let status = "";
  if (role == 2) status = "已被接出";
  else if (role == 1) status = "已报废";
  else status = "空闲";
  return status;
};
const getList = () => {
  getEquipment().then((res) => {
    list.value = res.data[0];
  });
};

const review = (data: any) => {
  console.log(data);
  router.push({
    path: "/home/equipment/bookEquipment",
    query: { id: data.eid, state: data.state },
  });
};

const select = () => {
  if (input.value == "") {
    ElMessage({
      message: "输入不能为空",
      type: "error",
    });
  } else {
    equipByName(input.value).then((res) => {
      list.value = res.data;
      console.log(list.value);
    });
  }
};
const open = (ename: string) => {
  ElMessageBox.prompt("请输入你要租借的数量", "器材租借", {
    confirmButtonText: "OK",
    cancelButtonText: "Cancel",

    inputErrorMessage: "不能超过最大剩余数",
  })
    .then(({ value }) => {
      borrow.eNum = Number(value);
      borrow.ename = ename;
      borrow.name = useUserStore.userInformation.name;
      console.log(borrow);
      insertBorrow(borrow).then((res) => {
        if ((res as any).code == 1010) {
          ElMessage({
            message: "租借成功",
            type: "success",
          });
        } else {
          ElMessage({
            message: "租借失败，未知问题",
            type: "error",
          });
        }
      });
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "Input canceled",
      });
    });
};
let list = ref([]);

interface User {
  date: string;
  name: string;
  address: string;
}

//启动时调用
onMounted(() => {
  getList();
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