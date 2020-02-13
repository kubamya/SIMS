<template>
    <div class="organ-container">
        <div class="organ-treeContent">
            <organTreeCom @prtFunc="show"></organTreeCom>
        </div>
        <div class="organ-editBtns">
            <el-button class="organ-editBtns-item" round type="primary" @click="openAddCom('com')">新增组织</el-button>
            <el-button class="organ-editBtns-item" round type="primary" @click="openAddCom('dept')">新增部门</el-button>
            <el-button class="organ-editBtns-item" round type="primary" @click="openAddCom('user')">新增人员</el-button>
        </div>
        <div class="organ-infoContent">
            <organInfoCom :nodeId="nodeId" :nodeType="nodeType" ></organInfoCom>
        </div>

        <el-dialog
            :title="addTitle"
            :visible.sync="dialogVisible"
            :close-on-click-modal="false"
            width="50%"
            :before-close="handleClose">
            <div v-if="comShow">
                <div class="info-row">
                    <div class="info-item">
                        <div class="info-item-label"><span class="require">*</span>组织名称：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                v-model="com.name"
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
                                clearable></el-input>
                        </div>
                    </div>
                </div>
            </div>
            <div v-if="deptShow">
                <div class="info-row">
                    <div class="info-item">
                        <div class="info-item-label"><span class="require">*</span>所属组织：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                v-model="dept.comId"
                                placeholder="请选择组织"></el-input>
                        </div>
                        
                    </div>
                    <div class="info-item">
                        <div class="info-item-label">所属部门：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                v-model="dept.pid"
                                placeholder="请选择部门"></el-input>
                        </div>
                    </div>
                </div>
                <div class="info-row">
                    <div class="info-item">
                        <div class="info-item-label"><span class="require">*</span>部门名称：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                v-model="dept.name"
                                placeholder="请输入名称"></el-input>
                        </div>
                        
                    </div>
                    <div class="info-item">
                        <div class="info-item-label">显示顺序：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                v-model="dept.xssx"
                                placeholder="请输入顺序"></el-input>
                        </div>
                    </div>
                </div>
            </div>
            <div v-if="userShow">
                <div class="info-row">
                    <div class="info-item">
                        <div class="info-item-label"><span class="require">*</span>所属组织：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                v-model="user.comId"
                                placeholder="选择组织"></el-input>
                        </div>
                        
                    </div>
                    <div class="info-item">
                        <div class="info-item-label"><span class="require">*</span>所属部门：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                v-model="user.deptId" 
                                placeholder="请选择部门"></el-input>
                        </div>
                    </div>
                </div>
                <div class="info-row">
                    <div class="info-item">
                        <div class="info-item-label"><span class="require">*</span>姓名：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                v-model="user.name" 
                                placeholder="请输入名称"></el-input>
                        </div>
                        
                    </div>
                    <div class="info-item">
                        <div class="info-item-label"><span class="require">*</span>账号：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                v-model="user.loginId" 
                                placeholder="请输入账号"></el-input>
                        </div>
                    </div>
                </div>
                <div class="info-row">
                    <div class="info-item">
                        <div class="info-item-label"><span class="require">*</span>密码：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                v-model="user.password" 
                                type="password"
                                placeholder="请输入密码"></el-input>
                        </div>
                        
                    </div>
                    <div class="info-item">
                        <div class="info-item-label"><span class="require">*</span>确认密码：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                v-model="user.confirmPass" 
                                type="password"
                                placeholder="请确认密码"></el-input>
                        </div>
                    </div>
                </div>
                <div class="info-row">
                    <div class="info-item">
                        <div class="info-item-label">邮箱：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                v-model="user.email" 
                                placeholder="请输入邮箱"></el-input>
                        </div>
                        
                    </div>
                    <div class="info-item">
                        <div class="info-item-label">电话：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                v-model="user.tel" 
                                placeholder="请输入电话"></el-input>
                        </div>
                    </div>
                </div>
                <div class="info-row">
                    <div class="info-item">
                        <div class="info-item-label">显示顺序：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                v-model="user.xssx" 
                                placeholder="请输入顺序"></el-input>
                        </div>
                    </div>
                </div>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="cancelAddcom()">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import organTree from '@/components/organTree.vue'
import organInfo from '@/components/organInfo.vue'
export default {
    components:{
        organTreeCom:organTree,
        organInfoCom:organInfo
    },
    data(){
        return{
            nodeId:'',
            nodeType:'',
            addTitle:'',

            dialogVisible:false,
            comShow:false,
            deptShow:false,
            userShow:false,

            com:{
                name:'',
                xssx:'',
            },
            dept:{
                comId:'',
                pid:'',
                name:'',
                xssx:'',
            },
            user:{
                comId:'',
                deptId:'',
                name:'',
                loginId:'',
                password:'',
                confirmPass:'',
                email:'',
                tel:'',
                xssx:'',
            }
        }
    },
    methods:{
        show(nodeId, nodeType){
            this.nodeId = nodeId;
            this.nodeType = nodeType;
        },
        clearForm(){
            this.com.name = '';
            this.com.xssx = '';
            this.dept.comId = '';
            this.dept.pid = '';
            this.dept.name = '';
            this.dept.xssx = '';
            this.user.comId = '';
            this.user.deptId = '';
            this.user.name = '';
            this.user.loginId = '';
            this.user.password = '';
            this.user.confirmPass = '';
            this.user.email = '';
            this.user.tel = '';
            this.user.xssx = '';
        },
        hideItem(){
            this.comShow = false;
            this.deptShow = false;
            this.userShow = false;
        },
        openAddCom(type){
            this.dialogVisible = true;
            switch(type){
                case 'com' :
                    this.addTitle = '新增组织';
                    this.comShow = true;
                break;
                case 'dept' :
                    this.addTitle = '新增部门';
                    this.deptShow = true;
                break;
                case 'user' :
                    this.addTitle = '新增人员';
                    this.userShow = true;
                break;
            }
        },
        handleClose(){
            this.clearForm();           
            this.hideItem();
            this.dialogVisible = false;
        },
        cancelAddcom(){
            this.clearForm(); 
            this.hideItem();         
            this.dialogVisible = false;
        }
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
    background-color: #fff;
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
    background-color: #fff;
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
.require{
    color: red;
}
</style>
