<template>
    <div class="nav-container">
        <div class="nav-item" v-for="navItem in navData" :key="navItem.id">
            <div class="nav-item-content">
                <!-- <span class="nav-icon"><i :class="navItem.icon"></i></span><br> -->
                <span class="nav-text">{{navItem.label}}</span>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data(){
        return {
            navData:[
                {id:'1', label:'菜单1', icon:'el-icon-chat-dot-square'},
                {id:'2', label:'菜单2', icon:'el-icon-message'},
                {id:'3', label:'菜单3', icon:'el-icon-time'},
                {id:'4', label:'菜单4', icon:'el-icon-mobile-phone'},
            ]
        }
    }
}
</script>

<style scoped>
.nav-container{
    width: 100%;
    height: 100%;
    background-color: rgba(64, 158, 255, 1);
}
.nav-item{
    width: 80px;
    height: 80px;
    float: left;
    text-align: center;
}
.nav-item-content{
    width: 70px;
    height: 70px;
    background-color: #fff;
    margin:4px;
    border-radius: 10px;
    border: 1px solid #ddd;
    /* box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04); */
}
.nav-item-content:hover{
    cursor: pointer;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .3), 0 0 6px rgba(0, 0, 0, .1);
}
.nav-icon{
    font-size: 34px;
    line-height: 40px;
}
.nav-text{
    font-size: 14px;
    color: #333;
    line-height: 30px;
}

</style>

