<template>
  <div class="user">
    <h3>赛事一览</h3>
    <div class="nav-tab">
      <el-input class="input" v-model="input" placeholder="比赛名" />
      <el-button color="#3c6eff" @click="select"> 查询</el-button>

    </div>
    <div class="table">
      <el-table :data="list" style="width: 100%">
        <el-table-column prop="gameName" label="赛事名" />
        <el-table-column prop="gameBeginTime" label="赛事开始时间" />
        <el-table-column prop="gameEndTime" label="赛事结束时间" />
        <el-table-column prop="gameAdd" label="赛事地点" />
        <el-table-column prop="gameEvent" label="比赛项目" />
        <!-- <el-table-column fixed="right" label="Operations" width="220">
          <template #default="scope">
            <el-button text @click="open(scope.row.ename)" type="primary">点击租借</el-button>
            <el-button link @click="deleteRow(scope.row.eid)" type="danger" size="small">
              回收删除
            </el-button>
          </template>
        </el-table-column> -->
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
import { GameByName, getGame } from "@/network/module/game";
const useUserStore = useUser();
let input = ref("");
const borrow = reactive({
  name: "",
  ename: "",
  eNum: 0,
});
const getList = () => {
  getGame().then((res) => {
    console.log(res.data);
    list.value = res.data[0];
  });
};

// const upRole = (uID: any, role: any) => {
//   console.log("uid" + uID + " uA" + role);
//   updateRole(uID, role).then((res) => {
//     getList();
//   });
// };
const select = () => {
  if (input.value == "") {
    ElMessage({
      message: "输入不能为空",
      type: "error",
    });
  } else {
    GameByName(input.value).then((res) => {
      list.value = res.data;
      console.log(list.value);
    });
  }
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