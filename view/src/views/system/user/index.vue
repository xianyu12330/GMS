<template>
  <div class="user">
    <h3>用户管理</h3>
    <div class="nav-tab">
      <el-input class="input" v-model="input" placeholder="人员用户名" />
      <el-button @click="select" color="#3c6eff"> 查询</el-button>

    </div>
    <div class="table">
      <el-table :data="list" style="width: 100%">
        <el-table-column prop="name" label="用户名" />
        <el-table-column prop="role" :formatter="showRole" label="角色" />
        <el-table-column fixed="right" label="Operations" width="220">
          <template #default="scope">
            <el-button link type="primary" size="small" @click.prevent="deleteRow(scope.row.user_id)">
              Delete
            </el-button>
            <div style="margin-top: 20px">
              <el-radio-group v-model="radio[scope.$index]" size="small">
                <el-radio-button label="AD1" @click.prevent="upRole(scope.row.user_id, 1)" />
                <el-radio-button label="MA2" @click.prevent="upRole(scope.row.user_id, 2)" />
                <el-radio-button label="US3" @click.prevent="upRole(scope.row.user_id, 3)" />
              </el-radio-group>
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
  getUserList,
  deleteUser,
  updateRole,
  userByName,
} from "@/network/module/user";

const radio = ref<any[]>([]);

let input = ref("");
const showRole = (row: any, column: any, role: number) => {
  let status = "";
  if (role == 2) status = "超级管理员";
  else if (role == 1) status = "管理员";
  else status = "普通用户";
  return status;
};

const updataMsg = () => {
  let msg = "";
  radio.value = [];
  list.value.forEach((item) => {
    msg = "";
    if ((item as any).role == 1) {
      msg = "AD1";
    } else if ((item as any).role == 2) {
      msg = "MA2";
    } else {
      msg = "US3";
    }
    radio.value = [...radio.value, msg];
  });
};

const getList = () => {
  getUserList().then((res) => {
    list.value = res.data[0];

    updataMsg();
  });
};

const deleteRow = (uID: any) => {
  console.log("click", uID);
  deleteUser(uID).then((res) => {
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
  updateRole(uID, role).then((res) => {
    getList();
  });
};
const select = () => {
  if (input.value == "") {
    getList();
  } else {
    userByName(input.value).then((res) => {
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