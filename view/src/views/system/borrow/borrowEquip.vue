
<template>
  <el-form :model="formData" ref="vForm" :rules="rules" label-position="left" label-width="80px" size="default"
    @submit.prevent>

    <el-row>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="器材名" prop="ename" class="required">
          <el-input v-model="formData.ename" type="text" clearable disabled></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="器材价格" prop="money" class="label-right-align">
          <el-input v-model="formData.money" type="text" clearable disabled></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="剩余数量" prop="count" class="label-right-align">
          <el-input type="text" clearable v-model="equipment.count" disabled></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="租借数量" prop="enum" class="label-right-align">
          <el-input type="text" clearable v-model="formData.enum" @change="small"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="9" class="grid-cell">
        <div class="demo-datetime-picker">
          <div class="block">
            <span class="demonstration">起始时间</span>
            <!-- <el-date-picker v-model="formData.borrowBegin" type="date" placeholder="选择起始时间" :size="size" /> -->
            <el-date-picker v-model="formData.borrowBegin" type="datetime" placeholder="选择起始时间"
              @change="getBeginTime" />
          </div>
        </div>
      </el-col>
      <el-col :span="9" class="grid-cell">
        <div class="demo-datetime-picker">
          <div class="block">
            <span class="demonstration">结束时间</span>
            <!-- <el-date-picker v-model="formData.borrowEnd" type="date" placeholder="选择结束时间" :size="size" /> -->
            <el-date-picker v-model="formData.borrowEnd" type="datetime" placeholder="选择结束时间" @change="getEndTime" />
          </div>
        </div>
      </el-col>
    </el-row>

  </el-form>
  <div class="subdiv">
    <el-button class="sub" @click="change" type="success">修改</el-button>
    <el-button class="sub" @click="deleteEquip" type="primary">归还</el-button>
  </div>

</template>

<script lang="ts">
import { ref, onMounted, computed } from "vue";
import { defineComponent, toRefs, reactive, getCurrentInstance } from "vue";
import { useUser } from "@/stores/index";
import { ElMessage } from "element-plus";
import router from "@/router";
import moment from "moment";
import { useRoute } from "vue-router";
import {
  BorrowByID,
  DeleteBorrow,
  insertBorrow,
} from "@/network/module/borrow";
import equipment, {
  changeEquipState,
  DeleteEquip,
  EquipByID,
  GetCount,
  updateEquip,
} from "@/network/module/equipment";

export default defineComponent({
  components: {},
  props: {},
  setup() {
    const useUserStore = useUser();
    const route = useRoute();
    const message = reactive({
      formData: {
        borrowid: 0,
        username: "",
        ename: "",
        money: 0,
        enum: 0,
        borrowBegin: "",
        borrowEnd: "",
      },

      borrow: {
        state: 1,
        count: 0,
      },
      equipment: {
        ename: "",
        Buyday: "",
        count: 0,
        money: 0,
        state: 0,
      },
      rules: {
        name: [
          {
            required: true,
            message: "字段值不可为空",
          },
        ],
        eNum: [{}],
      },
    });

    const getList = (id: any) => {
      BorrowByID(id).then((res) => {
        message.formData = res.data[0];
        message.equipment.ename = message.formData.ename;
        message.formData.borrowid = Number(route.query.id);
        GetCount(message.equipment.ename).then((res) => {
          message.equipment.count = res.data[0];
          message.borrow.count = message.formData.enum;
        });
      });
    };

    const instance: any = getCurrentInstance();
    const submitForm = () => {
      getBeginTime;
      getEndTime;
      insertBorrow(message.formData).then((res) => {
        if ((res as any).code == 1010) {
          updateEquip(message.equipment);
          ElMessage({
            message: "增加成功",
            type: "success",
          });
        } else {
          ElMessage({
            message: "增加失败，未知问题",
            type: "error",
          });
        }
        router.push({ name: "manageEquipment" });
      });
    };
    const change = () => {
      message.equipment.ename = message.formData.ename;
      message.equipment.count = message.formData.enum - message.borrow.count;
      if (message.equipment.count == 0) message.equipment.state = 1;
      message.equipment.money = message.formData.money;
      changeEquipState(
        message.equipment.ename,
        message.equipment.count,
        message.equipment.state
      ).then((res) => {
        console.log(res);
        if ((res as any).code == 1030) {
          ElMessage({
            message: "修改成功",
            type: "success",
          });
        } else {
          ElMessage({
            message: "修改失败，未知问题",
            type: "error",
          });
        }
      });
      router.push({ name: "manageEquipment" });
    };
    const small = () => {
      console.log(
        message.formData.enum - message.borrow.count,
        message.equipment.count
      );

      if (
        message.formData.enum - message.borrow.count >
        message.equipment.count
      ) {
        ElMessage({
          message: "不能超过最大剩余数",
          type: "error",
        });
        message.formData.enum = message.borrow.count;
      }
    };
    const deleteEquip = () => {
      DeleteBorrow(message.formData.borrowid).then((res) => {
        if ((res as any).code == 1020) {
          ElMessage({
            message: "归还成功",
            type: "success",
          });
        } else {
          ElMessage({
            message: "归还失败，未知问题",
            type: "error",
          });
        }
        router.push({ name: "borrowList" });
      });
    };
    const resetForm = () => {
      instance.ctx.$refs["vForm"].resetFields();
    };
    const getBeginTime = (time: any) => {
      message.formData.borrowBegin = moment(time).format("YYYY-MM-DD HH:mm:ss");
    };
    const getEndTime = (time: any) => {
      message.formData.borrowEnd = moment(time).format("YYYY-MM-DD HH:mm:ss");
    };
    onMounted(async () => {
      getList(route.query.id);
    });

    return {
      ...toRefs(message),
      submitForm,
      resetForm,
      getList,
      getBeginTime,
      getEndTime,
      useUserStore,
      route,
      change,
      small,
      deleteEquip,
    };
  },
});
</script>

<style lang="less" scoped>
.el-input-number.full-width-input,
.el-cascader.full-width-input {
  width: 100% !important;
}

.el-form-item {
  width: 92%;
}

.el-form-item--medium {
  .el-radio {
    line-height: 36px !important;
  }

  .el-rate {
    margin-top: 8px;
  }
}

.el-form-item--small {
  .el-radio {
    line-height: 32px !important;
  }

  .el-rate {
    margin-top: 6px;
  }
}

.el-form-item--mini {
  .el-radio {
    line-height: 28px !important;
  }

  .el-rate {
    margin-top: 4px;
  }
}

.clear-fix:before,
.clear-fix:after {
  display: table;
  content: "";
}

.clear-fix:after {
  clear: both;
}

.float-right {
  float: right;
}

div.table-container {
  table.table-layout {
    width: 100%;
    table-layout: fixed;
    border-collapse: collapse;

    td.table-cell {
      display: table-cell;
      height: 36px;
      border: 1px solid #e1e2e3;
    }
  }
}

.label-left-align :deep(.el-form-item__label) {
  text-align: left;
}

.label-center-align :deep(.el-form-item__label) {
  text-align: center;
}

.label-right-align :deep(.el-form-item__label) {
  text-align: right;
}

.static-content-item {
  min-height: 20px;
  display: flex;
  align-items: center;

  :deep(.el-divider--horizontal) {
    margin: 0;
  }
}

.sub {
  width: 250px;
}

.subdiv {
  margin: 60px auto;
}
.demo-datetime-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}
.demo-datetime-picker .block {
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}
.demo-datetime-picker .block:last-child {
  border-right: none;
}
</style>