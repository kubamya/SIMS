<template>
  <div id="app">    
    <router-view/>
  </div>
</template>

<script>
export default {
  date(){
    return {
      name: 'App'
    }
  },
   methods:{
        checkLoginTime(){
            var lastLoginTime = this.$handleLocalStorage('get','lastLoginTime');

            if(lastLoginTime == undefined || lastLoginTime == null){
                return false;
            }

            var curTime = this.$getCurtime();

            if(curTime - lastLoginTime > 1800){
                return false;
            }else{
                return true;
            };

        },
    },
  mounted(){
    if(this.checkLoginTime()){
      this.$router.push({path: '/main'});
    }else{
      this.$router.push({path: '/login'});
    }
  }
  
}
</script>

<style>
*{
  margin:0px;
  padding:0px;
}
html,body{
  width:100%;
  height:100%;
}
#app {
  width:100%;
  height:100%;
}
</style>
