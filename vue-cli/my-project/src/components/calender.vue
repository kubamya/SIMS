<template>
    <div class="calender-container">
        <div class="calender-title">
            {{curYear}}&nbsp;年&nbsp;{{curMonth}}&nbsp;月
        </div>
        <div class="calender-week">
            <div class="calender-week-item" v-for="item in weekData" :key="item.id">
                {{item.name}}
            </div>
        </div>
        <div class="calender-date">
            <div class="calender-date-item" v-for="item in calenderData" :key="item.id" :id="item.id">
                <div class="calender-date-item-left">
                  {{item.day}}
                </div>
                <div class="calender-date-item-right">

                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
  data() {
    return {
      calenderData: [],
      weekData:[
          {id:'1',name:'周一'},
          {id:'2',name:'周二'},
          {id:'3',name:'周三'},
          {id:'4',name:'周四'},
          {id:'5',name:'周五'},
          {id:'6',name:'周六'},
          {id:'7',name:'周日'}
      ],
      curYear:'',
      curMonth:'',
      month_olypic: [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31], //闰年每个月份的天数
      month_normal: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31],
      month_name: [
        "January",
        "Febrary",
        "March",
        "April",
        "May",
        "June",
        "July",
        "Auguest",
        "September",
        "October",
        "November",
        "December"
      ]
    };
  },
  mounted() {
    this.generateCal();
  },
  methods: {
    generateCal() {
      var calender = [];
      //获取当天的年月日
      var my_date = new Date();
      var my_year = my_date.getFullYear(); //获取年份
      var my_month = my_date.getMonth(); //获取月份，一月份的下标为0
      var my_day = my_date.getDate(); //获取当前日期
      this.curYear = my_year;
      this.curMonth = my_month + 1;

      //获取当前月份第一天是周几
      var first_day = this.dayStart(my_month, my_year);
      //补充第一天以前的空位
      for (var i = 0; i < first_day - 1; i++) {
        calender.push({
          year: "",
          month: "",
          day: "",
          id: "i"+i
        });
      }
      //获取本月有多少天
      var total_day = this.daysMonth(my_month, my_year);
      console.log("本月总天数", total_day);
      for (var j = 0; j < total_day; j++) {
        calender.push({
          year: my_year,
          month: my_month + 1,
          day: j + 1,
          id: "" + my_year + my_month + (j + 1)
        });
      }
      if(calender.length == 42){
          this.calenderData = calender;
      }else{
          var extBlank = 42 - calender.length;
          for(var k = 0; k < extBlank; k++){
              calender.push({
                year: "",
                month: "",
                day: "",
                id: "k"+k
            });
          }
          this.calenderData = calender;
      }
      console.log(calender);
    },

    //根据年月获取当月第一天是周几
    dayStart(month, year) {
      var tmpDate = new Date(year, month, 1);
      return tmpDate.getDay();
    },
    //根据年份判断某月有多少天(11,2018),表示2018年12月
    daysMonth(month, year) {
      var tmp1 = year % 4;
      var tmp2 = year % 100;
      var tmp3 = year % 400;

      if ((tmp1 == 0 && tmp2 != 0) || tmp3 == 0) {
        return this.month_olypic[month]; //闰年
      } else {
        return this.month_normal[month]; //非闰年
      }
    }
  }
};
</script>

<style scoped>
.calender-date-item-btn{
  width: 10%;
  height: 100%;
  line-height: 0;
  font-size: 14px;
  position: absolute;
  right: 0;
  top: 10px;
  cursor: pointer;
}
.calender-date-item-left{
  width: 30%;
  height: 100%;
  position: absolute;
  left: 0;
  top: 0;
}
.calender-container {
  width: 100%;
  height: 100%;
  background-color: #ffffff;
}
.calender-title{
    width: calc(98% + 13px);
    height: 50px;
    line-height: 50px;
    text-align: center;
    font-size: 20px;
    background-color: #67C23A;
    border-radius: 5px;
    color: #ffffff;
}
.calender-week{
    width: 100%;
    height: 50px;
    line-height: 50px;
    text-align: center;
    box-sizing: border-box;
}
.calender-date{
    width: 100%;
    height: 255px;
    text-align: center;
    box-sizing: border-box;
}
.calender-week-item{
    float: left;
    width: 14%;
    height: 50px;
    text-align: center;
    background-color: #67C23A;
    margin: 1px;
    border-radius: 5px;
    color: #ffffff;
}
.calender-date-item{
    float: left;
    width: 14%;
    height: 70px;
    line-height: 70px;
    text-align: center;
    background-color: #dddddd;
    margin: 1px;
    border-radius: 5px;
    position: relative;
}
.calender-date-item:hover{
    /* cursor: pointer; */
    background-color: #cccccc;
}
</style>
