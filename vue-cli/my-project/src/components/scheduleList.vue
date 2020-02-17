<template>
    <div class="scheduleList-container">
        <div class="scheduleList-search">

        </div>
    </div>
</template>
<script>
export default {
    props:['userId'],
    data(){
        return{

        }
    }
}
</script>

<style scoped>
.scheduleList-search{
    width: 96%;
    height: 200px;
    border-radius: 10px;
    border:1px solid #ddd;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .2), 0 0 6px rgba(0, 0, 0, .1);
    margin: 0 auto;
}
.scheduleList-container{
    width: 100%;
    height: 100%;
    position: relative;
}
</style>
