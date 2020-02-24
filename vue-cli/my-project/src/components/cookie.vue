<template>
    <div>
        <el-button @click="setCookie('userId', '123', 1)">设置cookie</el-button>
        <el-button @click="getCookie('userId')">获取cookie</el-button>
        <el-button @click="delCookie('userId')">清除cookie</el-button>
    </div>
</template>
<script>
export default {
    data(){
        return {

        }
    },
    methods:{
        delCookie(key){
            this.setCookie(name, '', -1);
        },
        getCookie(key){
            //获取cookie，格式化，去空格
            var getCookie = document.cookie.replace(/[ ]/g, "");
            var allCookie = document.cookie;
            //将获取的cookie以分号为标识，保存到arrCookie数组
            var arrCookie = getCookie.split(";");
            var tips;
            for(var i = 0; i < arrCookie.length; i++){
                var arr = arrCookie[i].split("=");
                if(key == arr[0]){
                    tips = arr[1];
                    break;
                }
            }
            console.log('cookies',allCookie);
            console.log(tips);
        },
        setCookie(name, value, expiredays){
            //设置过期时间
            var exdate = new Date();                   
            exdate.setDate(exdate.getDate() + expiredays);                   
            document.cookie = name + "=" + escape(value) + ";expires=" + exdate.toGMTString() + ";path=/";
        },
    }
}
</script>
