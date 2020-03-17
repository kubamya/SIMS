<template>
    <div class="login-container" v-loading="loading">
        
        <div class="login-form">
            <div class="login-form-left">
                <img src="../../../static/img/login/leftBackGround.png" width="100%" alt="">
            </div>
            <div class="login-form-right">
                <div class="login-form-right-title">
                    SIMS
                </div>
                <div class="login-form-ipt">
                    <img src="../../../static/img/login/user.png" width="13"><input type="text" v-model="loginId" placeholder="请输入账号">
                </div>
                <div class="login-form-ipt">
                    <img src="../../../static/img/login/password.png" width="13"><input type="password" v-model="password" placeholder="请输入密码">
                </div>
                <div class="login-form-ipt" style="border:none;">
                    <el-button style="width:350px;height:45px;background:#2882fe;color:#fff;font-size:16px" @click="login()">登 录</el-button>
                </div>                
            </div>
            <!-- <div class="login-form-row">
                <div class="ipt-ico">
                    <i class="el-icon-user"></i>
                </div>
                <div class="ipt-info">
                    <input type="text" v-model="loginId" placeholder="请输入账号">
                </div>
            </div>
            <div class="login-form-row">
                <div class="ipt-ico">
                    <i class="el-icon-lock"></i>
                </div>
                <div class="ipt-info">
                    <input type="password" v-model="password" placeholder="请输入密码">
                </div>
            </div>
            <div class="login-form-row">
                <el-button type="primary" style="width:100%;height:100%;" @click="login()">登录</el-button>
            </div> -->
        </div>
    </div>
</template>
<script>
import _global from '@/global/global.vue'
export default {
    data(){
        return {
            loginId:'',
            password:'',
            loading:false
        }
    },
    methods:{
        
        login(){
            this.loading = true;
            var params = new URLSearchParams();
            params.append('loginId', this.loginId);
            params.append('password', this.password);

            this.$axios({method:'post',url: _global.requestUrl+'/api/login/v1/loginDefault', data: params})
                .then(response =>{
                    var res = this.$handleRes(response);

                    if(res.code == 100){
                        console.log(res);
                        this.$handleLocalStorage('set', 'comId', res.data.comid);
                        this.$handleLocalStorage('set', 'deptid', res.data.deptid);
                        this.$handleLocalStorage('set', 'userid', res.data.id);
                        this.$handleLocalStorage('set', 'username', res.data.username);
                        this.$handleLocalStorage('set', 'lastLoginTime', this.$getCurtime());
                        this.$router.push({path: '/main'});
                        this.loading = false;
                    }else{
                        this.loading = false;
                        this.$message({
                            message: res.msg,
                            type: 'warning'
                        });
                    }
                })
        }
    }
}
</script>

<style scoped>
.login-form-left{
    width: 650px;
    height: 100%;
    float: left;
    /* background-image: url('../../../static/img/login/leftBackGround.png'); */
}
.login-form-right{
    width: 550px;
    height: 100%;
    float: left;
}
.login-form-ipt{
    width: 350px;
    margin: 0 auto;
    margin-top: 15px;
    text-align: center;
    border: 1px solid #eee;
}
.login-form-ipt input{
    width: 300px;
    height: 40px;
    border:none;
    outline: none;
    /* border: 1px solid #eee; */
    padding-left: 10px;
    line-height: 10px;
    font-size: 16px;
    color: #969696;
}
.login-form-right-title{
    color: #2882fe;
    font-size: 36px;
    text-align: center;
    width: 100%;
    height: 200px;
    line-height: 280px;
}
.login-container{
    width: 100%;
    height: 100%;
    background-image: url('../../../static/img/login/background.png');
    position: relative;
}
.login-logo{
    width: 100%;
    height: 30%;
    background-color: #409EFF;
    position: relative;
    padding-top: 30px;
}
.login-logo-img{
    width: 100px;
    height: 100px;
    border: 1px solid #fff;
    margin: 0 auto;
    border-radius: 50px
}
.login-form{
    width: 1200px;
    height: 573px;
    background-color: #fff;
    position: absolute;
    left:50%;
    top:50%;
    transform: translate(-50%, -50%);
    box-sizing: border-box;
    /* border-radius: 10px; */
    box-shadow: 0 16px 32px rgba(0, 0, 0, .24), 0 0 12px rgba(0, 0, 0, .08);
    /* padding-top: 50px; */
}
.login-form-row{
    width: 70%;
    height: 40px;
    margin: 0 auto;
    position: relative;
    text-align: center;
    margin-top: 15px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 20px;
}
.ipt-ico{
    width: 10%;
    height: 40px;
    line-height: 40px;
    text-align: center;
    position: absolute;
    top: 0;
    left: 10%;
    color: #409EFF;
}
.ipt-info{
    width: 70%;
    height: 40px;
    line-height: 40px;
    text-align: center;
    position: absolute;
    top: 0;
    right: 0;
}
.ipt-info input{
    width: 100%;
    border: none;
    outline: none;
    background: none;
    padding-left: 10px;
    font-size: 10px;
    color: #999
}
.login-footer{
    width: 100%;
    height: 30px;
    line-height: 30px;
    font-size: 12px;
    color: #aaa;
    text-align: center;
    position: fixed;
    bottom: 0;
}
</style>
