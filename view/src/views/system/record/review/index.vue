
<template>
  <div class="position">
    <h3>档案目录</h3>
    <div class="nav-tab">
      <el-input class="input" v-model="xItem.document_id" placeholder="档案ID" />
      <el-input class="input" v-model="xItem.hum_name" placeholder="人员名" />
      <div class="flex flex-wrap items-center">
        <!-- v-model变量接收值，甩到@change函数中的value里 -->
        <el-select v-model="xItem.one_depart_id" class="m-2" placeholder="一级机构" @change="select1Change">
          <el-option v-for="(item, index) in ins1" :key="index" :label="item.department_name"
            :value="item.department_id" />
        </el-select>
      </div>
      <div class="flex flex-wrap items-center">
        <el-select v-model="xItem.two_depart_id" class="m-2" placeholder="二级机构" @change="select2Change">
          <el-option v-for="(item, index) in ins2" :key="index" :label="item.department_name"
            :value="item.department_id" />
        </el-select>
      </div>
      <div class="flex flex-wrap items-center">
        <el-select v-model="xItem.three_depart_id" class="m-2" placeholder="三级机构">
          <el-option v-for="(item, index) in ins3" :key="index" :label="item.department_name"
            :value="item.department_id" />
        </el-select>
      </div>
      <div class="flex flex-wrap items-center">
        <el-select v-model="xItem.state" class="m-2" placeholder="档案状态">
          <el-option v-for="item in optionsCat" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </div>
      <el-button @click="select" color="#3c6eff"> 查询</el-button>
      <el-button type="success" @click="reset"> 重置Res</el-button>
    </div>
    <div class="table">
      <el-table :data="list" style="width: 100%">
        <el-table-column prop="document_id" label="档案id" />
        <el-table-column prop="hum_name" label="姓名" />
        <!-- <el-table-column label="一级机构" width="220">
                    <template #default="scope">
                        {{ getinsname(scope.row.rL1InstID) }}
                    </template>
                </el-table-column> -->
        <el-table-column prop="rL1InstName" label="一级机构" />
        <el-table-column prop="rL2InstName" label="二级机构" />
        <el-table-column prop="rL3InstName" label="三级机构" />
        <el-table-column prop="rStatusName" label="档案状态" />
        <el-table-column fixed="right" label="Operations" width="220">
          <template #default="scope">
            <el-button link type="primary" size="small" @click.prevent="reviewRow(scope.row.document_id)">
              审核
            </el-button>
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
  getHumList,
  deleteHum,
  SearchHumById,
  updateHum,
  vagueSearch,
} from "@/network/module/hum";
import {
  getLevelDepart,
  getDepartByParent,
  getDepartName,
} from "@/network/module/department";
import { useRouter } from "vue-router";
const reviewRow = (data: any) => {
  console.log(data);

  router.push({ name: "reviewInside", params: { id: data } });
};
const router = useRouter();

let list = ref<any>([]);
let listsee = ref([]);
let ins1 = ref<any>("");
let ins2 = ref<any>("");
let ins3 = ref<any>("");
let insCat = ref("");
let dialogVisible = ref(false);
let Title = ref("");
let input = ref("");
let inputname = ref("");
const xItem = reactive({
  document_id: "",
  hum_name: "",
  one_depart_id: "",
  two_depart_id: "",
  three_depart_id: "",
  state: "",
});
const select1Change = (value: any) => {
  getLevel(2, value);
};
const select2Change = (value: any) => {
  console.log(value);
  getLevel(3, value);
};
const getLevel = (lv: any, id: any) => {
  if (lv == 1) {
    getLevelDepart(1).then((res) => {
      ins1.value = res.data[0];
      // 在里面已经有名字了
    });
  } else if (lv == 2) {
    getDepartByParent(id).then((res) => {
      console.log(1, res);
      ins2.value = res.data[0];
    });
  } else if (lv == 3) {
    getDepartByParent(id).then((res) => {
      ins3.value = res.data[0];
    });
  }
};
const seeRow = (data: any) => {
  router.push({ name: "moreRecord", params: { id: data.document_id } });
};

let getLeve1 = computed(() => {
  return async (value: any) => {
    let name = await getDepartName(value);
    console.log(name.data[0]);
    return name.data[0];
  };
});
const select = () => {
  console.log(xItem);
  let data = {
    document_id: xItem.document_id,
    hum_name: xItem.hum_name,
    one_depart_id: xItem.one_depart_id,
    two_depart_id: xItem.two_depart_id,
    three_depart_id: xItem.three_depart_id,
    state: xItem.state,
  };
  vagueSearch(data).then((res) => {
    console.log("xItem", res);
    list.value = res.data[0];
    for (const index in list.value) {
      getDepartName(list.value[index].rL1InstID).then((res) => {
        list.value[index].rL1InstName = res.data[0];
      });
      getDepartName(list.value[index].rL2InstID).then((res) => {
        list.value[index].rL2InstName = res.data[0];
      });
      getDepartName(list.value[index].rL3InstID).then((res) => {
        list.value[index].rL3InstName = res.data[0];
      });
      if (list.value[index].rStatus == 1) {
        list.value[index].rStatusName = "待审核";
      } else if (list.value[index].rStatus == 2) {
        list.value[index].rStatusName = "已审核";
      } else if (list.value[index].rStatus == 3) {
        list.value[index].rStatusName = "已驳回";
      } else if (list.value[index].rStatus == 0) {
        list.value[index].rStatusName = "标记删除";
      }
    }
  });
};
const reset = () => {
  xItem.document_id = "";
  xItem.hum_name = "";
  xItem.one_depart_id = "";
  xItem.two_depart_id = "";
  xItem.three_depart_id = "";
  xItem.state = "";
  getList();
};
const getinsname = async (iID: any) => {
  console.log("dfsdf", iID);
  let iId = "";
  await getDepartName(iID).then((res) => {
    console.log("dsfsd", res.data[0]);
    iId = res.data[0];
    return iId;
  });
};
const getList = () => {
  getHumList().then((res) => {
    list.value = res.data[0];
    for (const index in list.value) {
      getDepartName(list.value[index].one_depart_id).then((res) => {
        list.value[index].rL1InstName = res.data[0];
      });
      getDepartName(list.value[index].two_depart_id).then((res) => {
        list.value[index].rL2InstName = res.data[0];
      });
      getDepartName(list.value[index].three_depart_id).then((res) => {
        list.value[index].rL3InstName = res.data[0];
      });

      if (list.value[index].state == 1) {
        list.value[index].rStatusName = "待审核";
      } else if (list.value[index].state == 2) {
        list.value[index].rStatusName = "已审核";
      } else if (list.value[index].state == 3) {
        list.value[index].rStatusName = "已驳回";
      } else if (list.value[index].state == 0) {
        list.value[index].rStatusName = "标记删除";
      }
    }
  });
  listsee.value = list.value;
};

const deleteRow = (rID: any) => {
  console.log("p", rID);
  deleteHum(rID).then((res) => {
    console.log(res);
    if ((res as any).code == 1020) {
      ElMessage({
        message: "删除成功",
        type: "success",
      });
    } else {
      ElMessage({
        message: "删除失败，未知问题",
        type: "error",
      });
    }
    getList();
  });
};

const updateRow = (data: any) => {
  router.push({ name: "updataRecord", params: { id: data.document_id } });
};
const optionsCat = [
  {
    value: "1",
    label: "待审核",
  },
  {
    value: "2",
    label: "已审核",
  },
  {
    value: "3",
    label: "已驳回",
  },
  {
    value: "0",
    label: "标记删除",
  },
];

//启动时调用
onMounted(() => {
  getList();
  getLevel(1, 0);
});
</script>
 
<style lang="less" scoped>
.items-center {
  margin-right: 20px;
}

.position {
  h3 {
    margin-bottom: 20px;
  }

  .nav-tab {
    margin-bottom: 16px;
    display: flex;
    justify-content: space-between;

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
  margin: 0 15px;
  margin-bottom: 19px;
  width: 450px;
}
</style>