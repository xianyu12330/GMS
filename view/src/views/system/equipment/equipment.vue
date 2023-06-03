
<template>
  <el-form :model="formData" ref="vForm" label-position="left" label-width="80px" size="default" @submit.prevent>

    <el-row>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="器材名称" prop="Ename" class="required">
          <el-input v-model="formData.ename" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="数量" prop="count" class="required">
          <el-input v-model="formData.count" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="金额" prop="cost" class="required">
          <el-input v-model="formData.money" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="9" class="grid-cell">
        <el-form-item label="购买日期" prop="Buyday" class="label-right-align">
          <el-date-picker v-model="formData.Buyday" type="date" class="full-width-input" format="YYYY-MM-DD"
            value-format="YYYY-MM-DD" clearable></el-date-picker>
        </el-form-item>
      </el-col>
    </el-row>

  </el-form>
  <div class="subdiv">
    <el-button class="sub" @click="submitForm">提交</el-button>
  </div>
</template>

<script lang="ts">
import { ref, onMounted, computed } from "vue";

import { defineComponent, toRefs, reactive, getCurrentInstance } from "vue";
import { useUser } from "@/stores/index";
import { ElMessage } from "element-plus";
import router from "@/router";
import { insertEquip } from "@/network/module/equipment";
const useUserStore = useUser();
export default defineComponent({
  components: {},
  props: {},
  setup() {
    const state = reactive({
      formData: {
        ename: "",
        count: 0,
        Buyday: "",
        money: "",
        state: 0,
      },
    });
    const instance: any = getCurrentInstance();
    const submitForm = () => {
      insertEquip(state.formData).then((res) => {
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
        router.push({ name: "manageEquipment" });
      });
    };
    const resetForm = () => {
      instance.ctx.$refs["vForm"].resetFields();
    };

    onMounted(() => {});
    return {
      ...toRefs(state),
      submitForm,
      resetForm,
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