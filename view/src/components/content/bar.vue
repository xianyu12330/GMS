<template>
    <div class="bar">
       <img src="@/assets/image/模块.png" alt=""> /  
       <span>{{ moduleName }}</span>
    </div>
</template>

<script setup lang="ts">
import { onMounted,watch,ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter()

onMounted(()=>{
    moduleName.value = (router.currentRoute.value.meta.name) as string

})
let moduleName = ref('')

watch(() => router.currentRoute.value.meta.name, (newValue) =>{
    moduleName.value = newValue as string
})

</script>
 
<style lang="less" scoped>
.bar {
    height: 24px;
    margin: 16px 0;
    color: #86909c;

    img {
        width: 16px;
        height: 16px;
        vertical-align: middle;
    }

        span {
            margin-left: 2px;
            font-size: 14px;
            color: #4e5969;
        }
}
</style>