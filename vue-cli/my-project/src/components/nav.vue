<template>
    <div class="nav-container">
        <div class="nav-item" v-for="navItem in navData" :key="navItem.id">
            <div class="nav-item-content" @click="go(navItem)">
                <span class="nav-icon"><i :class="navItem.icon"></i></span>
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
                {id:'1', label:'系统首页', icon:'el-icon-house'},
                {id:'6', label:'人员管理', icon:'el-icon-user'},
                {id:'2', label:'日程管理', icon:'el-icon-notebook-1'},
                {id:'3', label:'组织机构管理', icon:'el-icon-office-building'},
                {id:'4', label:'权限管理', icon:'el-icon-document-checked'},
                {id:'5', label:'系统配置', icon:'el-icon-setting'},
            ]
        }
    },
    methods:{
        go(item){
            switch(item.id){
                case '1': this.$router.push({path:'/main'});break;
                case '2': this.$router.push({path:'/schedule'});break;
                case '3': this.$router.push({path:'/organManage'});break;
                case '4': 
                    this.$notify({
                        title: '提示',
                        type: 'warning',
                        duration: 1500,
                        message: '此模块暂未开放！'
                    });
                break;
                case '5': 
                    this.$notify({
                        title: '提示',
                        type: 'warning',
                        duration: 1500,
                        message: '此模块暂未开放！'
                    });
                break;
                case '6': 
                    this.$notify({
                        title: '提示',
                        type: 'warning',
                        duration: 1500,
                        message: '此模块暂未开放！'
                    });
                break;
            }
        }
    }
}
</script>

<style scoped>
.nav-container{
    width: 100%;
    height: 100%;
    border-radius: 10px 10px 0px 0px;   
    /* background-color: rgba(64, 158, 255, 1); */
}
.nav-item{
    width: 120px;
    height: 30px;
    float: left;
    text-align: center;
    position: relative;
}
.nav-item-content{
    width: 120px;
    height: 40px;
    margin:4px;
    /* border-radius: 10px; */
    position: absolute;
    top: 10px;
}
.nav-item-content:hover{
    cursor: pointer;
    border-bottom: 2px solid #fff;
    /* box-shadow: 0 2px 4px rgba(0, 0, 0, .3), 0 0 6px rgba(0, 0, 0, .1); */
}
.nav-icon{
    font-size: 16px;
    color: #fff;
    line-height: 40px;
}
.nav-text{
    font-size: 16px;
    color: #fff;
    line-height: 40px;
}

</style>

