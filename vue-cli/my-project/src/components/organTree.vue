<template>
    <div class="organ-tree-container">
        <div class="organ-tree-header">
            <div class="organ-tree-header-icon"><i class="el-icon-school"></i></div>
            <div class="organ-tree-header-text">组织机构一览</div>
            <div class="organ-tree-header-refresh"><i class="el-icon-refresh"></i></div>
        </div>
        <div class="organ-tree-content">
            <el-tree
                :props="props"
                :load="loadNode"
                :show-checkbox="showCheckbox"
                @node-click="handleNodeClick"
                :expand-on-click-node="false"
                lazy>
            </el-tree>
        </div>
    </div>
</template>
<script>
import _global from '@/global/global.vue'
export default {
    props:{
        showCheckbox:{
            type: Boolean,
            default: function(params){
                return false;
            }
        }
    },
    data(){
        return{
            props: {
                label: 'name',
                id:'id',
                children: 'zones',
                isLeaf: 'leaf'
            },
        }
    },
    mounted(){
        // this.getTreeRoot();
    },
    methods:{
        handleNodeClick(data){
            this.$emit('prtFunc',data.id, data.type, data.name);
        },
        loadNode(node, resolve){
            //如果展开第一级节点，从后台加载一级节点列表
            if (node.level == 0) {
                this.loadRootNode(resolve);
            }
            //如果展开其他级节点，动态从后台加载下一级节点列表
            if (node.level >= 1){
                this.loadChildNode(node, resolve);
            }
        },
        loadChildNode(node, resolve){
            var params = new URLSearchParams();
            params.append('id', node.data.id);
            params.append('type', node.data.type);
            this.$axios({method:'post',url: _global.requestUrl+'/api/organ/v1/getOrganTreeNode', data: params})
                .then(response=>{
                    var res = this.$handleRes(response);
                    return resolve(res.data);
                });
        },
        loadRootNode(resolve){
            var params = new URLSearchParams();
            params.append('comId', this.$handleLocalStorage('get', 'comId'));
            this.$axios({method:'post',url: _global.requestUrl+'/api/organ/v1/getOrganTreeRoot', data: params})
                .then(response=>{
                    var res = this.$handleRes(response);
                    return resolve(res.data);
                });
        }
    }
}
</script>

<style scoped>
.organ-tree-container{
    width: 100%;
    height: 100%;
}
.organ-tree-header{
    width: 100%;
    height: 30px;
    border-bottom: 1px solid #409EFF;
    position: relative;
}
.organ-tree-header-icon{
    width: 30px;
    height: 30px;
    color: #409Eff;
    text-align: center;
    font-size: 18px;
    line-height: 30px;
    position: absolute;
    top: 0;
    left: 10px;
}
.organ-tree-header-refresh{
    width: 30px;
    height: 30px;
    color: #409Eff;
    text-align: center;
    font-size: 18px;
    line-height: 30px;
    position: absolute;
    top: 0;
    right: 10px;
    cursor: pointer;
}
.organ-tree-header-text{
    width: calc(100% - 40px);
    height: 30px;
    line-height: 30px;
    color: #333;
    font-size: 14px;
    position: absolute;
    top: 0;
    left: 40px;
}
.organ-tree-content{
    width: 100%;
    height: calc(100% - 31px);
    overflow: auto;
}
</style>

