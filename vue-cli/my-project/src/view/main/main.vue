<template>
    <div class="main-container">
        <div class="main-header">
            <mainHeaderCom></mainHeaderCom>
        </div>
        <!-- <div class="main-nav">
            <navCom></navCom>
        </div> -->
        <div class="main-content">
            <router-view/>
        </div>
        
    </div>
</template>
<script>
import mainHeader from '@/components/mainHeader.vue';
import nav from '@/components/nav.vue';
export default {
    components:{
        mainHeaderCom: mainHeader,
        navCom: nav,
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
    position: relative;
}
.main-header{
    width: 100%;
    height: 50px;
    background-color: #409EFF;
    /* box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04); */
}
.main-content{
    /* width: calc(100% - 280px); */
    width: 100%;
    height: calc(100% - 50px);
    /* background-color: #ccc; */
    position: absolute;
    top: 50px;
    right: 0px;
}
.main-nav{
    width: 80%;
    height: 80px;
    /* border-right: 1px solid #409EFF;
    box-sizing: border-box;
    border-collapse: collapse; */
    position: absolute;
    bottom: 0;
    left: 10%;
}
</style>
