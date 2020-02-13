<template>
    <div class="ewm-container">
        <div class="ewm-btn">
            <el-button type="primary" @click="generate()">生成二维码</el-button>
        </div>
        
        <div class="ewm-showArea">
            <iframe :src="url" frameborder="0"></iframe>
        </div>
    </div>
</template>
<script>
import _global from '@/global/global.vue'
export default {
    data(){
        return {
            url:'',
        }
    },
    methods:{
        generate(){
            this.url = _global.ewmApiUrl + 'http://www.baidu.com' + _global.ewmMbid;
        }
    }
}
</script>
<style scoped>
.ewm-container{
    width: 100%;
    height: 100%;
}
.ewm-btn{
    width: 100%;
    height: 50px;
    line-height: 50px;
}
.ewm-showArea{
    width: 100%;
    height: calc(100% - 50px);
}
.ewm-showArea iframe{
    display: block;
    width: 100%;
    height: 100%;
}
</style>
