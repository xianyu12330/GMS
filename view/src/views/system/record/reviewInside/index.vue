
<template>
  <el-form :model="formData" ref="vForm" :rules="rules" label-position="left" label-width="80px" size="default"
    @submit.prevent>
    <el-row>
      <el-col :span="6" class="grid-cell">
        <el-form-item label="一级机构" prop="one_depart_id" class="required">
          <el-select v-model="formData.one_depart_id" class="full-width-input" clearable @change="select1Change"
            disabled>
            <el-option v-for="(item, index) in ins1" :key="index" :label="item.department_name"
              :value="item.department_id"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="6" class="grid-cell">
        <el-form-item label="二级机构" prop="two_depart_id">
          <el-select v-model="formData.two_depart_id" class="full-width-input" clearable @change="select2Change"
            disabled>
            <el-option v-for="(item, index) in ins2" :key="index" :label="item.department_name"
              :value="item.department_id"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="6" class="grid-cell">
        <el-form-item label="三级机构" prop="three_depart_id">
          <el-select v-model="formData.three_depart_id" class="full-width-input" clearable @change="select3Change"
            disabled>
            <el-option v-for="(item, index) in ins3" :key="index" :label="item.department_name"
              :value="item.department_id"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="6" class="grid-cell">
        <el-form-item label="职位分类" prop="sort_id" class="required">
          <el-select v-model="formData.sort_id" class="full-width-input" clearable @change="selectSort" disabled>
            <el-option v-for="(item, index) in sort" :key="index" :label="item.jobsort_name" :value="item.jobsort_id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>

      <el-col :span="6" class="grid-cell">
        <el-form-item label="职位" prop="job_id" class="required">
          <el-select v-model="formData.job_id" class="full-width-input" clearable disabled>
            <el-option v-for="(item, index) in job" :key="index" :label="item.name" :value="item.job_id"></el-option>
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
        <el-form-item label="姓名" prop="hum_name" class="required">
          <el-input v-model="formData.hum_name" type="text" clearable></el-input>
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
      <el-col :span="5" class="grid-cell">
        <el-form-item label="年龄" prop="age" class="label-right-align">
          <el-input v-model="formData.age" type="text" clearable></el-input>
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
      <el-col :span="5" class="grid-cell">
        <el-form-item label="学历" prop="education">
          <el-input v-model="formData.education" type="text" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="7" class="grid-cell">
        <el-form-item label="银行账户" prop="bank" class="required">
          <el-input v-model="formData.bank" type="text" clearable></el-input>
        </el-form-item>
      </el-col>

      <el-col :span="9" class="grid-cell">
        <el-form-item label="建档时间" prop="create_time" class="label-right-align">
          <el-date-picker v-model="formData.create_time" type="date" class="full-width-input" format="YYYY-MM-DD"
            value-format="YYYY-MM-DD" clearable disabled></el-date-picker>
        </el-form-item>
      </el-col>

      <el-col :span="5" class="grid-cell">
        <el-form-item label="登记人" prop="record_name" class="label-right-align">
          <el-input v-model="record.record_name" type="text" clearable disabled></el-input>
        </el-form-item>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="12" class="grid-cell">
        <el-form-item label="简历" prop="resume">
          <el-input type="textarea" v-model="formData.resume " rows="3"></el-input>
        </el-form-item>
      </el-col>

    </el-row>
    <el-form-item label="备注" prop="remark">
      <el-input type="textarea" v-model="formData.remark" rows="3"></el-input>
    </el-form-item>

  </el-form>
  <div class="btn">
    <el-button class="control" type="danger" @click="reject">驳回</el-button>
    <el-button class="control" type="primary" @click="agree">通过审核 -></el-button>
  </div>

</template>

<script lang="ts">
import { ref, onMounted, computed } from "vue";
import { ElMessage } from "element-plus";
import { addHum, SearchHumById, updateHum } from "@/network/module/hum";
import {
  getLevelDepart,
  getDepartByParent,
  getDepartName,
  sortByDepart,
} from "@/network/module/department";
import { userById } from "@/network/module/user";
import { getSortList, getJobBySort } from "@/network/module/jobsort";
import { reviewHum } from "@/network/module/hum";
import { useRoute } from "vue-router";
import { useRouter } from "vue-router";
import { GetStandardList } from "@/network/module/salaryStandard";
import { defineComponent, toRefs, reactive, getCurrentInstance } from "vue";
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
    const route = useRoute();
    const router = useRouter();
    const getlist = (id: any) => {
      SearchHumById(id).then((res) => {
        state.formData = res.data[0];
        userById(state.formData.recorder_id).then((res) => {
          state.record.record_name = res.data[0].name;
        });
      });
      GetStandardList().then((res) => {
        standard.value = res.data[0];
        console.log(standard.value);
      });
    };
    const reject = () => {
      reviewHum(route.params.id, 3).then((res) => {
        if ((res as any).code == 1030) {
          ElMessage({
            message: "审核成功",
            type: "success",
          });
        } else {
          ElMessage({
            message: "审核失败",
            type: "error",
          });
        }
      });
      router.push({ name: "reviewRecord" });
    };
    const agree = () => {
      reviewHum(route.params.id, 2).then((res) => {
        if ((res as any).code == 1030) {
          ElMessage({
            message: "审核成功",
            type: "success",
          });
        } else {
          ElMessage({
            message: "审核失败",
            type: "error",
          });
        }
      });
      router.push({ name: "reviewRecord" });
    };
    const select1Change = (value: any) => {
      getLevel(2, value);
    };
    const select2Change = (value: any) => {
      console.log(value);
      getLevel(3, value);
    };
    const select3Change = (value: any) => {
      getSort(value);
    };
    const selectSort = (value: any) => {
      getJob(value);
    };
    const getLevel = async (lv: any, id: any) => {
      if (lv == 1) {
        await getLevelDepart(1).then((res) => {
          ins1.value = res.data[0];
          console.log(11111, ins1.value);

          // 在里面已经有名字了
        });
      } else if (lv == 2) {
        // console.log(id);
        await getDepartByParent(id).then((res) => {
          ins2.value = res.data[0];
        });
      } else if (lv == 3) {
        await getDepartByParent(id).then((res) => {
          ins3.value = res.data[0];
        });
      }
    };
    const getSort = (id: any) => {
      sortByDepart(id).then((res) => {
        sort.value = res.data[0];
      });
    };
    const getJob = (id: any) => {
      getJobBySort(id).then((res) => {
        job.value = res.data[0];
      });
    };
    const state = reactive({
      formData: {
        document_id: "",
        one_depart_id: "",
        two_depart_id: "",
        three_depart_id: "",
        sort_id: "",
        job_id: "",
        hum_name: "",
        sex: 1,
        age: "",
        email: "",
        phone: "",
        birthday: null,
        education: "",
        bank: "",
        resume: "",
        remark: "",
        create_time: "",
        recorder_id: "",
        register_id: "",
        salary_id: "",
      },
      record: {
        record_name: "",
      },

      rules: {
        one_depart_id: [
          {
            required: true,
            message: "字段值不可为空",
          },
        ],
        sort_id: [
          {
            required: true,
            message: "字段值不可为空",
          },
        ],
        hum_name: [
          {
            required: true,
            message: "字段值不可为空",
          },
        ],
        sex: [
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
            message: "此处需填电话",
          },
        ],

        bank: [
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
    const resetForm = () => {
      instance.ctx.$refs["vForm"].resetFields();
    };
    onMounted(async () => {
      getlist(route.params.id);
      await getLevel(1, 0);
      await getLevel(2, state.formData.one_depart_id);
      await getLevel(3, state.formData.two_depart_id);
      getSort(state.formData.three_depart_id);
      getJob(state.formData.sort_id);
    });
    return {
      ...toRefs(state),
      resetForm,
      getLevel,
      select1Change,
      select2Change,
      select3Change,
      selectSort,
      getlist,
      reject,
      agree,
      ins1,
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

.btn {
  margin-left: 80px;
}
</style>