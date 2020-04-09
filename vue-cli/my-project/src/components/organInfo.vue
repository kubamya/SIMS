<template>
    <div class="organInfo-container" :loading="loading">
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
                    <el-button class="info-btn" :disabled="isUpdate" @click="delCom()" type="danger">删除</el-button>
                    <el-button class="info-btn" :disabled="isUpdate" @click="update()" type="primary">修改</el-button>
                    <el-button class="info-btn" :disabled="!isUpdate" @click="updataCom()" type="primary">保存</el-button>
                    <el-button class="info-btn" :disabled="!isUpdate" @click="cancel()">取消</el-button>
                </div>                
            </div>
        </div>
        <div class="info-content" v-if="nodeType == 'dept'">
            <div class="info-header">
                <div class="info-header-icon"><i class="el-icon-menu"></i></div>
                <div class="info-header-text">部门信息</div>
            </div>
            <div class="info-row">
                <div class="info-item">
                    <div class="info-item-label"><span class="require">*</span>所属组织：</div>
                    <div class="info-item-value">
                        <el-input 
                            style="width:50%;" 
                            clearable 
                            v-model="dept.comId" 
                            :disabled="!isUpdate"
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
                            :disabled="!isUpdate"
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
                            :disabled="!isUpdate"
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
                            :disabled="!isUpdate"
                            placeholder="请输入顺序"></el-input>
                    </div>
                </div>
            </div>
            <div class="info-row"></div>
            <div class="info-row">
                <div class="info-btns">                    
                    <el-button class="info-btn" :disabled="!isUpdate" type="danger">删除</el-button>
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
            <div class="info-row">
                <div class="info-item">
                    <div class="info-item-label"><span class="require">*</span>所属组织：</div>
                    <div class="info-item-value">
                        <el-input 
                            style="width:50%;" 
                            clearable 
                            v-model="user.comId" 
                            :disabled="!isUpdate"
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
                            :disabled="!isUpdate"
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
                            :disabled="!isUpdate"
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
                            :disabled="!isUpdate"
                            placeholder="请输入账号"></el-input>
                    </div>
                </div>
            </div>
            <!-- <div class="info-row">
                <div class="info-item">
                    <div class="info-item-label"><span class="require">*</span>密码：</div>
                    <div class="info-item-value">
                        <el-input 
                            style="width:50%;" 
                            clearable 
                            v-model="user.password" 
                            :disabled="!isUpdate"
                            type="password"
                            placeholder="请输入密码"></el-input>
                    </div>
                    
                </div>
                <div class="info-item">
                    <div class="info-item-label">确认密码：</div>
                    <div class="info-item-value">
                        <el-input 
                            style="width:50%;" 
                            clearable 
                            v-model="user.confirmPass" 
                            :disabled="!isUpdate"
                            type="password"
                            placeholder="请确认密码"></el-input>
                    </div>
                </div>
            </div> -->
            <div class="info-row">
                <div class="info-item">
                    <div class="info-item-label">邮箱：</div>
                    <div class="info-item-value">
                        <el-input 
                            style="width:50%;" 
                            clearable 
                            v-model="user.email" 
                            :disabled="!isUpdate"
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
                            :disabled="!isUpdate"
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
                            :disabled="!isUpdate"
                            placeholder="请输入顺序"></el-input>
                    </div>
                </div>
            </div>
            <div class="info-row"></div>
            <div class="info-row">
                <div class="info-btns">
                    <el-button class="info-btn" :disabled="!isUpdate" type="danger">删除</el-button>
                    <el-button class="info-btn" :disabled="isUpdate" type="primary">修改</el-button>
                    <el-button class="info-btn" :disabled="!isUpdate" type="primary">保存</el-button>
                    <el-button class="info-btn" :disabled="!isUpdate" type="primary">修改密码</el-button>
                    <el-button class="info-btn" :disabled="!isUpdate">取消</el-button>
                </div>                
            </div>
        </div>
    </div>
</template>
<script>
import _global from '@/global/global.vue'
export default {
    props:['nodeId','nodeType'],
    data(){
        return{
            isUpdate:false,
            loading: false,
            com:{
                name:'',
                xssx:'',
                id:'',
            },
            dept:{
                comId:'',
                pid:'',
                name:'',
                xssx:'',
                id:'',
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
                id:'',
            }
        }
    },
    watch:{
        nodeId(){
            if(this.nodeType == 'dept'){
                var params = new URLSearchParams();
                params.append('deptId', this.nodeId);

                this.$axios({method:'post',url: _global.requestUrl+'/api/organ/v1/getDeptInfo', data: params})
                    .then(response =>{
                        var res = this.$handleRes(response);

                        if(res.code == 100){
                            console.log(res);
                            this.dept.id = res.data.cid;
                            this.dept.name = res.data.cname;
                            this.dept.xssx = res.data.nxssx;
                            this.dept.comId = res.data.ccomId;
                            this.dept.pid = res.data.cpid;
                        }else{
                            this.$message({
                                message: res.msg,
                                type: 'warning'
                            });
                        }
                    })
                return;
            }
            if(this.nodeType == 'com'){

                var params = new URLSearchParams();
                params.append('comId', this.nodeId);

                this.$axios({method:'post',url: _global.requestUrl+'/api/organ/v1/getComInfo', data: params})
                    .then(response =>{
                        var res = this.$handleRes(response);

                        if(res.code == 100){
                            console.log(res);
                            this.com.id = res.data.cid;
                            this.com.name = res.data.cname;
                            this.com.xssx = res.data.nxssx;
                        }else{
                            this.$message({
                                message: res.msg,
                                type: 'warning'
                            });
                        }
                    })

                return;
            }
            if( this.nodeType== 'user'){
                var params = new URLSearchParams();
                params.append('userId', this.nodeId);

                this.$axios({method:'post',url: _global.requestUrl+'/api/organ/v1/getUserInfo', data: params})
                    .then(response =>{
                        var res = this.$handleRes(response);

                        if(res.code == 100){
                            console.log(res);
                            this.user.id = res.data.cid;
                            this.user.name = res.data.cuserName;
                            this.user.xssx = res.data.nxssx;
                            this.user.comId = res.data.ccomId;
                            this.user.deptId = res.data.cdeptId;
                            this.user.loginId = res.data.cloginId;
                            this.user.password = '';
                            this.user.confirmPass = '';
                            this.user.email = res.data.cemail;
                            this.user.tel = res.data.ctel;
                        }else{
                            this.$message({
                                message: res.msg,
                                type: 'warning'
                            });
                        }
                    })
                return;
            }
        },
    },
    methods:{
        //修改按钮点击事件
        update(){
            this.isUpdate = true;
        },
        //取消按钮点击事件
        cancel(){
            this.isUpdate = false;
        },
        //修改com信息
        updataCom(){
            this.loading = true;
            var params = new URLSearchParams();
            params.append('cXgr', this.$handleLocalStorage('get','userid'));
            params.append('comId', this.com.id);
            params.append('cName', this.com.name);
            params.append('nXssx', this.com.xssx);

            this.$axios({method:'post',url: _global.requestUrl+'/api/com/v1/updateComById', data: params}).then(response =>{
                var res = this.$handleRes(response);
                if(res.code == 100){
                    //保存成功
                    this.$message({
                        message: '保存成功！',
                        type: 'success'
                    });
                    this.loading = true;
                    this.cancel();
                    this.$emit('infoUpdated');
                }else{
                    //保存失败
                    this.$message({
                        message: res.msg,
                        type: 'warning'
                    });
                    this.loading = false;
                }
            }).catch(error =>{
                this.$message.error('保存失败！请联系管理员',error);
                this.loading = false;
            });

        },
        //删除com信息
        delCom(){
            this.$confirm('此操作将删除 '+ this.com.name +' 及下级信息, 是否继续?', '提示',{
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.loading = true;
                var params = new URLSearchParams();
                params.append('cXgr', this.$handleLocalStorage('get','userid'));
                params.append('comId', this.com.id);

                this.$axios({method:'post',url: _global.requestUrl+'/api/com/v1/setComDisableById', data: params}).then(response => {
                    var res = this.$handleRes(response);
                    if(res.code == 100){
                        //删除成功
                        this.$message({
                            message: '删除成功！',
                            type: 'success'
                        });
                        this.loading = false;
                        this.$emit('infoUpdated');
                    }else{
                        //删除失败
                        this.$message({
                            message: res.msg,
                            type: 'warning'
                        });
                        this.loading = false;
                    }
                }).catch(error => {
                    this.$message.error('删除失败！请联系管理员',error);
                    this.loading = false;
                })

            }).catch(() => {

            });
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

