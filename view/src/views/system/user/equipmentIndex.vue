<template>
  <div class="user">
    <h3>器材管理</h3>
    <div class="nav-tab">
      <el-input class="input" v-model="input" placeholder="器材名称" />
      <el-button @click="select" color="#3c6eff"> 查询</el-button>

    </div>
    <div class="table">
      <el-table :data="list" style="width: 100%">
        <el-table-column prop="name" label="器材名称" />
        <el-table-column prop="count" :formatter="showRole" label="器材数量" />
        <el-table-column prop="cost" label="金额" />
        <el-table-column prop="Buyday" label="购买日期" />
        <el-table-column prop="state" label="状态" />
        <el-table-column fixed="right" label="Operations" width="220">
          <template #default="scope">
            <el-button link type="primary" size="small" @click.prevent="deleteRow(scope.row.user_id)">
              Delete
            </el-button>
            <div style="margin-top: 20px">

            </div>
            <!-- <el-button link type="primary" size="small" @click.prevent="UpdataAuthority(scope.row.uID)">
                            UpdataAuthority
                        </el-button> -->
          </template>
        </el-table-column>
      </el-table>
    </div>

  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive, computed } from "vue";
import { ElMessage } from "element-plus";
import {
  getEquipment,
  EquipByID,
updateEquip,
DeleteEquip
} from "@/network/module/equipment";

const radio = ref<any[]>([]);

let input = ref("");


const updataMsg = () => {
  let msg = "";
  radio.value = [];
  
};

const getList = () => {
  getEquipment().then((res) => {
    console.log(res.data);

    list.value = res.data[0];

    updataMsg();
  });
};

const deleteRow = (uID: any) => {
  console.log("click", uID);
  DeleteEquip(uID).then((res) => {
    console.log(res);
    if ((res as any).code == 1020) {
      ElMessage({
        message: "删除成功",
        type: "success",
      });
    } else {
      ElMessage({
        message: "删除失败，此键可能被其他文档引用",
        type: "error",
      });
    }

    getList();
  });
};
const upRole = (uID: any, role: any) => {
  console.log("uid" + uID + " uA" + role);
  updateEquip(uID).then((res) => {
    getList();
  });
};
const select = () => {
  if (input.value == "") {
    getList();
  } else {
    EquipByID(input.value).then((res) => {
      list.value = res.data;
      console.log(list.value);
    });
  }
};

let list = ref([]);

interface equipment {
  name: string;
  count: number;
  cost: number;
  Buyday:Date;
  state:string;
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