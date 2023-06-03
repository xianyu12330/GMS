
<template>
  <el-form :model="formData" ref="vForm" :rules="rules" label-position="left" label-width="80px" size="default"
    @submit.prevent>

    <el-row>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="赛事名称" class="required">
          <el-input v-model="formData.gameName" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="比赛地点" label-width="80px" class="required label-right-align">
          <el-select v-model="formData.gameAdd" class="full-width-input" clearable>
            <el-option v-for="(item, index) in address" :key="index" :label="item.addressName"
              :value="item.addressName">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="比赛器材" label-width="80px" class="required label-right-align">
          <el-select v-model="formData.gameEquip" class="full-width-input" clearable @change="canBorrow">
            <el-option v-for="(item, index) in equipment" :key="index" :label="item.ename" :value="item.ename">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="剩余数量" class="required">
          <el-input v-model="formData.count" type="text" clearable disabled> </el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="借用数量" class="required">
          <el-input v-model="formData.borrowNum" type="text" clearable @change="small"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="比赛项目" prop="gameEvent" class="label-right-align">
          <el-input v-model="formData.gameEvent" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="9" class="grid-cell">
        <div class="demo-datetime-picker">
          <div class="block">
            <span class="demonstration">起始时间</span>

            <el-date-picker v-model="formData.gameBeginTime" type="datetime" placeholder="选择起始时间"
              @change="getBeginTime" />
          </div>
        </div>
      </el-col>
      <el-col :span="9" class="grid-cell">
        <div class="demo-datetime-picker">
          <div class="block">
            <span class="demonstration">结束时间</span>
            <!-- <el-date-picker v-model="formData.borrowEnd" type="date" placeholder="选择结束时间" :size="size" /> -->
            <el-date-picker v-model="formData.gameEndTime" type="datetime" placeholder="选择结束时间" @change="getEndTime" />
          </div>
        </div>
      </el-col>

      <el-col :span="4" class="grid-cell">
      </el-col>
    </el-row>

  </el-form>
  <div class="subdiv">
    <el-button class="sub" @click="submitForm">提交</el-button>
  </div>
</template>

<script lang="ts">
import { ref, onMounted, computed } from "vue";

import { addUser, userByName } from "@/network/module/user";
import { defineComponent, toRefs, reactive, getCurrentInstance } from "vue";
import { useUser } from "@/stores/index";
import { ElMessage } from "element-plus";
import router from "@/router";
import { changeState, GetFreePlace, getPlaces } from "@/network/module/address";
import {
  changeEquipState,
  GetCount,
  GetFreeEquip,
} from "@/network/module/equipment";
import { insertGame } from "@/network/module/game";
import moment from "moment";
import changeEquipmentVue from "../equipment/changeEquipment.vue";

const useUserStore = useUser();
let address = ref<any>("");
let equipment = ref<any>("");
export default defineComponent({
  components: {},
  props: {},

  setup() {
    const username = useUserStore.userInformation.name;
    const state = reactive({
      formData: {
        gameName: "",
        gameBeginTime: "",
        gameEndTime: "",
        gameAdd: "",
        gameEvent: "",
        gameEquip: "",
        count: 0,
        borrowNum: 0,
        agree: 0,
      },
      record: {
        record_name: "",
        create_time: "",
      },
      place: {
        AddressName: "",
        state: 1,
        money: 0,
      },
      equipment: {
        ename: "",
        money: 0,
        count: 0,
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
    const instance: any = getCurrentInstance();
    const submitForm = () => {
      state.place.AddressName = state.formData.gameAdd;
      insertGame(state.formData).then((res) => {
        changeState(state.place.AddressName, state.place.state);
        console.log(state.formData.gameEquip);
        state.equipment.ename = state.formData.gameEquip;
        state.equipment.count = state.formData.count - state.formData.borrowNum;
        changeEquipState(state.equipment.ename, state.equipment.count, 1);

        if ((res as any).code == 1010) {
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
        router.push({ name: "user" });
      });
    };
    const resetForm = () => {
      instance.ctx.$refs["vForm"].resetFields();
    };
    const getList = () => {
      GetFreePlace().then((res) => {
        address.value = res.data[0];
      });
      GetFreeEquip().then((res) => {
        equipment.value = res.data[0];
      });
    };
    const canBorrow = (value: any) => {
      GetCount(value).then((res) => {
        state.formData.count = res.data[0];
      });
    };
    const small = () => {
      if (state.formData.borrowNum > state.formData.count) {
        ElMessage({
          message: "不能超过最大剩余数",
          type: "error",
        });
        state.formData.borrowNum = 0;
      }
    };
    const getBeginTime = (time: any) => {
      state.formData.gameBeginTime = moment(time).format("YYYY-MM-DD HH:mm:ss");
    };
    const getEndTime = (time: any) => {
      state.formData.gameEndTime = moment(time).format("YYYY-MM-DD HH:mm:ss");
    };
    onMounted(() => {
      state.record.record_name = username;
      getList();
    });
    return {
      ...toRefs(state),
      submitForm,
      resetForm,
      address,
      equipment,
      canBorrow,
      small,
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
</style>