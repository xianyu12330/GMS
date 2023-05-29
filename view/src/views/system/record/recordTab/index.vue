
<template>
  <el-form :model="formData" ref="vForm" :rules="rules" label-position="left" label-width="80px" size="default"
    @submit.prevent>

    <el-row>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="用户名" prop="name" class="required">
          <el-input v-model="formData.name" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="4" class="grid-cell">
        <el-form-item label="性别" label-width="80px" prop="sex" class="required label-right-align">
          <el-select v-model="formData.sex" class="full-width-input" clearable>
            <el-option v-for="(item, index) in rGenderOptions" :key="index" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="10" class="grid-cell">
        <el-form-item label="邮箱" prop="email" class="label-right-align">
          <el-input v-model="formData.email" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="7" class="grid-cell">
        <el-form-item label="电话" prop="phone" class="required">
          <el-input v-model="formData.phone" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="9" class="grid-cell">
        <el-form-item label="生日" prop="birthday" class="label-right-align">
          <el-date-picker v-model="formData.birthday" type="date" class="full-width-input" format="YYYY-MM-DD"
            value-format="YYYY-MM-DD" clearable></el-date-picker>
        </el-form-item>
      </el-col>

      <el-col :span="9" class="grid-cell">
        <el-form-item label="建立时间" prop="create_time" class="label-right-align">
          <el-date-picker v-model="record.create_time" type="date" class="full-width-input" format="YYYY-MM-DD"
            value-format="YYYY-MM-DD" clearable disabled> </el-date-picker>
        </el-form-item>
      </el-col>

      <el-col :span="5" class="grid-cell">
        <el-form-item label="登记人" prop="record_name" class="label-right-align">
          <el-input v-model="record.record_name" type="text" clearable disabled></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="4" class="grid-cell">
        <el-form-item label="用户身份" label-width="80px" prop="sex" class="required label-right-align">
          <el-select v-model="formData.role" class="full-width-input" clearable>
            <el-option v-for="(item, index) in roles" :key="index" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
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

import { addUser, userByName } from "@/network/module/user";
import { defineComponent, toRefs, reactive, getCurrentInstance } from "vue";
import { useUser } from "@/stores/index";
import { ElMessage } from "element-plus";
import router from "@/router";
const useUserStore = useUser();
export default defineComponent({
  components: {},
  props: {},
  setup() {
    let ins1 = ref<any>([]);
    let ins2 = ref<any>([]);
    let ins3 = ref<any>([]);
    let sort = ref<any>([]);
    let job = ref<any>([]);
    let standard = ref<any>([]);

    const username = useUserStore.userInformation.name;
    const state = reactive({
      formData: {
        name: "",
        email: "",
        phone: "",
        birthday: null,
        sex: "",
        role: "",
      },
      record: {
        record_name: "",
        create_time: "",
      },

      rules: {
        name: [
          {
            required: true,
            message: "字段值不可为空",
          },
        ],
        email: [
          {
            pattern: /^([-_A-Za-z0-9.]+)@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/,
            trigger: ["blur", "change"],
            message: "此处填入邮箱",
          },
        ],

        phone: [
          {
            required: true,
            message: "字段值不可为空",
          },
          {
            pattern: /^[1][3-9][0-9]{9}$/,
            trigger: ["blur", "change"],
            message: "此处需填11位电话",
          },
        ],
      },
      rGenderOptions: [
        {
          label: "男",
          value: 1,
        },
        {
          label: "女",
          value: 2,
        },
      ],
      roles: [
        {
          label: "普通用户",
          value: 0,
        },
        {
          label: "管理员",
          value: 1,
        },
        {
          label: "超级管理员",
          value: 2,
        },
      ],
    });
    const instance: any = getCurrentInstance();
    const submitForm = () => {
      addUser(state.formData).then((res) => {
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
      state.record.create_time = getNowDate();
    };
    const getNowDate = (): string => {
      const date = new Date();
      let month: string | number = date.getMonth() + 1;
      let strDate: string | number = date.getDate();

      if (month <= 9) {
        month = "0" + month;
      }

      if (strDate <= 9) {
        strDate = "0" + strDate;
      }

      return (
        date.getFullYear() +
        "-" +
        month +
        "-" +
        strDate +
        " " +
        date.getHours() +
        ":" +
        date.getMinutes() +
        ":" +
        date.getSeconds()
      );
    };
    onMounted(() => {
      state.record.record_name = username;
      getList();
    });
    return {
      ...toRefs(state),
      submitForm,
      resetForm,
      ins2,
      ins3,
      sort,
      job,
      standard,
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