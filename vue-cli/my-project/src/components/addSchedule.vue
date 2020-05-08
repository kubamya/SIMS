<template>
    <div class="addSchedule-container">
        <div class="addSchedule-left">
            <div class="info-row">
                <div class="info-row-label">
                    <i class="el-icon-chat-line-square"></i>&nbsp;主题
                </div>  
                <div class="info-row-value">
                    <el-input v-model="zt" clearable placeholder="请输入主题"></el-input>
                </div>
            </div>
            <div class="info-row">
                <div class="info-row-label">
                    <i class="el-icon-time"></i>&nbsp;时间
                </div>  
                <div class="info-row-value">
                    <el-date-picker
                        style="width:100%"
                        v-model="sj"
                        type="datetime"
                        placeholder="选择日期时间">
                    </el-date-picker>
                </div>
            </div>
            <div class="info-row">
                <div class="info-row-label">
                    <i class="el-icon-map-location"></i>&nbsp;地点
                </div>  
                <div class="info-row-value">
                    <el-input v-model="dd" clearable placeholder="请输入地点"></el-input>
                </div>
            </div>
            <div class="info-row" style="height:110px">
                <div class="info-row-label">
                    <i class="el-icon-data-analysis"></i>&nbsp;内容
                </div>  
                <div class="info-row-value">
                    <el-input v-model="nr" type="textarea" :rows="3" clearable placeholder="请输入内容"></el-input>
                </div>
            </div>
            <div class="info-row">
                <div class="info-row-label">
                    <i class="el-icon-edit-outline"></i>&nbsp;是否签到
                </div>  
                <div class="info-row-value">
                    <el-radio-group v-model="qd">
                        <el-radio :label="1">是</el-radio>
                        <el-radio :label="2">否</el-radio>
                    </el-radio-group>
                </div>
            </div>
            <div class="info-row" v-if="qd == 1">
                <el-button type="primary">生成二维码</el-button>
            </div>
        </div>
        <div class="addSchedule-right">
            <div class="info-row">
                <el-button type="primary" @click="selectCyr()"><i class="el-icon-plus"></i>&nbsp;添加参与人</el-button>
                <p style="font-size:12px;color:#666;line-height:30px;">* 未选择参与人则给自己添加日程</p>
            </div>
            <div class="cyr-area">
                <div class="cryInfo" v-for="item in cyrList" :key="item.id">                    
                    <div class="cryInfo-name">
                        {{item.name}}
                    </div>
                    <!-- <div class="cryInfo-del" @click="delCyr(item)">
                        <i class="el-icon-circle-close"></i>
                    </div> -->
                </div>
            </div>
        </div>
        <div class="addSchedule-confirm">
            <el-button type="primary">确认</el-button>
            <el-button @click="restInfo()">重置</el-button>
        </div>

        <el-dialog
            title="选择参与人"
            :visible.sync="cyrDialogVisible"
            width="30%"
            :close-on-click-modal="false"
            :before-close="handleClose">

            <div class="userTree-area">
               <organTreeCom :showCheckbox="true"></organTreeCom>
            </div>            
            
            <span slot="footer" class="dialog-footer">
                <el-button @click="cyrDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="confirm()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import organTree from './organTree.vue';
export default {
    components:{
        organTreeCom : organTree
    },
  data() {
    return {
      cyrDialogVisible: false,
      zt: "",
      sj: "",
      dd: "",
      nr: "",
      qd: 2,
      cyr: "",
      cyrList: [],
      defaultProps: {
        id: "aaaa",
        children: "children",
        label: "label"
      },
      userTreeData: [
        {
          id: "1",
          label: "组织",
          type: "com",
          children: [
            {
              id: "2",
              label: "部门1",
              type: "dept",
              children: [
                {
                  id: "4",
                  label: "用户1",
                  type: "user"
                },
                {
                  id: "5",
                  label: "用户2",
                  type: "user"
                }
              ]
            },
            {
              id: "3",
              label: "部门2",
              type: "dept",
              children: [
                {
                  id: "6",
                  label: "用户3",
                  type: "user"
                },
                {
                  id: "7",
                  label: "用户4",
                  type: "user"
                }
              ]
            }
          ]
        }
      ]
    };
  },
  methods: {
      restInfo(){
          this.zt = '';
          this.sj = '';
          this.dd = '';
          this.nr = '';
          this.qd = 2;
          this.cyrList = [];
      },
    confirm() {
        this.cyrList = [];
      var nodeList = this.$refs.tree.getCheckedNodes();
      for (var i = 0; i < nodeList.length; i++) {
        if (nodeList[i].type == "user") {
          this.cyrList.push({
            id: nodeList[i].id,
            name: nodeList[i].label
          });
        }
      }
      console.log(this.cyrList);
      this.cyrDialogVisible = false;
    },
    delCyr(item){
        console.log(item);
    },
    handleClose() {
      this.cyrDialogVisible = false;
    },
    selectCyr() {
      this.cyrDialogVisible = true;
    }
  }
};
</script>

<style scoped>
.cryInfo:hover{
    background-color: #eee;
}
.cryInfo{
    width: 90%;
    height: 40px;
    font-size: 14px;
    color: #333;
    position: relative;
    margin-bottom: 10px;
    /* border: 1px solid #ddd; */
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .2), 0 0 6px rgba(0, 0, 0, .1);
}
.cryInfo-name{
    width: 50%;
    height: 40px;
    line-height: 40px;
    position: absolute;
    left: 10px;
}
.cryInfo-del{
    width: 40px;
    height: 40px;
    line-height: 40px;
    color: #F56C6C;
    font-size: 16px;
    position: absolute;
    right: 0;
    cursor: pointer;
}
.cyr-area{
    width: 90%;
    height: 300px;
    padding-left: 5px;
    padding-top: 5px;
    overflow: auto;
}
.addSchedule-container {
  width: 100%;
  height: 100%;
  position: relative;
}
.addSchedule-left {
  width: 49%;
  height: 400px;
  position: absolute;
  top: 0;
  left: 0;
}
.addSchedule-right {
  width: 49%;
  height: 400px;
  position: absolute;
  top: 0;
  right: 0;
}
.addSchedule-confirm {
  width: 100%;
  height: 100px;
  text-align: center;
  position: absolute;
  top: 400px;
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
.userTree-area {
  width: 100%;
  height: 300px;
  overflow: auto;
}
</style>
