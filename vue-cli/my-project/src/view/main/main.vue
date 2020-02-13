<template>
    <div class="main-container">
        <div class="main-header">
            <mainHeaderCom></mainHeaderCom>
        </div>
        <div class="main-nav">
            <navCom></navCom>
        </div>
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
        // this.$router.push({path:'/organManage'});
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
    /* width: calc(100% - 150px); */
    width: 100%;
    height: calc(100% - 50px);
    background: url('../../../static/img/mainBg.jpg');
    position: absolute;
    top: 50px;
    right: 0px;
}
.main-nav{
    z-index: 999;
    background-color: rgba(64,158,255, 0.9);
    width: 70%;
    height: 60px;
    border-radius: 10px 10px 0px 0px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    position: fixed;
    bottom: 0;
    left: 15%;
}
</style>
