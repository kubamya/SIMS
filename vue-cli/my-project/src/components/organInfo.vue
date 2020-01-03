<template>
    <div class="organInfo-container">
        <div v-if="nodeType == 'com'">com</div>
        <div v-if="nodeType == 'dept'">dept</div>
        <div v-if="nodeType == 'user'">user</div>
    </div>
</template>
<script>
export default {
    props:['nodeId','nodeType'],
    data(){
        return{

        }
    }
}
</script>
<style scoped>
.organInfo-container{
    /* background-color: #ccc; */
    width: calc(100% - 20px);
    height: calc(100% - 20px);
    margin: 0 auto;
    margin-top: 10px;
}
</style>

