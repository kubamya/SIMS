<template>
    <div class="organ-container">
        <div class="organ-treeContent">
            <organTreeCom></organTreeCom>
        </div>
        <div class="organ-editBtns">
            <el-button class="organ-editBtns-item" type="primary">新增1</el-button>
            <el-button class="organ-editBtns-item" type="primary">新增2</el-button>
            <el-button class="organ-editBtns-item" type="primary">新增3</el-button>
        </div>
        <div class="organ-infoContent">

        </div>
    </div>
</template>
<script>
import organTree from '@/components/organTree.vue'
export default {
    components:{
        organTreeCom:organTree
    }
}
</script>

<style scoped>
.organ-container{
    width: 100%;
    height: 100%;
    position: relative;
}
.organ-treeContent{
    background-color: #fff;
    width: 400px;
    height: 96%;
    overflow: auto;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .2), 0 0 6px rgba(0, 0, 0, .1);
    position: absolute;
    top: 2%;
    left: 10px;
}
.organ-editBtns{
    width: calc(100% - 430px);
    height: 80px;
    line-height: 80px;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .2), 0 0 6px rgba(0, 0, 0, .1);
    position: absolute;
    top: 2%;
    left: 420px;
}
.organ-infoContent{
    width: calc(100% - 430px);
    height: calc(96% - 90px);
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .2), 0 0 6px rgba(0, 0, 0, .1);
    position: absolute;
    top: calc(2% + 90px);
    left: 420px;
}
.organ-editBtns-item{
    width: 100px;
    margin-left: 20px;
}
</style>
