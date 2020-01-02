package com.example.util;

import com.example.dept.service.impl.DeptServiceImpl;
import com.example.model.Com;
import com.example.model.Dept;
import com.example.model.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 公用方法
 */
public class CommonUtil {

    @Autowired
    private static UserService userService;

    /**
     * 获取32位UUID
     * @return
     */
    public static String getUUid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 获取当前日期
     * @return
     */
    public static Date getCurDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(df.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Date getCurDateTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.parse(df.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Map<String, Object>> getDeptTreeByComId(String deptid, List<Dept> depts){
        List<Map<String, Object>> tree = new ArrayList<>();
        for(Dept dept : depts){
            if(deptid.equals(dept.getCPid())){
                Map<String, Object> treeNode = new HashMap<>();
                treeNode.put("label", dept.getCName());
                treeNode.put("id", dept.getCId());
                treeNode.put("children",getDeptTreeByComId(dept.getCId(), depts));

                //部门下的人员
//                User user = new User();
//                user.setCDeptId(dept.getCId());
//                List<User> userList = userService.getUsersByDeptId(user);
//                List<Map<String, Object>> userMapList =  new ArrayList<>();
//                if(userList != null && !userList.isEmpty()){
//                    for (User user1 : userList){
//                        Map<String, Object> userMap = new HashMap<>();
//                        userMap.put("label", user1.getCUserName());
//                        userMap.put("id", user1.getCId());
//
//                        userMapList.add(userMap);
//                    }
//                }
//
//                treeNode.put("children",getDeptTreeByComId(dept.getCId(), depts).addAll(userMapList));

                tree.add(treeNode);
            }
        }
        return tree;
    }
}
