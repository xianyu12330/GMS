
<template>
  <el-form :model="formData" ref="vForm" :rules="rules" label-position="left" label-width="80px" size="default"
    @submit.prevent>

    <el-row>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="器材名" prop="ename" class="required">
          <el-input v-model="formData.ename" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="器材价格" prop="money" class="label-right-align">
          <el-input v-model="formData.money" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="剩余数量" prop="count" class="label-right-align">
          <el-input type="text" clearable v-model="formData.count"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="9" class="grid-cell">
        <div class="demo-datetime-picker">
          <div class="block">
            <span class="demonstration">起始时间</span>
            <!-- <el-date-picker v-model="formData.borrowBegin" type="date" placeholder="选择起始时间" :size="size" /> -->
            <el-date-picker v-model="formData.borrowBegin" type="datetime" placeholder="选择起始时间" @change="getBeginTime"
              disabled />
          </div>
        </div>
      </el-col>
      <el-col :span="9" class="grid-cell">
        <div class="demo-datetime-picker">
          <div class="block">
            <span class="demonstration">结束时间</span>
            <!-- <el-date-picker v-model="formData.borrowEnd" type="date" placeholder="选择结束时间" :size="size" /> -->
            <el-date-picker v-model="formData.borrowEnd" type="datetime" placeholder="选择结束时间" @change="getEndTime"
              disabled />
          </div>
        </div>
      </el-col>
    </el-row>

  </el-form>
  <div class="subdiv">
    <el-button class="sub" @click="submitForm">修改</el-button>
  </div>
</template>

<script lang="ts">
import { ref, onMounted, computed } from "vue";
import { defineComponent, toRefs, reactive, getCurrentInstance } from "vue";
import { useUser } from "@/stores/index";
import { ElMessage } from "element-plus";
import router from "@/router";
import { changeState, insertPlace, PlaceByID } from "@/network/module/address";
import moment from "moment";
import { useRoute } from "vue-router";
import { insertBorrow } from "@/network/module/borrow";
import { EquipByID } from "@/network/module/equipment";
const useUserStore = useUser();
export default defineComponent({
  components: {},
  props: {},
  setup() {
    const route = useRoute();
    const message = reactive({
      formData: {
        username: "",
        ename: "",
        money: 0,
        eNum: 0,
        count: 0,
        borrowBegin: "",
        borrowEnd: "",
      },
      place: {
        state: 1,
      },
      rules: {
        name: [
          {
            required: true,
            message: "字段值不可为空",
          },
        ],
      },
    });

    const getList = (id: any) => {
      EquipByID(id).then((res) => {
        console.log(res.data[0]);

        message.formData = res.data[0];
        message.formData.username = useUserStore.userInformation.name;
      });
    };

    const instance: any = getCurrentInstance();
    const submitForm = () => {
      console.log(message.formData);
      getBeginTime;
      getEndTime;
      insertBorrow(message.formData).then((res) => {
        if ((res as any).code == 1010) {
          changeState(route.query.id, message.place.state);
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
        router.push({ name: "AddressList" });
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
      console.log(route);
      getList(route.query.id);
    });

    return {
      ...toRefs(message),
      submitForm,
      resetForm,
      getList,
      getBeginTime,
      getEndTime,
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
  width: 450px;
}

.subdiv {
  margin: 0 auto;
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