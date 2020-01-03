<template>
    <div class="main-container">
        <div class="main-header">
            <mainHeaderCom></mainHeaderCom>
        </div>
        <div class="main-content">
            <router-view/>
        </div>
        
    </div>
</template>
<script>
import mainHeader from '@/components/mainHeader.vue';
export default {
    components:{
        mainHeaderCom: mainHeader
    },
    data(){
        return {
        }
    },
   
    mounted(){
        this.$router.push({path:'/organManage'});
    },
    methods:{
    }
}
</script>
<style scoped>
.main-container{
    width: 100%;
    height: 100%;
}
.main-header{
    width: 100%;
    height: 50px;
    background-color: #409EFF;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
}
.main-content{
    width: 100%;
    height: calc(100% - 50px);
}

</style>
