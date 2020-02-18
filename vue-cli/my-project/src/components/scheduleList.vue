<template>
    <div class="scheduleList-container">
        <div class="scheduleList-search">
            <el-row>
                <el-col :span="6">
                    <div class="search-item">
                        主题：<el-input v-model="zt" style="width:200px;" placeholder="请输入关键字"></el-input>
                    </div>                    
                </el-col>
                <el-col :span="8">
                    <div class="search-item">
                        时间范围：
                        <el-date-picker
                            v-model="sjfw"
                            type="datetimerange"
                            :picker-options="pickerOptions"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            align="right">
                        </el-date-picker>
                    </div>                    
                </el-col>
                <el-col :span="6">
                    <div class="search-item">
                        创建人：<el-input v-model="zt" style="width:200px;" placeholder="请输入关键字"></el-input>
                    </div>                    
                </el-col>
                <el-col :span="4">
                    <div class="search-item">
                        <el-button type="primary"><i class="el-icon-search"></i> 搜索</el-button>
                    </div>                    
                </el-col>
            </el-row>
        </div>

        <div class="scheduleList-table">
            <el-table
                :data="tableData"
                style="width: 100%">
                <el-table-column prop="zt" label="主题" ></el-table-column>
                <el-table-column prop="sj" label="时间" ></el-table-column>
                <el-table-column prop="dd" label="地点" ></el-table-column>
                <el-table-column prop="nr" label="内容" ></el-table-column>
                <el-table-column label="操作" >
                    <template slot-scope="scope">
                        <el-button @click="handleClick(scope.row)" type="text" size="small">详情</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>

        <div class="scheduleList-footer">
            <el-pagination
                background
                layout="prev, pager, next"
                :total="1000">
            </el-pagination>
        </div>

        <el-dialog
            title="详情"
            :visible.sync="dialogVisible"
            width="50%"
            :close-on-click-modal="false"
            :before-close="handleClose">
            <scheduleInfoCom></scheduleInfoCom>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import scheduleInfo from '@/components/scheduleInfo.vue'
export default {
    components:{
        scheduleInfoCom : scheduleInfo
    },
  props: ["userId"],
  data() {
    return {
      zt: "",
      sjfw: "",
      dialogVisible:false,
      tableData:[
          {
              id:'a',
              zt:'ceshizhuti',
              sj:'2020-02-14',
              dd:'somewhere',
              nr:'xxxxxx'
          }
      ],
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            }
          }
        ]
      }
    };
  },
  methods:{
      handleClick(row){
          this.dialogVisible = true;
      },
      handleClose(){
          this.dialogVisible = false;
      }
  }
};
</script>

<style scoped>
.scheduleList-table{
    width: 100%;
    height: calc(100% - 170px);
    margin-top: 10px;
}
.scheduleList-footer{
    width: 100%;
    height: 40px;
    position: absolute;
    bottom: 30px;
}
.search-item{
    width: 100%;
    height: 80px;
    line-height: 80px;
    font-size: 14px;
    color: #333;
    text-align: center;
}
.scheduleList-search {
  width: 98%;
  height: 80px;
  border-radius: 10px;
  border: 1px solid #ddd;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2), 0 0 6px rgba(0, 0, 0, 0.1);
  margin: 0 auto;
}
.scheduleList-container {
  width: 100%;
  height: 100%;
  position: relative;
}
</style>
