<template>
    <div class="scheduleInfo-container">
        <div class="scheduleInfo-info">
            <div class="scheduleInfo-title">日程信息</div>
            <div class="scheduleInfo-info-area">
                <div class="info-row">
                    <div class="info-row-label">
                        <i class="el-icon-chat-line-square"></i>&nbsp;主题
                    </div>  
                    <div class="info-row-value">
                        <el-input v-model="zt" clearable placeholder="请输入主题" readonly></el-input>
                    </div>
                </div>
                <div class="info-row">
                    <div class="info-row-label">
                        <i class="el-icon-time"></i>&nbsp;时间
                    </div>  
                    <div class="info-row-value">
                        <el-input v-model="sj" clearable placeholder="请输入主题" readonly></el-input>
                    </div>
                </div>
                <div class="info-row">
                    <div class="info-row-label">
                        <i class="el-icon-map-location"></i>&nbsp;地点
                    </div>  
                    <div class="info-row-value">
                        <el-input v-model="dd" clearable placeholder="请输入地点" readonly></el-input>
                    </div>
                </div>
                <div class="info-row" style="height:110px">
                    <div class="info-row-label">
                        <i class="el-icon-data-analysis"></i>&nbsp;内容
                    </div>  
                    <div class="info-row-value">
                        <el-input v-model="nr" type="textarea" :rows="3" clearable placeholder="请输入内容" readonly></el-input>
                    </div>
                </div>
                <div class="info-row" v-if="false">
                    <div class="info-row-label">
                        <i class="el-icon-menu"></i>&nbsp;二维码
                    </div>  
                    <div class="info-row-value">
                        <el-input v-model="dd" clearable placeholder="请输入地点" readonly></el-input>
                    </div>
                </div>
            </div>
        </div>
        <div class="scheduleInfo-user">
            <div class="scheduleInfo-title">人员信息</div>
            <div class="scheduleInfo-info-area">
                <div class="cryInfo" v-for="item in cyrList" :key="item.id">                    
                    <div class="cryInfo-name">
                        {{item.name}}
                    </div>
                    <div class="cryInfo-del">
                        <i class="el-icon-document-checked" style="color:#67C23A;" v-if="item.qd"></i>
                        <i class="el-icon-document-delete" style="color:#F56C6C;" v-if="!item.qd"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data(){
        return{
            zt:'',
            sj:'',
            dd:'',
            nr:'',
            cyrList:[
                {id:'1',name:'参与人1',qd:true},
                {id:'2',name:'参与人2',qd:true},
                {id:'3',name:'参与人3',qd:false},
                {id:'4',name:'参与人4',qd:true},
            ]
        }
    }
}
</script>

<style scoped>
.cryInfo-del{
    width: 40px;
    height: 30px;
    line-height: 30px;
    color: #F56C6C;
    font-size: 16px;
    position: absolute;
    right: 0;
    cursor: pointer;
}
.cryInfo{
    width: 90%;
    height: 30px;
    font-size: 14px;
    color: #333;
    position: relative;
    margin-bottom: 5px;
    border: 1px solid #ddd;
    border-radius: 5px;
    /* box-shadow: 0 2px 4px rgba(0, 0, 0, .2), 0 0 6px rgba(0, 0, 0, .1); */
}
.cryInfo-name{
    width: 50%;
    height: 30px;
    color: #333;
    line-height: 30px;
    position: absolute;
    left: 10px;
}
.info-row {
  width: 100%;
  height: 70px;
}
.info-row-label {
  width: 100%;
  height: 30px;
  line-height: 30px;
  color: #333;
}
.scheduleInfo-info-area{
    width: 100%;
    height: 380px;
    background-color: #fff;
    overflow: auto;
}
.scheduleInfo-title{
    width: 100%;
    height: 20px;
    /* background-color: #409EFF;
    text-align: center;
    color: #fff; */
}
.scheduleInfo-user{
    width: 49%;
    height: 400px;
    position: absolute;
    right: 0;
}
.scheduleInfo-info{
    width: 49%;
    height: 400px;
    position: absolute;
    left: 0;
}
.scheduleInfo-container{
    width: 100%;
    height: 400px;
    position: relative;
}
</style>
