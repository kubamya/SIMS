<template>
    <div class="organ-container">
        <div class="organ-treeContent">
            <organTreeCom
                ref="organTree"
                @prtFunc="show"
                :key="reload">
            </organTreeCom>
        </div>
        <div class="organ-editBtns">
            <el-button class="organ-editBtns-item" round :disabled="addComDisable" type="primary" @click="openAddCom('com')">新增组织</el-button>
            <el-button class="organ-editBtns-item" round :disabled="addDeptDisable" type="primary" @click="openAddCom('dept')">新增部门</el-button>
            <el-button class="organ-editBtns-item" round :disabled="addUserDisable" type="primary" @click="openAddCom('user')">新增人员</el-button>
        </div>
        <div class="organ-infoContent">
            <organInfoCom 
                :nodeId="nodeId" 
                :nodeType="nodeType"
                :key="reload"
                @infoUpdated="infoUpdated" >
            </organInfoCom>
        </div>

        <el-dialog
            :title="addTitle"
            :loading="loading"
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
                                disabled
                                v-model="dept.comName"
                                placeholder="请选择组织"></el-input>
                        </div>
                        
                    </div>
                    <div class="info-item">
                        <div class="info-item-label">所属部门：</div>
                        <div class="info-item-value">
                            <el-input 
                                style="width:50%;" 
                                clearable 
                                disabled
                                v-model="dept.pName"
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
                <el-button type="primary" @click="save()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import _global from '@/global/global.vue'
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
            nodeName:'',

            addTitle:'',

            reload:'',

            dialogVisible:false,
            loading:false,
            comShow:false,
            deptShow:false,
            userShow:false,

            addComDisable:false,
            addDeptDisable:true,
            addUserDisable:true,

            com:{
                name:'',
                xssx:'',
            },
            dept:{
                comId:'',
                comName:'',
                pid:'',
                pName:'',
                name:'',
                xssx:'',
            },
            user:{
                comId:'',
                comName:'',
                deptId:'',
                deptName:'',
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
        //组织信息组件删除成功回调
        infoUpdated(){
            this.reload = this.$getCurtime();
        },
        //保存dept信息
        saveDept(){
            //组装参数
            this.loading = true;
            var params = new URLSearchParams();
            params.append('cCjr', this.$handleLocalStorage('get','userid'));
            params.append('cName', this.dept.name);
            params.append('nXssx', this.dept.xssx);
            if(this.nodeType == 'dept'){
                params.append('cPid', this.dept.pid);
            }            
            params.append('cComid', this.dept.comId);

            this.$axios({method:'post',url: _global.requestUrl+'/api/dept/v1/addDept', data: params}).then(response => {
                var res = this.$handleRes(response);
                if(res.code == 100){
                    this.$message({
                        message: '保存成功！',
                        type: 'success'
                    });
                    this.clearForm();
                    this.loading = false;
                    this.dialogVisible = false;
                    this.reload = this.$getCurtime();
                }else{
                    this.$message({
                        message: res.data,
                        type: 'warning'
                    });
                    this.loading = false;
                }
            }).catch(error => {
                this.$message.error('保存失败！请联系管理员',error);
                this.loading = false;
            })
        },
        //保存com信息
        saveCom(){
            //组装参数
            this.loading = true;
            var params = new URLSearchParams();
            params.append('cCjr', this.$handleLocalStorage('get','userid'));
            params.append('cName', this.com.name);
            params.append('nXssx', this.com.xssx);

            this.$axios({method:'post',url: _global.requestUrl+'/api/com/v1/addCom', data: params}).then(response => {
                var res = this.$handleRes(response);
                if(res.code == 100){
                    this.$message({
                        message: '保存成功！',
                        type: 'success'
                    });
                    this.clearForm();
                    this.loading = false;
                    this.dialogVisible = false;
                    this.reload = this.$getCurtime();
                }else{
                    this.$message({
                        message: res.data,
                        type: 'warning'
                    });
                    this.loading = false;
                }
            }).catch(error => {
                this.$message.error('保存失败！请联系管理员',error);
                this.loading = false;
            })


        },
        //保存新增信息
        save(){
            if(this.comShow){
                //保存com信息
                this.saveCom();
            }else if(this.deptShow){
                //保存dept信息
                this.saveDept();
            }else if(this.userShow){
                //保存user信息
            }
        },
        show(nodeId, nodeType, nodeName){
            this.nodeId = nodeId;
            this.nodeType = nodeType;
            this.nodeName = nodeName;

            switch(nodeType){
                case 'com': 
                    this.addDeptDisable = false;
                    this.addUserDisable = true;
                break;
                case 'dept': 
                    this.addUserDisable = false;
                    this.addDeptDisable = false;
                break;
                case 'user':
                    this.addDeptDisable = true;
                    this.addUserDisable = true;
                break;
            }
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
                    //判断是组织下创建还是部门下创建
                    if(this.nodeType == 'com'){
                        //组织下创建
                        console.log('com-dept-nodeId',this.nodeId);
                        this.dept.comId = this.nodeId;
                        this.dept.comName = this.nodeName;
                        //清掉上次点击数据
                        this.dept.pid = null;
                        this.dept.pName = null;
                    }else{
                        //部门下创建                        
                        this.dept.pid = this.nodeId;
                        this.dept.pName = this.nodeName;
                        //清掉上次点击数据
                        this.dept.comId = null;
                        this.dept.comName = null;
                        //查所属组织
                        this.getParentCom();
                        console.log('dept-dept-nodeId',this.dept.comId);
                    }
                break;
                case 'user' :
                    this.addTitle = '新增人员';
                    this.userShow = true;
                break;
            }
        },
        //获取所属组织
        getParentCom(){
            var params = new URLSearchParams();
            params.append('nodeId', this.nodeId);

            this.$axios({method:'post',url: _global.requestUrl+'/api/organ/v1/getParentComInfo', data: params}).then(response => {
                debugger
                var res = this.$handleRes(response);
                if(res.code == 100){
                    this.dept.comId = res.data.cid;
                    this.dept.comName = res.data.cname;
                    this.user.comId = res.data.cid;
                    this.user.comName = res.data.cname;
                }else{
                    this.$message({
                        message: res.data,
                        type: 'warning'
                    });
                }
            }).catch(error => {
                this.$message.error('获取所属组织失败！请联系管理员',error);
            })

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
