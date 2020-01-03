<template>
    <div class="organInfo-container">
        <div class="info-content" v-if="nodeType == 'com'">
            <div class="info-header">
                <div class="info-header-icon"><i class="el-icon-menu"></i></div>
                <div class="info-header-text">组织信息</div>
            </div>
            <div class="info-row">
                <div class="info-item">
                    <div class="info-item-label"><span class="require">*</span>组织名称：</div>
                    <div class="info-item-value">
                        <el-input 
                            style="width:50%;" 
                            clearable 
                            v-model="com.name" 
                            :disabled="!isUpdate"
                            placeholder="请输入名称"></el-input>
                    </div>
                </div>
                <div class="info-item">
                    <div class="info-item-label">显示顺序：</div>
                    <div class="info-item-value">
                        <el-input 
                            style="width:50%;" 
                            type="number" 
                            v-model="com.xssx" 
                            placeholder="请输入顺序" 
                            :disabled="!isUpdate"
                            clearable></el-input>
                    </div>
                </div>
            </div>
            <div class="info-row"></div>
            <div class="info-row">
                <div class="info-btns">
                    <el-button class="info-btn" :disabled="isUpdate" type="primary">修改</el-button>
                    <el-button class="info-btn" :disabled="!isUpdate" type="primary">保存</el-button>
                    <el-button class="info-btn" :disabled="!isUpdate">取消</el-button>
                </div>                
            </div>
        </div>
        <div class="info-content" v-if="nodeType == 'dept'">
            <div class="info-header">
                <div class="info-header-icon"><i class="el-icon-menu"></i></div>
                <div class="info-header-text">部门信息</div>
            </div>

            <div class="info-row"></div>
            <div class="info-row">
                <div class="info-btns">
                    <el-button class="info-btn" :disabled="isUpdate" type="primary">修改</el-button>
                    <el-button class="info-btn" :disabled="!isUpdate" type="primary">保存</el-button>
                    <el-button class="info-btn" :disabled="!isUpdate">取消</el-button>
                </div>                
            </div>
        </div>
        <div class="info-content" v-if="nodeType == 'user'">
            <div class="info-header">
                <div class="info-header-icon"><i class="el-icon-menu"></i></div>
                <div class="info-header-text">人员信息</div>
            </div>

            <div class="info-row"></div>
            <div class="info-row">
                <div class="info-btns">
                    <el-button class="info-btn" :disabled="isUpdate" type="primary">修改</el-button>
                    <el-button class="info-btn" :disabled="!isUpdate" type="primary">保存</el-button>
                    <el-button class="info-btn" :disabled="!isUpdate">取消</el-button>
                </div>                
            </div>
        </div>
    </div>
</template>
<script>
export default {
    props:['nodeId','nodeType'],
    data(){
        return{
            isUpdate:false,
            com:{
                name:'',
                xssx:''
            }
        }
    }
}
</script>
<style scoped>
.organInfo-container{
    width: calc(100% - 20px);
    height: calc(100% - 20px);
    margin: 0 auto;
    margin-top: 10px;
}
.info-content{
    width: 100%;
    height: 100%;
}
.info-header{
    width: 100%;
    height: 30px;
    position: relative;
    border-bottom: 1px solid #409EFF;
}
.info-header-icon{
    width: 30px;
    height: 30px;
    text-align: center;
    line-height: 30px;
    color: #409EFF;
    font-size: 18px;
    position: absolute;
    top: 0;
    left: 0;
}
.info-header-text{
    width: calc(100% - 30px);
    height: 30px;
    text-align: left;
    line-height: 30px;
    color: #333;
    font-size: 14px;
    position: absolute;
    top: 0;
    left: 30px;
}
.info-row{
    width: 100%;
    height: 50px;
    line-height: 50px;
}
.info-item{
    width: 50%;
    height: 50px;
    float: left;
    position: relative;
}
.info-item-label{
    width: 30%;
    height: 50px;
    text-align: right;
    color: #333;
    font-size: 14px;
    position: absolute;
    top: 0;
    left: 0;
}
.info-item-value{
    width: 70%;
    height: 50px;
    text-align: left;    
    position: absolute;
    top: 0;
    right: 0;
}
.info-btns{
    width: 100%;
    height: 50px;
    line-height: 50px;
    text-align: center;
}
.info-btn{
    width: 100px;
}
.require{
    color: red;
}
</style>

