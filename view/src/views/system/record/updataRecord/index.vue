
<template>
  <el-form :model="formData" ref="vForm" :rules="rules" label-position="left" label-width="80px" size="default"
    @submit.prevent>
    <el-row>
      <el-col :span="6" class="grid-cell">
        <el-form-item label="一级机构" prop="rL1InstID" class="required">

          <el-select v-model="formData.rL1InstID" class="full-width-input" clearable disabled @change="select1Change">
            <el-option v-for="(item, index) in ins1" :key="index" :label="item.iName" :value="item.iID"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="6" class="grid-cell">
        <el-form-item label="二级机构" prop="rL2InstID">
          <el-select v-model="formData.rL2InstID" class="full-width-input" clearable disabled @change="select2Change">
            <el-option v-for="(item, index) in ins2" :key="index" :label="item.iName" :value="item.iID"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="6" class="grid-cell">
        <el-form-item label="三级机构" prop="rL3InstID">
          <el-select v-model="formData.rL3InstID" class="full-width-input" disabled clearable>
            <el-option v-for="(item, index) in ins3" :key="index" :label="item.iName" :value="item.iID"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="6" class="grid-cell">
        <el-form-item label="职位" prop="positionId" class="required">
          <el-select v-model="formData.positionId" class="full-width-input" disabled clearable>
            <el-option v-for="(item, index) in pos" :key="index" :label="item.pName" :value="item.pID"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="6" class="grid-cell">
        <el-form-item label="薪资标准" prop="job_id" class="required">
          <el-select v-model="formData.salary_id" class="full-width-input" clearable>
            <el-option v-for="(item,index) in standard" :key="index" :label="item.standard_name"
              :value="item.standard_id"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="姓名" prop="rName" class="required">
          <el-input v-model="formData.rName" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="4" class="grid-cell">
        <el-form-item label="性别" label-width="80px" prop="rGender" class="required label-right-align">
          <el-select v-model="formData.rGender" class="full-width-input" clearable>
            <el-option v-for="(item, index) in rGenderOptions" :key="index" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="年龄" prop="rAge" class="label-right-align">
          <el-input v-model="formData.rAge" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="10" class="grid-cell">
        <el-form-item label="邮箱" prop="rEmail" class="label-right-align">
          <el-input v-model="formData.rEmail" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="7" class="grid-cell">
        <el-form-item label="电话" prop="rPhone" class="required">
          <el-input v-model="formData.rPhone" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="7" class="grid-cell">
        <el-form-item label="QQ" prop="rQQ" class="label-right-align">
          <el-input v-model="formData.rQQ" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="10" class="grid-cell">
        <el-form-item label="住址" prop="rAddress" class="label-right-align">
          <el-input v-model="formData.rAddress" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="邮编" prop="rPostcode">
          <el-input v-model="formData.rPostcode" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="9" class="grid-cell">
        <el-form-item label="生日" prop="rBirthday" class="label-right-align">
          <el-date-picker v-model="formData.rBirthday" type="date" class="full-width-input" format="YYYY-MM-DD"
            value-format="YYYY-MM-DD" clearable></el-date-picker>
        </el-form-item>
      </el-col>
      <el-col :span="10" class="grid-cell">
        <el-form-item label="出生地" prop="rBirthplace" class="label-right-align">
          <el-input v-model="formData.rBirthplace" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="7" class="grid-cell">
        <el-form-item label="身份证号" prop="rIdCard" class="required">
          <el-input v-model="formData.rIdCard" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="7" class="grid-cell">
        <el-form-item label="社保号码" prop="rSSC">
          <el-input v-model="formData.rSSC" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="国籍" prop="rCountry" class="required label-right-align">
          <el-input v-model="formData.rCountry" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="民族" prop="rEthnic" class="required label-right-align">
          <el-input v-model="formData.rEthnic" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="宗教信仰" prop="rReligion">
          <el-input v-model="formData.rReligion" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="4" class="grid-cell">
        <el-form-item label="政治面貌" prop="rPolitical" class="required">
          <el-input v-model="formData.rPolitical" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="学历" prop="rEducation">
          <el-input v-model="formData.rEducation" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="学历专业" prop="rMajor" class="label-right-align">
          <el-input v-model="formData.rMajor" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5" class="grid-cell">
        <el-form-item label="爱好" prop="rHobby" class="label-right-align">
          <el-input v-model="formData.rHobby" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="7" class="grid-cell">
        <el-form-item label="开户银行" prop="rBankName" class="required">
          <el-input v-model="formData.rBankName" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="7" class="grid-cell">
        <el-form-item label="银行账户" prop="rBankAccount" class="required">
          <el-input v-model="formData.rBankAccount" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="10" class="grid-cell">
        <el-form-item label="特长" prop="rSpecicalty">
          <el-input v-model="formData.rSpecicalty" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12" class="grid-cell">
        <el-form-item label="简历" prop="rResume">
          <el-input type="textarea" v-model="formData.rResume" rows="3"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12" class="grid-cell">
        <el-form-item label="家庭关系" prop="rFamily">
          <el-input type="textarea" v-model="formData.rFamily" rows="3"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-form-item label="备注" prop="rRemarks">
      <el-input type="textarea" v-model="formData.rRemarks" rows="3"></el-input>
    </el-form-item>

  </el-form>
  <div class="subdiv">
    <el-button class="sub" @click="submitForm">提交修改</el-button>
  </div>
</template>

<script lang="ts">
import { ref, onMounted, computed } from "vue";
import { ElMessage } from "element-plus";
import {
  addRecord,
  SearchRecordById,
  putRecord,
} from "@/network/module/record";
import {
  getLevelInsList,
  getInsByParent,
  getInsName,
} from "@/network/module/institution";
import { getPositionList } from "@/network/module/position";
import { useRoute } from "vue-router";
import { useRouter } from "vue-router";
import { defineComponent, toRefs, reactive, getCurrentInstance } from "vue";
export default defineComponent({
  components: {},
  props: {},
  setup() {
    let ins1 = ref<any>([]);
    let ins2 = ref<any>([]);
    let ins3 = ref<any>([]);
    let pos = ref<any>([]);
    let pi = ref("");

    const route = useRoute();
    const router = useRouter();
    const getlist = (id: any) => {
      SearchRecordById(id).then((res) => {
        state.formData = res.data[0];
        console.log(state.formData);
      });
    };
    const select1Change = (value: any) => {
      getLevel(2, value);
    };
    const select2Change = (value: any) => {
      console.log(value);
      getLevel(3, value);
    };
    const getLevel = async (lv: any, id: any) => {
      if (lv == 1) {
        await getLevelInsList(1).then((res) => {
          ins1.value = res.data[0];
          // 在里面已经有名字了
        });
      } else if (lv == 2) {
        console.log(id);
        await getInsByParent(id).then((res) => {
          console.log(1, res);
          ins2.value = res.data[0];
        });
      } else if (lv == 3) {
        await getInsByParent(id).then((res) => {
          console.log(res);
          ins3.value = res.data[0];
        });
      }
    };
    const getpos = () => {
      getPositionList().then((res) => {
        console.log("dff", res.data[0]);
        pos.value = res.data[0];
      });
    };
    const state = reactive({
      formData: {
        rL1InstID: "",
        rL2InstID: "",
        rL3InstID: "",
        positionId: "",
        rName: "",
        rGender: 1,
        rAge: "",
        rEmail: "",
        rPhone: "",
        rQQ: "",
        rAddress: "",
        rPostcode: "",
        rBirthday: null,
        rBirthplace: "",
        rIdCard: "",
        rSSC: "",
        rCountry: "",
        rEthnic: "",
        rReligion: "",
        rPolitical: "",
        rEducation: "",
        rMajor: "",
        rHobby: "",
        rBankName: "",
        rBankAccount: "",
        rSpecicalty: "",
        rResume: "",
        rFamily: "",
        rRemarks: "",
      },
      rules: {
        rL1InstID: [
          {
            required: true,
            message: "字段值不可为空",
          },
        ],
        pi: [
          {
            required: true,
            message: "字段值不可为空",
          },
        ],
        rName: [
          {
            required: true,
            message: "字段值不可为空",
          },
        ],
        rGender: [
          {
            required: true,
            message: "字段值不可为空",
          },
        ],
        rEmail: [
          {
            pattern: /^([-_A-Za-z0-9.]+)@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/,
            trigger: ["blur", "change"],
            message: "此处填入邮箱",
          },
        ],
        rPhone: [
          {
            required: true,
            message: "字段值不可为空",
          },
          {
            pattern: /^[1][3-9][0-9]{9}$/,
            trigger: ["blur", "change"],
            message: "此处需填电话",
          },
        ],
        rQQ: [
          {
            pattern: /^\d+(\.\d+)?$/,
            trigger: ["blur", "change"],
            message: "填入QQ账号",
          },
        ],
        rIdCard: [
          {
            required: true,
            message: "字段值不可为空",
          },
        ],
        rCountry: [
          {
            required: true,
            message: "字段值不可为空",
          },
          {
            pattern: /^[一-龥]+$/,
            trigger: ["blur", "change"],
            message: "",
          },
        ],
        rEthnic: [
          {
            required: true,
            message: "字段值不可为空",
          },
          {
            pattern: /^[一-龥]+$/,
            trigger: ["blur", "change"],
            message: "",
          },
        ],
        rPolitical: [
          {
            required: true,
            message: "字段值不可为空",
          },
        ],
        rBankName: [
          {
            required: true,
            message: "字段值不可为空",
          },
          {
            pattern: /^[一-龥]+$/,
            trigger: ["blur", "change"],
            message: "",
          },
        ],
        rBankAccount: [
          {
            required: true,
            message: "字段值不可为空",
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
    });
    const instance: any = getCurrentInstance();
    const submitForm = () => {
      console.log("xxx");
      instance.ctx.$refs["vForm"].validate((valid: any) => {
        if (!valid) return;
        putRecord(state.formData).then((res) => {
          if ((res as any).code == 1030) {
            ElMessage({
              message: "更新成功",
              type: "success",
            });
          } else {
            ElMessage({
              message: "更新失败，未知问题",
              type: "error",
            });
          }
          router.push({ name: "getRecordList" });
        });
        //TODO: 提交表单
      });
    };
    const resetForm = () => {
      instance.ctx.$refs["vForm"].resetFields();
    };
    onMounted(async () => {
      getlist(route.params.id);
      await getLevel(1, 0);
      console.log("xxxx" + state.formData.rL1InstID);
      await getLevel(2, state.formData.rL1InstID);
      await getLevel(3, state.formData.rL2InstID);
      getpos();
    });
    return {
      ...toRefs(state),
      submitForm,
      resetForm,
      getLevel,
      select1Change,
      select2Change,
      getpos,
      getlist,
      ins1,
      ins2,
      ins3,
      pos,
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