<template>
    <div class="schedule-container">
        <div class="schedule-content">
            <el-tabs v-model="activeName">
                <el-tab-pane label="新增日程" name="first">
                    <div style="height:600px;">
                        <addScheduleCom></addScheduleCom>
                    </div>                    
                </el-tab-pane>
                <el-tab-pane label="日程一览" name="second">
                    <calenderCom></calenderCom>
                </el-tab-pane>
                <el-tab-pane label="日程查询" name="third">
                    <div style="height:600px;">
                        <scheduleListCom></scheduleListCom>
                    </div>
                </el-tab-pane>
            </el-tabs>
        </div>        
    </div>
</template>
<script>
import calender from '@/components/calender.vue'
import addSchedule from '@/components/addSchedule.vue'
import scheduleList from '@/components/scheduleList.vue'
export default {
    components:{
        calenderCom : calender,
        addScheduleCom : addSchedule,
        scheduleListCom : scheduleList
    },
    data(){
        return {
            activeName: 'second'
        }
    }
}
</script>

<style scoped>
.schedule-container{
    width: 100%;
    height: 100%;
    position: relative;
}
.schedule-content{
    width: 96%;
    height: 94%;
    background-color: #fff;
    position: absolute;
    top: 3%;
    left: calc(2% - 20px);
    padding: 0 20px;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .2), 0 0 6px rgba(0, 0, 0, .1);
}
</style>
