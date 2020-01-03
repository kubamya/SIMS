<template>
    <div class="organ-container">
        <div class="organ-treeContent">
            <organTreeCom></organTreeCom>
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

</style>
