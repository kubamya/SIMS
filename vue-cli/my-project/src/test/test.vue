<template>
    <div>
        <button @click="changeText()">click me</button>
        {{text}}
    </div>
</template>
<script>
export default {
    data(){
        return {
            text:''
        }
    },
    methods:{
        changeText(){
            this.clickButtton().then(() =>{
                alert('changed');
            })
            
        },
        clickButtton(){
            return new Promise((resolve, reject) => {
                if(true){
                    alert('success');
                    resolve();
                }else{
                    reject();
                }
            });
        },
    }
}
</script>
